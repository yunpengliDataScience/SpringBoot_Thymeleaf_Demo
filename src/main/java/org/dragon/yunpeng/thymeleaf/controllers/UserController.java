package org.dragon.yunpeng.thymeleaf.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.dragon.yunpeng.thymeleaf.entities.User;
import org.dragon.yunpeng.thymeleaf.pojos.AccessCategory;
import org.dragon.yunpeng.thymeleaf.pojos.AccessDictionary;
import org.dragon.yunpeng.thymeleaf.pojos.LabelIdPair;
import org.dragon.yunpeng.thymeleaf.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Controller

public class UserController {

	private final UserRepository userRepository;
	private MeterRegistry registry;

	@Autowired
	public UserController(UserRepository userRepository, MeterRegistry registry) {
		this.userRepository = userRepository;
		this.registry = registry;
	}

	@GetMapping("/index")
	public String showUserList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "index";
	}

	@GetMapping("/signup")
	public String showSignUpForm(User user) {

		setupUser(user);

		return "add-user";
	}

	private void setupUser(User user) {
		List<AccessDictionary> accessDictionaryList = new ArrayList<AccessDictionary>();
		for (int i = 0; i < 5; i++) {
			AccessDictionary accessDictionary = new AccessDictionary(i, "access" + i);
			accessDictionaryList.add(accessDictionary);
		}

		user.setAccessDictionaryList(accessDictionaryList);

		List<LabelIdPair> labelIdPairList = new ArrayList<LabelIdPair>();
		for (int i = 0; i < 4; i++) {
			LabelIdPair labelIdPair = new LabelIdPair("Label" + i, -1);
			labelIdPairList.add(labelIdPair);
		}

		user.setLabelIdPairList(labelIdPairList);

		List<AccessCategory> accessCategoryList = new ArrayList<AccessCategory>();

		for (int i = 0; i < 5; i++) {
			AccessCategory accessCategory = new AccessCategory(i, "Category" + i);
			accessCategoryList.add(accessCategory);
		}

		user.setAccessCategoryList(accessCategoryList);
	}

	@PostMapping("/adduser")
	public String addUser(@Valid User user, BindingResult result, Model model) {

		Counter addUserCounter = Counter.builder("usercontroller.add.user.count").description("Add User Count")
				.register(registry);
		addUserCounter.increment();

		if (result.hasErrors()) {
			setupUser(user);  //TODO
			return "add-user";
		}

		List<LabelIdPair> labelIdPairList = user.getLabelIdPairList();
		System.out.println("labelIdPairList==>" + labelIdPairList);
		
		List<AccessCategory> accessCategoryList = user.getAccessCategoryList();
		System.out.println("accessCategoryList==>" + accessCategoryList);

		userRepository.save(user);

		Supplier<Number> s = () -> userRepository.count();

		Gauge gauge = Gauge.builder("usercontroller.user.size", s).description("Number of Users in DB Gauge")
				.register(registry);

		return "redirect:/index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);

		return "update-user";
	}

	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}

		userRepository.save(user);

		return "redirect:/index";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {

		Counter deleteUserCounter = Counter.builder("usercontroller.delete.user.count").description("Delete User Count")
				.register(registry);
		deleteUserCounter.increment();

		User user = userRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);

		return "redirect:/index";
	}
}

package org.dragon.yunpeng.thymeleaf.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.dragon.yunpeng.thymeleaf.pojos.AccessDictionary;
import org.dragon.yunpeng.thymeleaf.pojos.LabelIdPair;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Email is mandatory")
	private String email;

	@Transient
	private List<AccessDictionary> accessDictionaryList;

	@Transient
	private List<LabelIdPair> labelIdPairList;

	public User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<AccessDictionary> getAccessDictionaryList() {
		return accessDictionaryList;
	}

	public void setAccessDictionaryList(List<AccessDictionary> accessDictionaryList) {
		this.accessDictionaryList = accessDictionaryList;
	}

	public List<LabelIdPair> getLabelIdPairList() {
		return labelIdPairList;
	}

	public void setLabelIdPairList(List<LabelIdPair> labelIdPairList) {
		this.labelIdPairList = labelIdPairList;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
	}
}

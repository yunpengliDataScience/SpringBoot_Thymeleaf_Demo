package org.dragon.yunpeng.thymeleaf.repositories;

import java.util.List;

import org.dragon.yunpeng.thymeleaf.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByName(String name);

}

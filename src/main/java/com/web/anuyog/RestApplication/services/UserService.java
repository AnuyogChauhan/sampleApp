package com.web.anuyog.RestApplication.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.web.anuyog.RestApplication.domain.User;

public interface UserService extends CrudRepository<User, Integer> {

	User save(User user);
	List<User>findByAccNum(int accNum);
}

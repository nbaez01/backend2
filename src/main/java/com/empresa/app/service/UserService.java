package com.empresa.app.service;

import java.util.List;

import com.empresa.app.entity.User;

public interface UserService {

	User save(User user);
	
	List<User> findAll();

	void deleteUser(Long id);

}

package com.empresa.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	@SuppressWarnings("unchecked")
	User save(User user);
}

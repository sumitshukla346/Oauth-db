package com.sumit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.entity.User;



public interface UserDao extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}

package com.sumit.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.sumit.dao.UserDao;
import com.sumit.entity.User;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		User user = userDao.findByUsername(userId);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public List findAll() {
		List list = new ArrayList<>();
		userDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}
	
	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		//return list;
		return userDao.findAll();
	}

	@Override
	public User addUser(User user) {
		//list.add(course);
		//return course;
		userDao.save(user);
		return user;
	}

	@Override
	public void deleteUser(long parseLong) {
		//list=this.list.stream().filter(e->e.getId()!=parseLong).collect(Collectors.toList());
		User entity=userDao.getOne((int) parseLong);
		userDao.delete(entity);
	}
}
package com.sumit.service;

import java.util.List;


import com.sumit.entity.User;

public interface UserService {

	public List<User> getUser();

	public User addUser(User user);

	public void deleteUser(long parseLong);

	

	

}

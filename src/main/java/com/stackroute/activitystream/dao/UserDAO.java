package com.stackroute.activitystream.dao;

import java.util.List;

import com.stackroute.activitystream.model.User;

public interface UserDAO {

	public boolean saveUser(User user);
	public User getUserByEmailId(String emailId);
	//we should able to delete if we have emailId
	//So the parameter should be only emailId, not complete user object.
	public boolean deleteUser(User user);
	public boolean updateUser(User user);
	public List<User> getAllUsers();
	public boolean validateUser(String emailId, String password);
	
}

package com.hzmsc.scada.service.shiro;

import java.util.List;

import com.hzmsc.scada.entity.shiro.User;

public interface UserService {
	
	public User createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	
	User findById(Long id);
	User findByUsername(String Username);
	
	List<User> findAll();
	
	public void deleteAllUsers();
	public boolean isUserExist(User user);
	
	public User login(User user);


}

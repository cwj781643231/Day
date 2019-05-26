package com.hzmsc.scada.dao.shiro;

import java.util.List;

import com.hzmsc.scada.entity.shiro.User;

public interface UserDao {
	
	public User createUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	
	public User findById(Long id);
	public User findByUsername(String Username);
	
	public List<User> findAll();

	public void deleteAllUsers();
	public boolean isUserExist(User user);
	
	public User login(User user);

}

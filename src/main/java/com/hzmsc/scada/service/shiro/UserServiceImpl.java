package com.hzmsc.scada.service.shiro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzmsc.scada.dao.shiro.UserDao;
import com.hzmsc.scada.entity.shiro.User;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User createUser(User user) {
		return userDao.createUser(user);
		// 不能重名，如果已经存在用户名一样的用户，则改为update
		/*
		if (userDao.findByUsername(user.getUsername()).equals(null)) {
			return userDao.createUser(user);
		} else {
			user.setId(userDao.findByUsername(user.getUsername()).getId());
			userDao.updateUser(user);
			return user;
		}
		*/
	}

	public void updateUser(User user) {
		// 不能重名，如果已经存在用户名一样的用户，则改为update
		if (userDao.findByUsername(user.getUsername()).equals(null)){
			userDao.updateUser(user);
		}

	}

	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.deleteUser(user);

	}

	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	public User findByUsername(String Username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(Username);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	
    public void deleteAllUsers() {
		
		userDao.deleteAllUsers();
	}

	public boolean isUserExist(User user) {
		
		return userDao.isUserExist(user);
	}

	public User login(User user) {
		
		return userDao.login(user);
	}
}

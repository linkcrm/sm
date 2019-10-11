package com.owen.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.owen.bean.User;
import com.owen.dao.UserDao;
import com.owen.service.UserService;
import com.owen.util.Page;

/**
 * Created by tengj on 2017/4/7.
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public int add(User user) {
		return userDao.add(user);
	}

	public int update(User user) {
		return userDao.update(user);
	}

	public int deleteById(int id) {
		return userDao.deleteById(id);
	}

	public User queryLearnResouceById(int id) {
		return userDao.queryLearnResouceById(id);
	}

	public Page queryLearnResouceList(Map<String, Object> params) {
		return null;
	}

	public List<User> queryAllUser() {
		return userDao.queryAllUsers();
	}

	public User queryUserByAccount(String fUserName) {
		return userDao.queryUserByAccount(fUserName);
	}

	@Override
	public int updatePw(int userId, String pw) {
		return userDao.updatePw(userId, pw);
	}

	@Override
	public List<User> queryAllUsers(String userName) {
		return userDao.queryAllUsers(userName);
	}

}

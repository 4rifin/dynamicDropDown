package com.testbaron.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testbaron.dao.UserDao;
import com.testbaron.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User findId(long id) {

		return userDao.findByUserId(id);
	}
	
	public User findByUserName(String userName) {

		return userDao.findByUserName(userName);
	}
	
	public void save(User User) {
			userDao.save(User);
	}

	public void deleteById(long id) {

		userDao.deleteById(id);
	}
	
	
	public Boolean isRecordFull(){
		List<User>listUser = (List<User>) userDao.findAll();
		if(listUser.size() >= 10){
			return true;
		}
		return false;
	}
	
}

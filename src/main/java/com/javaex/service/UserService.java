package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int joinUser(UserVo userVo) {
		return userDao.joinUser(userVo);
	}
	public UserVo loginUser(UserVo userVo) {
		return userDao.loginUser(userVo);
	}
	public int idCheck(String id) {
		return userDao.idCheck(id);
	}
	public UserVo getUser(String id) {
		return userDao.getUser(id);
	}
	public int makeBlog(UserVo userVo) {
		return userDao.makeBlog(userVo);
	}

}

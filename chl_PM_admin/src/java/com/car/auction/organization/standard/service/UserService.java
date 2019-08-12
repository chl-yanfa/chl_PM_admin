package com.car.auction.organization.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.organization.dao.UserDao;
import com.car.auction.organization.entity.User;
import com.car.auction.sys.dto.UserInfoDto;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	public List<UserInfoDto> getAllUser() {
		return userDao.getAllUser();
	}
	

}

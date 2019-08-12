package com.car.auction.organization.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.organization.entity.User;
import com.car.auction.sys.dto.UserInfoDto;


@Component
public class UserDao {
	
	@Autowired
	private BaseDao baseDao;
	public User getUser(String id) {
		return baseDao.findOne("mapper.standard.UserMapper.getUser",id);
	}
	/**
	 * getAllUser(获取用户)
	 * @param userInfoDto
	 * @return
	 */
	public List<UserInfoDto> getAllUser() {
		return baseDao.findList("mapper.standard.UserMapper.getAllUser");
	}
}

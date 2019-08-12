package com.car.auction.organization.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.organization.entity.PMNumber;


@Component
public class PMNumberDao {
	
	@Autowired
	private BaseDao baseDao;
	
	public PMNumber getPMNumberByType(Integer type) {
		return baseDao.findOne("mapper.standard.PMNumberMapper.getPMNumberByType",type);
	}
	public int updatePMNumberById(Integer id) {
		return baseDao.update("mapper.standard.PMNumberMapper.updatePMNumberById", id);
	}
}

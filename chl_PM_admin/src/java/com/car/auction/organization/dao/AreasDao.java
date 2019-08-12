package com.car.auction.organization.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.organization.entity.Areas;


@Component
public class AreasDao {
	
	@Autowired
	private BaseDao baseDao;
	public List<Areas> getAreas() {
		return baseDao.findList("mapper.standard.AreasMapper.getAreas");
	}
}

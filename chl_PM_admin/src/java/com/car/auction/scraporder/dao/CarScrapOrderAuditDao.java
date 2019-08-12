package com.car.auction.scraporder.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.scraporder.entity.CarScrapOrderAudit;

@Component
public class CarScrapOrderAuditDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public int addCarScrapOrderAudit(CarScrapOrderAudit audit) {
		return baseDao.create("mapper.standard.CarScrapOrderAuditMapper.addCarScrapOrderAudit", audit);
	}
	
	
}

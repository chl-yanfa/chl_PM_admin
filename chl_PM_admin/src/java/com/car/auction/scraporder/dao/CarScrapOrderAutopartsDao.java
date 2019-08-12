package com.car.auction.scraporder.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.scraporder.entity.CarScrapOrderAutoparts;

@Component
public class CarScrapOrderAutopartsDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public List<String> getAutopartsIdByAuctionId(String auctionId) {
		return baseDao.findList("mapper.standard.CarScrapOrderAutopartsMapper.getAutopartsIdByAuctionId", auctionId);
	}
	
	public int updateCarScrapOrderAutoparts(CarScrapOrderAutoparts carScrapOrderAutoparts) {
		return baseDao.update("mapper.standard.CarScrapOrderAutopartsMapper.updateCarScrapOrderAutoparts", carScrapOrderAutoparts);
	}
	
	
}

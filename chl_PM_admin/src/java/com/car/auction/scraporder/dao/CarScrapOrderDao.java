package com.car.auction.scraporder.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.scraporder.dto.CarScrapOrderAutopartsDto;
import com.car.auction.scraporder.dto.CarScrapOrderDto;
import com.car.auction.scraporder.entity.CarScrapOrder;
import com.car.auction.scraporder.vo.CarScrapOrderVo;

@Component
public class CarScrapOrderDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public List<CarScrapOrderDto> getCarScrapOrderList(Integer page,Integer rows,CarScrapOrderVo vo) {
		return baseDao.findList("mapper.standard.CarScrapOrderMapper.getCarScrapOrderList",
				vo,page,rows);
	}
	public int getCarScrapOrderListTotal(CarScrapOrderVo vo) {
		return baseDao.findOne("mapper.standard.CarScrapOrderMapper.getCarScrapOrderListTotal", vo);
	}
	
	public List<CarScrapOrderAutopartsDto> getCarScrapOrderAutopartsList(Integer page,Integer rows,CarScrapOrderVo vo) {
		return baseDao.findList("mapper.standard.CarScrapOrderMapper.getCarScrapOrderAutopartsList",
				vo,page,rows);
	}
	public int getCarScrapOrderAutopartsListTotal(CarScrapOrderVo vo) {
		return baseDao.findOne("mapper.standard.CarScrapOrderMapper.getCarScrapOrderAutopartsListTotal", vo);
	}
	public String getOrderIdByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.CarScrapOrderMapper.getOrderIdByAuctionId", auctionId);
	}
	public int updateCarScrapOrder(CarScrapOrder carScrapOrder) {
		return baseDao.update("mapper.standard.CarScrapOrderMapper.updateCarScrapOrder", carScrapOrder);
	}
	
}

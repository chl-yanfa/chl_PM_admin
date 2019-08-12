package com.car.auction.paimai.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.paimai.entity.TraIndent;

@Component
public class AuctionResultDao {

	@Autowired 
	private BaseDao baseDao;
	
	public List<TraIndent> findTraIndent(TraIndent traIndent){
		return baseDao.findList("mapper.standard.TraIndentMapper.findTraIndent", traIndent);
	}
	
	public int updateTraIndent(TraIndent traIndent){
		return baseDao.update("mapper.standard.TraIndentMapper.updateTraIndent", traIndent);
	}
	
	public TraIndent getConfirmSendData(String orderId){
		return baseDao.findOne("mapper.standard.TraIndentMapper.getConfirmSendData", orderId);
	}
}

package com.car.auction.paimai.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.paimai.entity.HighestCustomerInfo;
import com.car.auction.paimai.entity.OfferWaterInfo;

@Component
public class OfferWaterDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public List<OfferWaterInfo> getOfferWaterListByOrderId(String auctionSetId){
		return baseDao.findList("mapper.standard.OfferWaterMapper.getOfferWaterListByOrderId", auctionSetId);
	}
	public int getOfferWaterTotalByOrderId(String auctionSetId){
		return baseDao.findOne("mapper.standard.OfferWaterMapper.getOfferWaterTotalByOrderId", auctionSetId);
	}
	/**
	 * getBidCountByOrderId() 出价人数
	 * @param  
	 * @return 
	 */
	public Integer getBidCountByOrderId(String auctionSetId){
		return baseDao.findOne("mapper.standard.OfferWaterMapper.getBidCountByOrderId", auctionSetId);
	}
	
	/**
	 * getHighestCustomerByOrderId() 最高出价客户信息
	 * @param  
	 * @return 
	 */
	public HighestCustomerInfo getHighestCustomerByOrderId(String auctionSetId){
		return baseDao.findOne("mapper.standard.OfferWaterMapper.getHighestCustomerByOrderId", auctionSetId);
	}
}

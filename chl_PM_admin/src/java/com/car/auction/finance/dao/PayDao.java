package com.car.auction.finance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.finance.entity.Pay;


/**
 * 项目名称：SDIC-Inner
 * 类名称：PayDao
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月18日 下午7:13:48
 */
@Component
public class PayDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public Pay getMemberPay(Pay pay) {
		return baseDao.findOne("mapper.standard.PayMapper.getMemberPay", pay);
	}
	
	public int addPay(Pay pay) {
		return baseDao.create("mapper.standard.PayMapper.addPay", pay);
	}
	public Pay getPayByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.PayMapper.getPayByAuctionSetId", auctionSetId);
	}
	public Pay getRefundByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.PayMapper.getRefundByAuctionSetId", auctionSetId);
	}
	public int updatePaySelective(Pay pay) {
		return baseDao.update("mapper.standard.PayMapper.updatePaySelective", pay);
	}
}

package com.car.auction.auction.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.BaseDao;


/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionSetDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午7:28:40
 * @version
 */
@Component
public class AuctionSetDao {
	
	@Autowired
	private BaseDao baseDao;
	
	public AuctionSet getAuctionSetById(String id) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getAuctionSetById", id);
	}
	
	public AuctionSet getToDealAuctionSet(String id) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getToDealAuctionSet", id);
	}
	
	public List<AuctionSet> getAuctionSetByPmhId(String pmhId) {
		return baseDao.findList("mapper.standard.AuctionSetMapper.getAuctionSetByPmhId", pmhId);
	}
	/**
	 * getAuctionSetByAuctionId()
	 */
	public AuctionSet getAuctionSetByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getAuctionSetByAuctionId", auctionId);
	}
	
	public AuctionSet getNextAuctionSet(String pmhId) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getNextAuctionSet", pmhId);
	}
	
	public AuctionSet getAuctioningSet(String pmhId) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getAuctioningSet", pmhId);
	}
	
	public Integer getPmOrderByPmhId(String pmhId) {
		return baseDao.findOne("mapper.standard.AuctionSetMapper.getPmOrderByPmhId", pmhId);
	}
	public int addAuctionSet(AuctionSet auctionSet) {
		return baseDao.create("mapper.standard.AuctionSetMapper.addAuctionSet", auctionSet);
	}
	public int updateAuctionSet(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.updateAuctionSet", auctionSet);
	}
	public int updateAuctionSetSelective(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.updateAuctionSetSelective", auctionSet);
	}
	public int updateAuctionSetPublish(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.updateAuctionSetPublish", auctionSet);
	}
	public int updateAuctionSetAdd(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.updateAuctionSetAdd", auctionSet);
	}
	public int updatePmOrder(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.updatePmOrder", auctionSet);
	}
	public int clearAuctionSetPmh(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.clearAuctionSetPmh", auctionSet);
	}
	/**
	 * enterAuctionSet() 成交或流拍 更新结果
	 * @param  
	 * @return 
	 */
	public int enterAuctionSet(AuctionSet auctionSet) {
		return baseDao.update("mapper.standard.AuctionSetMapper.enterAuctionSet", auctionSet);
	}
	
}

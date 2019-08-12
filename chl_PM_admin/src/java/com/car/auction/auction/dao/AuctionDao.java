package com.car.auction.auction.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.auction.entity.Auction;
import com.car.auction.common.BaseDao;

/**
 * 
 * 项目名称：SDIC-Inner
 * 类名称：AuctionDao
 * 类描述：拍品信息
 * 创建人：
 * 创建时间： 
 * 修改人：
 * 修改时间：
 * 修改备注： 
 * @version 
 *
 */
@Component
public class AuctionDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 查询拍品信息
	 */
	public Auction getAuctionById(String id) {
		Auction auction = baseDao.findOne("mapper.standard.AuctionMapper.getAuctionById", id);
		if(auction!=null && auction.getAuctionType() == 0) {
			String orderId = baseDao.findOne("mapper.standard.CarScrapOrderMapper.getOrderIdByAuctionId", id);
			if(StringUtils.isNotBlank(orderId)) {
				auction.setScrapOrderId(orderId);
			}
		}
		return auction;
	}
	/**
	 * 新增拍品信息
	 */
	public void insertAuction(Auction auction) {
		baseDao.create("mapper.standard.AuctionMapper.insertAuction", auction);
	}
	/**
	 * 更改拍品状态
	 */
	public void updateAuctionState(Auction auction){
		baseDao.update("mapper.standard.AuctionMapper.updateAuctionState",auction);
	}
	/**
	 * 编辑拍品信息
	 */
	public void updateAuctionSel(Auction auction) {
		baseDao.update("mapper.standard.AuctionMapper.updateAuctionSel", auction);
	}
	
	public void updateAuction(Auction auction) {
		baseDao.update("mapper.standard.AuctionMapper.updateAuction", auction);
	}
	
}

package com.car.auction.auction.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.auction.dto.AuctionDto;
import com.car.auction.auction.entity.AuctionSave;
import com.car.auction.auction.entity.AuctionScrap;
import com.car.auction.auction.entity.AuctionWhole;
import com.car.auction.common.BaseDao;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionWholeDao
 * 类描述：车辆列表
 * 创建人：张婉欣
 * 创建时间： 2018年8月13日 下午4:35:10
 * @version
 */
@Component
public class AuctionWholeDao {
	
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询车辆列表--分页
	 */
	public List<AuctionWhole> getAuctionWholeListByPage(AuctionDto auction) {
		int start = 0;
		int length = 10;
		try {
			start =auction.getPage();
		} catch (Exception e) {}
		try {
			length =auction.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.AuctionWholeMapper.getAuctionWholeListByPage",
				auction,start,length);
		
	}
	public int getAuctionWholeListTotal(AuctionDto auction) {
		return baseDao.findOne("mapper.standard.AuctionWholeMapper.getAuctionWholeListTotal", auction);
	}
	public List<AuctionWhole> getAuctionFinishListByPage(AuctionDto auction) {
		int start = 0;
		int length = 10;
		try {
			start =auction.getPage();
		} catch (Exception e) {}
		try {
			length =auction.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.AuctionWholeMapper.getAuctionFinishListByPage",
				auction,start,length);
		
	}
	public int getAuctionFinishListTotal(AuctionDto auction) {
		return baseDao.findOne("mapper.standard.AuctionWholeMapper.getAuctionFinishListTotal", auction);
	}
	/**
	 * 查询车辆列表--TAB
	 */
	public List<Map<String, String>> getAuctionWholeTab(AuctionDto auction) {
		return baseDao.findList("mapper.standard.AuctionWholeMapper.getAuctionWholeTab", auction);
	}
	/**
	 * 查询暂存列表信息--分页
	 */
	public List<AuctionSave> getAuctionSaveListByPage(AuctionDto auction) {
		int start = 0;
		int length = 10;
		try {
			start =auction.getPage();
		} catch (Exception e) {}
		try {
			length =auction.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.AuctionWholeMapper.getAuctionSaveListByPage",
				auction,start,length);
		
	}
	public int getAuctionSaveListTotal(AuctionDto auction) {
		return baseDao.findOne("mapper.standard.AuctionWholeMapper.getAuctionSaveListTotal", auction);
	}
	/**
	 * 查询报废车辆列表信息--分页
	 */
	public List<AuctionScrap> getScrapCarListByPage(AuctionDto auction) {
		int start = 0;
		int length = 10;
		try {
			start =auction.getPage();
		} catch (Exception e) {}
		try {
			length =auction.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.AuctionWholeMapper.getScrapCarListByPage",
				auction,start,length);
		
	}
	public int getScrapCarListTotal(AuctionDto auction) {
		return baseDao.findOne("mapper.standard.AuctionWholeMapper.getScrapCarListTotal", auction);
	}
	public List<Map<String, String>> getScrapCarTab(AuctionDto auction) {
		List<Map<String, String>> findList 
			= baseDao.findList("mapper.standard.AuctionWholeMapper.getScrapCarListTab", auction);
		
		return findList;
	}
}

package com.car.auction.paimai.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.paimai.dto.PaimaiDto;
import com.car.auction.paimai.entity.AuctionCar;
import com.car.auction.paimai.entity.PmAuction;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiWholeDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月16日 下午2:18:26
 * @version
 */
@Component
public class PaimaiWholeDao {
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询车辆列表--分页
	 */
	public List<PmAuction> getPmAuctionListByPage(PaimaiDto paimai) {
		int start = 0;
		int length = 10;
		try {
			start =paimai.getPage();
		} catch (Exception e) {}
		try {
			length =paimai.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.PaimaiWholeMapper.getPmAuctionListByPage",
				paimai,start,length);
		
	}
	public int getPmAuctionListTotal(PaimaiDto paimai) {
		return baseDao.findOne("mapper.standard.PaimaiWholeMapper.getPmAuctionListTotal", paimai);
	}
	public List<AuctionCar> getAuctionCarList(String pmhId) {
		return baseDao.findList("mapper.standard.PaimaiWholeMapper.getAuctionCarList",pmhId);
	}
}

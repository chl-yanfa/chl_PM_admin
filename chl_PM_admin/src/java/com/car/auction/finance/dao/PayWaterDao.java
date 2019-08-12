package com.car.auction.finance.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.finance.entity.PayWater;


/**
 * 项目名称：	SDIC-Inner
 * 类名称：	PayWaterDao
 * 类描述：
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月14日 下午1:52:45
 */
@Component
public class PayWaterDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public int addPayWater(PayWater water) {
		return baseDao.create("mapper.standard.PayWaterMapper.addPayWater", water);
	}
	public PayWater getPayWaterById(String id) {
		return baseDao.findOne("mapper.standard.PayWaterMapper.getPayWaterById", id);
	}
	
	public int editPayWater(PayWater water) {
		return baseDao.update("mapper.standard.PayWaterMapper.editPayWater", water);
	}
}

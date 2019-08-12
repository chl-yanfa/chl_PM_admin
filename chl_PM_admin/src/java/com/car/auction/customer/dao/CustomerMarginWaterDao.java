package com.car.auction.customer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.customer.dto.CustomerMarginWaterDto;
import com.car.auction.customer.entity.CustomerMarginWater;
import com.car.auction.customer.vo.CustomerMarginWaterVo;


/**
 * 项目名称：	SDIC-Inner
 * 类名称：	CustomerMarginWaterDao
 * 类描述：
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月12日 下午2:14:36
 */
@Component
public class CustomerMarginWaterDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	
	public CustomerMarginWater getCustomerMarginWaterById(String id) {
		return baseDao.findOne("mapper.standard.CustomerMarginWaterMapper.getCustomerMarginWaterById", id);
	}
	
	public CustomerMarginWater getCustomerMarginWaterByParam(CustomerMarginWater param) {
		return baseDao.findOne("mapper.standard.CustomerMarginWaterMapper.getCustomerMarginWaterByParam", param);
	}
	
	public List<CustomerMarginWaterDto> getMarginWaterList(Integer page,Integer rows,CustomerMarginWaterVo vo) {
		return baseDao.findList("mapper.standard.CustomerMarginWaterMapper.getMarginWaterList", vo,page,rows);
	}
	
	public Integer getMarginWaterListTotal(CustomerMarginWaterVo vo) {
		return baseDao.findOne("mapper.standard.CustomerMarginWaterMapper.getMarginWaterListTotal", vo);
	}
	
	public int addCustomerMarginWater(CustomerMarginWater water) {
		return baseDao.create("mapper.standard.CustomerMarginWaterMapper.addCustomerMarginWater", water);
	}
	/**
	 * updateRefundState() 更新退款状态
	 * @param  
	 * @return 
	 */
	public int updateRefundState(String id) {
		return baseDao.update("mapper.standard.CustomerMarginWaterMapper.updateRefundState", id);
	}
	
}

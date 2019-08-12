package com.car.auction.customer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.customer.entity.CustomerMargin;


/**
 * 项目名称：	SDIC-Inner
 * 类名称：	CustomerMarginDao
 * 类描述：
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月12日 下午2:12:35
 */
@Component
public class CustomerMarginDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public CustomerMargin getCustomerMarginById(String id) {
		return baseDao.findOne("mapper.standard.CustomerMarginMapper.getCustomerMarginById", id);
	}
	
	public int addCustomerMargin(CustomerMargin customerMargin) {
		return baseDao.create("mapper.standard.CustomerMarginMapper.addCustomerMargin", customerMargin);
	}
	
	public int updateCustomerMarginSelective(CustomerMargin customerMargin) {
		return baseDao.update("mapper.standard.CustomerMarginMapper.updateCustomerMarginSelective", customerMargin);
	}
	
	/**
	 * thawCustomerMargin() 解冻参拍保证金
	 * @param  
	 * @return 
	 */
	public int thawCustomerMargin(CustomerMargin customerMargin) {
		return baseDao.update("mapper.standard.CustomerMarginMapper.thawCustomerMargin", customerMargin);
	}

	/**
	 * chargeCustomerMargin() 充值参拍保证金
	 * @param  
	 * @return 
	 */
	public int chargeCustomerMargin(CustomerMargin customerMargin) {
		return baseDao.update("mapper.standard.CustomerMarginMapper.chargeCustomerMargin", customerMargin);
	}
	
	/**
	 * refundCustomerMargin() 退款参拍保证金
	 * @param  
	 * @return 
	 */
	public int refundCustomerMargin(CustomerMargin customerMargin) {
		return baseDao.update("mapper.standard.CustomerMarginMapper.refundCustomerMargin", customerMargin);
	}
	
}

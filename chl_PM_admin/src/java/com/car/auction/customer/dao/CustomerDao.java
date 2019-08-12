package com.car.auction.customer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.customer.entity.Customer;
import com.car.auction.customer.vo.CustomerVo;

@Component
public class CustomerDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * getCustomerList
	 * @param param
	 * @return
	 */
	public List<Customer> getCustomerList(Integer page,Integer rows,CustomerVo vo){
		return baseDao.findList("mapper.standard.CustomerMapper.getCustomerList", vo,page,rows);
	}
	
	/**
	 * getCustomerListTotal
	 * @param param
	 * @return
	 */
	public int getCustomerListTotal(CustomerVo param){
		return baseDao.findOne("mapper.standard.CustomerMapper.getCustomerListTotal", param);
	}
	
	/**
	 * 根据id获取用户信息
	 * @param uid
	 * @return
	 */
	public Customer getCustomerById(String id){
		return baseDao.findOne("mapper.standard.CustomerMapper.getCustomerById", id);
	}
	
	/**
	 * 根据id获取用户信息
	 * @param uid
	 * @return
	 */
	public Integer updateCustomerSelective(Customer param){
		return baseDao.update("mapper.standard.CustomerMapper.updateCustomerSelective", param);
	}

}

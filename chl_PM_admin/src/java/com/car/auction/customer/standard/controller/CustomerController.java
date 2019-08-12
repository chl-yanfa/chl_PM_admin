package com.car.auction.customer.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.customer.dto.CustomerMarginWaterDto;
import com.car.auction.customer.entity.Customer;
import com.car.auction.customer.entity.CustomerMarginWater;
import com.car.auction.customer.standard.service.CustomerService;
import com.car.auction.customer.vo.CustomerMarginWaterVo;
import com.car.auction.customer.vo.CustomerVo;

/**
 * 项目名称：	sdicPM_interface
 * 类名称：	AdminController
 * 类描述：	
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月6日17:08:55
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController{
	@Autowired
	private CustomerService customerService;
	
	/**
	 * getCustomerList() 获取用户列表
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/getCustomerList", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<Customer> getCustomerList(
    		@RequestParam(value="page",required=false,defaultValue="1")Integer page,
			@RequestParam(value="rows",required=false,defaultValue="10")Integer rows,
			CustomerVo vo){
		return customerService.getCustomerList(page,rows,vo);
	}
	
	/**
	 * getCustomerList() 获取用户列表
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/getMarginWaterList", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<CustomerMarginWaterDto> getMarginWaterList(
    		@RequestParam(value="page",required=false,defaultValue="1")Integer page,
			@RequestParam(value="rows",required=false,defaultValue="10")Integer rows,
			CustomerMarginWaterVo vo){
		return customerService.getMarginWaterList(page,rows,vo);
	}
	
	
	/**
	 * editCustomer() 编辑用户信息
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/editCustomer", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> editCustomer(CustomerVo vo){
		return customerService.editCustomer(vo);
	}
	
	/**
	 * resetPwd() 重置密码
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> resetPwd(CustomerVo vo){
		return customerService.resetPwd(vo);
	}
	
	/**
	 * frozenAccount() 冻结账户
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/frozenAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> frozenAccount(String id){
		return customerService.frozenAccount(id);
	}
	
	/**
	 * thawAccount() 解冻账户
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/thawAccount", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> thawAccount(String id){
		return customerService.thawAccount(id);
	}
	
	/**
	 * chargeMargin() 充值保证金
	 * @param  request
	 * @return 
	 */
	@RequestMapping(value = "/chargeMargin", method = RequestMethod.POST)
    @ResponseBody
    public ResultDTO<String> chargeMargin(CustomerMarginWater water){
		return customerService.chargeMargin(water);
	}
	/**
	 * applyRefund
	 * 申请退款
	 */
	@RequestMapping(value = "/applyRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> applyRefund(String id,String mid,String operatorCash) {
		return customerService.applyRefund(id,mid,operatorCash);
	}
	
	
}

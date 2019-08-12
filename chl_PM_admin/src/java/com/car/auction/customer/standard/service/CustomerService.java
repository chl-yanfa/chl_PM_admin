package com.car.auction.customer.standard.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.common.CommonUtils;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.customer.dao.CustomerDao;
import com.car.auction.customer.dao.CustomerMarginDao;
import com.car.auction.customer.dao.CustomerMarginWaterDao;
import com.car.auction.customer.dto.CustomerMarginWaterDto;
import com.car.auction.customer.entity.Customer;
import com.car.auction.customer.entity.CustomerMargin;
import com.car.auction.customer.entity.CustomerMarginWater;
import com.car.auction.customer.vo.CustomerMarginWaterVo;
import com.car.auction.customer.vo.CustomerVo;


/**
 * 项目名称：	SDIC-Inner
 * 类名称：	CustomerService
 * 类描述：
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月16日 下午6:20:41
 */
@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CustomerMarginDao customerMarginDao;
	
	@Autowired
	private CustomerMarginWaterDao customerMarginWaterDao;
	
	/**
	 * getCustomerList() 获取用户列表
	 * @param  
	 * @return 
	 */
	public ResultDTO<Customer> getCustomerList(Integer page,Integer rows,CustomerVo vo){
		ResultDTO<Customer> res = new ResultDTO<Customer>();
		List<Customer> customerList = customerDao.getCustomerList(page,rows,vo);
		int total = customerDao.getCustomerListTotal(vo);
		res.setRows(customerList);
		res.setTotal(total);
		res.setReturnCode(RtnMsgConstants.RETURN_CODE_SUCCESS);
		res.setReturnMsg("成功");
		return res;
	}
	public ResultDTO<CustomerMarginWaterDto> getMarginWaterList(Integer page,Integer rows,CustomerMarginWaterVo vo) {
		ResultDTO<CustomerMarginWaterDto> res = new ResultDTO<CustomerMarginWaterDto>();
		List<CustomerMarginWaterDto> list = customerMarginWaterDao.getMarginWaterList(page,rows,vo);
		int total = customerMarginWaterDao.getMarginWaterListTotal(vo); 
		res.setRows(list);
		res.setTotal(total);
		return res;
	}
	
	/**
	 * editCustomer() 编辑用户信息
	 * @param customer
	 * @return
	 */
	@Transactional
	public ResultDTO<String> editCustomer(CustomerVo vo){
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(vo.getId())||StringUtils.isBlank(vo.getUserName())
				||StringUtils.isBlank(vo.getPhone())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customerDB = customerDao.getCustomerById(vo.getId());
		if(customerDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customer = new Customer();
		customer.setId(vo.getId());
		customer.setUserName(vo.getUserName());
		customer.setPhone(vo.getPhone());
		customer.setOperatortime(new Date());
		customerDao.updateCustomerSelective(customer);
		return res;
	}
	
	/**
	 * resetPwd() 重置密码
	 * @param vo
	 * @return
	 */
	@Transactional
	public ResultDTO<String> resetPwd(CustomerVo vo){
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(vo.getId())||StringUtils.isBlank(vo.getPassword())
				||StringUtils.isBlank(vo.getPassword2())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		if(!StringUtils.equals(vo.getPassword(), vo.getPassword2())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PWD_NOT_SAME);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PWD_NOT_SAME); 
			return res;
		}
		Customer customerDB = customerDao.getCustomerById(vo.getId());
		if(customerDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		String createPassword = CommonUtils.createPassword(vo.getPassword(), salt, Constants.HASHITERATIONS);
		Customer customer = new Customer();
		customer.setId(vo.getId());
		customer.setPasswordSalt(salt);
		customer.setPassword(createPassword);
		customer.setOperatortime(new Date());
		customerDao.updateCustomerSelective(customer);
		return res;
	}
	
	/**
	 * frozenAccount() 冻结账户
	 * @param id
	 * @return
	 */
	@Transactional
	public ResultDTO<String> frozenAccount(String id){
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(id)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customerDB = customerDao.getCustomerById(id);
		if(customerDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customer = new Customer();
		customer.setId(id);
		customer.setStatus("3");
		customer.setOperatortime(new Date());
		customerDao.updateCustomerSelective(customer);
		return res;
	}
	/**
	 * thawAccount() 解冻账户
	 * @param id
	 * @return
	 */
	@Transactional
	public ResultDTO<String> thawAccount(String id){
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(id)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customerDB = customerDao.getCustomerById(id);
		if(customerDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL); 
			return res;
		}
		Customer customer = new Customer();
		customer.setId(id);
		customer.setStatus("1");
		customer.setOperatortime(new Date());
		customerDao.updateCustomerSelective(customer);
		return res;
	}
	/**
	 * chargeMargin() 充值保证金
	 */
	@Transactional
	public ResultDTO<String> chargeMargin(CustomerMarginWater water) {
		ResultDTO<String> result = new ResultDTO<String>();
		if(water == null||null == water.getChargeAmount()
				||StringUtils.isBlank(water.getMid())) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return result;
		}
		/**
		 * 新增保证金流水
		 */
		water.setId(UUIDUtil.getUuid());
		water.setOperatorCash("+"+water.getChargeAmount().intValue());
		water.setMemo("参拍保证金充值");
		water.setOperatorType("4");
		water.setCreateUser(null);
		customerMarginWaterDao.addCustomerMarginWater(water);
		/**
		 * 更新保证金表,余额增加
		 */
		String mid = water.getMid();
		CustomerMargin customerMargin = customerMarginDao.getCustomerMarginById(mid);
		if(customerMargin!=null) {
			customerMargin.setWalletPledge(water.getChargeAmount());
			customerMargin.setUpdateUser(null);
			customerMarginDao.chargeCustomerMargin(customerMargin);
		}else {
			customerMargin = new CustomerMargin();
			customerMargin.setId(mid);
			customerMargin.setWalletPledge(water.getChargeAmount());
			customerMargin.setIsDelete(0);
			customerMargin.setCreateTime(new Date());
			customerMarginDao.addCustomerMargin(customerMargin);
		}
		return result;
	}
	/**
	 * applyRefund()  申请退款
	 */
	@Transactional
	public ResultDTO<String> applyRefund(String id,String mid,String operatorCash) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(mid)
				||StringUtils.isBlank(operatorCash)
				||StringUtils.isBlank(id)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		CustomerMargin customerMargin = customerMarginDao.getCustomerMarginById(mid);
		if(null == customerMargin) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		BigDecimal operatorCashB = null;
		try {
			operatorCash=operatorCash.substring(1, operatorCash.length());
			operatorCashB = new BigDecimal(operatorCash);
		} catch (Exception e) {
			operatorCash ="0";
			operatorCashB = new BigDecimal("0.00");
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_ERROR4);
			res.setReturnMsg("计算出错");
			return res;
		} 
		BigDecimal walletPledge = customerMargin.getWalletPledge();
		int compareTo = walletPledge.compareTo(operatorCashB);
		if(compareTo<0) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("余额不足");
			return res;
		}
		/**
		 * 新增保证金流水
		 */
		CustomerMarginWater water =new CustomerMarginWater();
		water.setId(UUIDUtil.getUuid());
		water.setMid(mid);
		water.setOperatorCash("-"+operatorCash);
		water.setMemo("参拍保证金退款");
		water.setOperatorType("5");
		water.setCreateUser(null);
		customerMarginWaterDao.addCustomerMarginWater(water);
		customerMarginWaterDao.updateRefundState(id);
		/**
		 * 更新保证金表,余额减少
		 */
		if(customerMargin!=null) {
			customerMargin.setWalletPledge(operatorCashB);
			customerMargin.setUpdateUser(null);
			customerMarginDao.refundCustomerMargin(customerMargin);
		}
		return res;
	}
	
}

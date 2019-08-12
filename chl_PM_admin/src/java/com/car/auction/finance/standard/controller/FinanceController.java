package com.car.auction.finance.standard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.finance.dto.FinanceDto;
import com.car.auction.finance.entity.FinanceEntity;
import com.car.auction.finance.entity.Pay;
import com.car.auction.finance.standard.service.FinanceService;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AftersaleController
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月11日 下午1:33:02
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceController extends BaseController{
	
	@Autowired
	private FinanceService financeService;
	
	/**
	 * getFinanceList
	 */
	@RequestMapping(value = "/getFinanceList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<FinanceEntity> getFinanceList(HttpSession session,FinanceDto finance) {
		return financeService.getFinanceList(session, finance);
	}
	/**
	 * getFinanceTab
	 */
	@RequestMapping(value = "/getFinanceTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getFinanceTab(HttpSession session, FinanceDto finance) {
		return financeService.getFinanceTab(session,finance);
	}
	
	/**
	 * addPay
	 * 新增付款
	 */
	@RequestMapping(value = "/addPay", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addPay(HttpSession session, Pay pay) {
		return financeService.addPay(session,pay);
	}
	/**
	 * getPay
	 * 获取付款
	 */
	@RequestMapping(value = "/getPay", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Pay> getPay(String auctionSetId) {
		return financeService.getPay(auctionSetId);
	}
	/**
	 * getRefund
	 * 获取退款
	 */
	@RequestMapping(value = "/getRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Pay> getRefund(String auctionSetId) {
		return financeService.getRefund(auctionSetId);
	}
}

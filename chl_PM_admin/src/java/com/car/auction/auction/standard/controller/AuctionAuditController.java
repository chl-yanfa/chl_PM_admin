package com.car.auction.auction.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.entity.AuctionAudit;
import com.car.auction.auction.standard.service.AuctionAuditService;
import com.car.auction.common.ResultDTO;


/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionAuditController
 * 类描述：拍品，手续，入库信息审核
 * 创建人：张婉欣
 * 创建时间： 2018年8月2日 上午11:03:05
 * @version
 */
@Controller
@RequestMapping(value = "/auctionAudit")
public class AuctionAuditController{
	
	@Autowired
	private AuctionAuditService auctionAuditService;

	/**
	 * getAuctionAuditById(根据id查询拍品审核信息)
	 */
	@RequestMapping(value = "/getAuctionAuditById", method = RequestMethod.POST)
	@ResponseBody
	public AuctionAudit getAuctionAuditById(String id) {
		return auctionAuditService.getAuctionAuditById(id);
	}
	/**
	 * addAuctionAudit(新增拍品审核信息)
	 */
	@RequestMapping(value = "/addAuctionAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAuctionAudit(AuctionAudit auctionAudit) {
		return auctionAuditService.addAuctionAudit(auctionAudit);
	}

}

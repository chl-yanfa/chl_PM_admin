package com.car.auction.auction.standard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.auction.standard.service.AuctionSetService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;



@Controller
@RequestMapping(value = "/auctionSet")
public class AuctionSetController extends BaseController{
	
	@Autowired
	private AuctionSetService auctionSetService;

	
	@RequestMapping(value = "/getAuctionSetByAuctionId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionSet> getAuctionSetByAuctionId(String auctionId) {
		return auctionSetService.getAuctionSetByAuctionId(auctionId);
	}
	
	@RequestMapping(value = "/getAuctionSetById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionSet> getAuctionSetById(String auctionSetId) {
		return auctionSetService.getAuctionSetById(auctionSetId);
	}
	/**
	 * addAuctionSet
	 * 新增参拍设置
	 */
	@RequestMapping(value = "/addAuctionSet", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAuctionSet(HttpServletRequest request,AuctionSet auctionSet) {
		return auctionSetService.addAuctionSet(request,auctionSet);
	}
	
	
	/**
	 * auctionAgainSet  再次拍卖设置
	 */
	@RequestMapping(value = "/auctionAgainSet", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> auctionAgainSet(HttpServletRequest request,AuctionSet auctionSet) {
		return auctionSetService.auctionAgainSet(request,auctionSet);
	}
	/**
	 * auctionAgainSetTC
	 * 新增再次参拍设置(退货后)
	 */
	@RequestMapping(value = "/auctionAgainSetTC", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> auctionAgainSetTC(HttpServletRequest request,AuctionSet auctionSet) {
		return auctionSetService.auctionAgainSetTC(request,auctionSet);
	}
}

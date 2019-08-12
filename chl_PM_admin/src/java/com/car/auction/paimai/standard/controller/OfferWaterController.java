package com.car.auction.paimai.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.ResultDTO;
import com.car.auction.paimai.entity.HighestCustomerInfo;
import com.car.auction.paimai.entity.OfferWaterInfo;
import com.car.auction.paimai.standard.service.OfferWaterService;



@Controller
@RequestMapping(value = "/offerwater")
public class OfferWaterController {
	
	@Autowired
	private OfferWaterService offerWaterService;

	
	@RequestMapping(value = "/getOfferWaterList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<OfferWaterInfo> getOfferWaterList(String auctionSetId){
		return offerWaterService.getOfferWaterListByOrderId(auctionSetId);
	}
	
	@RequestMapping(value = "/getBidCountByOrderId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Integer> getBidCountByOrderId(String auctionSetId){
		return offerWaterService.getBidCountByOrderId(auctionSetId);
	}
	
	@RequestMapping(value = "/getHighestCustomerByOrderId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<HighestCustomerInfo> getHighestCustomerByOrderId(String auctionSetId){
		return offerWaterService.getHighestCustomerByOrderId(auctionSetId);
	}


}

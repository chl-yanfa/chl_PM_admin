package com.car.auction.auction.standard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.dto.AuctionDto;
import com.car.auction.auction.dto.EntityDto;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionSave;
import com.car.auction.auction.entity.AuctionScrap;
import com.car.auction.auction.entity.AuctionWhole;
import com.car.auction.auction.standard.service.AuctionService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;

/**
 * 
 * 项目名称：SDIC-Inner
 * 类名称：AuctionController 
 * 类描述：报价管理查询 创建人： 
 * 创建时间： 
 * 修改人: 
 * 修改时间： 
 * 修改备注：
 * 
 * @version
 *
 */
@Controller
@RequestMapping(value = "/auction")
public class AuctionController extends BaseController{
	
	@Autowired
	private AuctionService auctionService;

	/**
	 * getAuctionById(根据id查看拍品信息)
	 */
	@RequestMapping(value = "/getAuctionById", method = RequestMethod.POST)
	@ResponseBody
	public Auction getAuctionById(String id) {
		return auctionService.getAuctionById(id);
	}
	/**
	 * getVPWById(根据id查看拍品，手续，仓库信息)
	 */
	@RequestMapping(value = "/getVPWById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<EntityDto> getVPWById(String id) {
		return auctionService.getVPWById(id);
	}
	/**
	 * addAuction(新增拍品信息)
	 */
	@RequestMapping(value = "/addAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAuction(HttpSession session, Auction auction) {
		return auctionService.addAuction(session, auction);
	}

	/**
	 * updateAuction(编辑拍品信息)
	 */
	@RequestMapping(value = "/updateAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> updateAuction(Auction auction) {
		return auctionService.updateAuction(auction);
	}
	/**
	 * deleteAuction(删除拍品信息)
	 */
	@RequestMapping(value = "/deleteAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteAuction(HttpSession session,String id) {
		return auctionService.deleteAuction(session,id);
	}
	/**
	 * getAuctionList
	 * 根据条件获取‘车辆列表’信息
	 */
	@RequestMapping(value = "/getAuctionList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionWhole> getAuctionList(HttpSession session, AuctionDto dto) {
		return auctionService.getAuctionList(session, dto);
	}
	/**
	 * getAuctionTab
	 * Auction车辆管理--车辆列表显示的tab
	 */
	@RequestMapping(value = "/getAuctionTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getAuctionTab(HttpSession session,AuctionDto dto) {
		return auctionService.getAuctionTab(session,dto);
	}
	/**
	 * getAuctionSaveList
	 * 根据条件获取‘暂存车辆’列表信息
	 */
	@RequestMapping(value = "/getAuctionSaveList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionSave> getAuctionSaveList(HttpSession session, AuctionDto auction) {
		return auctionService.getAuctionSaveList(session, auction);
	}
	/**
	 * getScrapCarList
	 * 根据条件获取‘报废车辆’列表信息
	 */
	@RequestMapping(value = "/getScrapCarList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionScrap> getScrapCarList(HttpSession session, AuctionDto auction) {
		return auctionService.getAuctionScrapList(session, auction);
	}
	/**
	 * getScrapCarTab
	 * 报废列表显示的tab
	 */
	@RequestMapping(value = "/getScrapCarTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getScrapCarTab(HttpSession session,AuctionDto auction) {
		return auctionService.getScrapCarTab(session,auction);
	}

}

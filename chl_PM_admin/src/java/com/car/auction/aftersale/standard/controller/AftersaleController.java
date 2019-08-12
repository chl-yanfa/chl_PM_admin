package com.car.auction.aftersale.standard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.aftersale.dto.AftersaleDto;
import com.car.auction.aftersale.dto.ApplyDto;
import com.car.auction.aftersale.dto.TrackDto;
import com.car.auction.aftersale.entity.AbnormalAudit;
import com.car.auction.aftersale.entity.AdjustPrice;
import com.car.auction.aftersale.entity.Aftersale;
import com.car.auction.aftersale.entity.BackCar;
import com.car.auction.aftersale.entity.DealEntity;
import com.car.auction.aftersale.entity.DealInfo;
import com.car.auction.aftersale.entity.Defer;
import com.car.auction.aftersale.entity.PayTrack;
import com.car.auction.aftersale.entity.Scrap;
import com.car.auction.aftersale.entity.SecondAsk;
import com.car.auction.aftersale.entity.TakeCarTrack;
import com.car.auction.aftersale.standard.service.ASListBoxService;
import com.car.auction.aftersale.standard.service.AfterSaleService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.finance.entity.Pay;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AftersaleController
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月11日 下午1:33:02
 */
@Controller
@RequestMapping(value = "/aftersale")
public class AftersaleController extends BaseController{
	
	@Autowired
	private ASListBoxService listBoxService;
	@Autowired
	private AfterSaleService afterSaleService;
	
	/**
	 * getDealList
	 * 根据条件获取‘成交列表’信息
	 */
	@RequestMapping(value = "/getDealList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<DealEntity> getDealList(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getDealList(session, aftersale);
	}
	/**
	 * getDealTab
	 * 售后管理--成交列表显示的tab
	 */
	@RequestMapping(value = "/getDealTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getDealTab(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getDealTab(session,aftersale);
	}
	/**
	 * getAbnormalList
	 * 根据条件获取‘售后异常列表’信息
	 */
	@RequestMapping(value = "/getAbnormalList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<DealEntity> getAbnormalList(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getAbnormalList(session, aftersale);
	}
	/**
	 * getAbnormalTab
	 * 售后管理--售后异常显示的tab
	 */
	@RequestMapping(value = "/getAbnormalTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getAbnormalTab(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getAbnormalTab(session,aftersale);
	}
	
	/**
	 * getAbortiveList
	 * 根据条件获取‘流拍列表’信息
	 */
	@RequestMapping(value = "/getAbortiveList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<DealEntity> getAbortiveList(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getAbortiveList(session, aftersale);
	}
	/**
	 * getAbortiveTab
	 * 售后管理--流拍列表显示的tab
	 */
	@RequestMapping(value = "/getAbortiveTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getAbortiveTab(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getAbortiveTab(session,aftersale);
	}
	
	/**
	 * getBreachList
	 * 根据条件获取‘违约列表’信息
	 */
	@RequestMapping(value = "/getBreachList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<DealEntity> getBreachList(HttpSession session, AftersaleDto aftersale) {
		return listBoxService.getBreachList(session, aftersale);
	}
	/**
	 * getDealInfoByAuctionSetId
	 * 获取成交信息
	 */
	@RequestMapping(value = "/getDealInfoByAuctionSetId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<DealInfo> getDealInfoByAuctionSetId(String auctionSetId) {
		return afterSaleService.getDealInfoByAuctionSetId(auctionSetId);
	}
	/**
	 * addPayTrack
	 * 添加付款跟踪信息
	 */
	@RequestMapping(value = "/addPayTrack", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addPayTrack(HttpServletRequest request,PayTrack payTrack) {
		return afterSaleService.addPayTrack(request,payTrack);
	}
	/**
	 * getTrackByAuctionSetId
	 * 获取跟踪信息
	 */
	@RequestMapping(value = "/getTrackByAuctionSetId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<TrackDto> getTrackByAuctionSetId(String auctionSetId) {
		return afterSaleService.getTrackByAuctionSetId(auctionSetId);
	}
	/**
	 * addTakeCarTrack
	 * 添加提货跟踪信息
	 */
	@RequestMapping(value = "/addTakeCarTrack", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addTakeCarTrack(HttpServletRequest request,TakeCarTrack takeCarTrack) {
		return afterSaleService.addTakeCarTrack(request,takeCarTrack);
	}
	/**
	 * addDefer
	 * 新增申请延期信息
	 */
	@RequestMapping(value = "/addDefer", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addDefer(Defer defer) {
		return afterSaleService.addDefer(defer);
	}
	/**
	 * addAdjustPrice
	 * 新增申请调价信息
	 */
	@RequestMapping(value = "/addAdjustPrice", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAdjustPrice(AdjustPrice adjustPrice) {
		return afterSaleService.addAdjustPrice(adjustPrice);
	}
	/**
	 *  addBackCar
	 * 新增申请退货信息
	 */
	@RequestMapping(value = "/addBackCar", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addBackCar(BackCar backCar) {
		return afterSaleService.addBackCar(backCar);
	}
	/**
	 * getApplyByAuctionSetId
	 * 获取申请信息
	 */
	@RequestMapping(value = "/getApplyByAuctionSetId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ApplyDto> getApplyByAuctionSetId(String auctionSetId) {
		return afterSaleService.getApplyByAuctionSetId(auctionSetId);
	}
	/**
	 *  addAbnormalAudit
	 * 新增售后审核
	 */
	@RequestMapping(value = "/addAbnormalAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAbnormalAudit(AbnormalAudit abnormalAudit) {
		return afterSaleService.addAbnormalAudit(abnormalAudit);
	}
	/**
	 *  addScrap
	 * 新增报废
	 */
	@RequestMapping(value = "/addScrap", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addScrap(HttpSession session,Scrap scrap) {
		return afterSaleService.addScrap(session,scrap);
	}
	/**
	 *  getScrapByAuctionId
	 * 获取报废
	 */
	@RequestMapping(value = "/getScrapByAuctionId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Scrap> getScrapByAuctionId(String auctionId) {
		return afterSaleService.getScrapByAuctionId(auctionId);
	}
	/**
	 *  confirmScrap
	 * 确认报废
	 */
	@RequestMapping(value = "/confirmScrap", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> confirmScrap(Scrap scrap) {
		return afterSaleService.confirmScrap(scrap);
	}
	/**
	 *  addSecondAsk
	 * 新增二询
	 */
	@RequestMapping(value = "/addSecondAsk", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addSecondAsk(SecondAsk secondAsk) {
		return afterSaleService.addSecondAsk(secondAsk);
	}
	
	/**
	 * getAftersaleByAuctionSetId
	 * 获取售后状态
	 */
	@RequestMapping(value = "/getAftersaleByAuctionSetId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Aftersale> getAftersaleByAuctionSetId(String auctionSetId) {
		return afterSaleService.getAftersaleByAuctionSetId(auctionSetId);
	}
	/**
	 * addRefund
	 * 新增退款--违约处理
	 */
	@RequestMapping(value = "/addRefund", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addRefund(HttpSession session, Pay pay) {
		return afterSaleService.addRefund(session,pay);
	}
}

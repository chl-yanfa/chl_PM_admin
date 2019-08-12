package com.car.auction.paimai.standard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.BaseController;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.paimai.dto.PaimaiDto;
import com.car.auction.paimai.dto.PaimaiStatisticsDto;
import com.car.auction.paimai.entity.AuctionCar;
import com.car.auction.paimai.entity.Paimai;
import com.car.auction.paimai.entity.PaimaiInfo;
import com.car.auction.paimai.entity.PmAuction;
import com.car.auction.paimai.standard.service.AuctionResultService;
import com.car.auction.paimai.standard.service.PaimaiService;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiController
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月16日 下午3:25:37
 * @version
 */
@Controller
@RequestMapping(value = "/paimai")
public class PaimaiController extends BaseController{
	@Autowired
	private PaimaiService paimaiService;
	
	@Autowired
	private AuctionResultService auctionResultService;
	/**
	 * getPmAuctionList
	 * 根据条件获取‘拍品管理’信息
	 */
	@RequestMapping(value = "/getPmAuctionList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<PmAuction> getPmAuctionList(HttpSession session,PaimaiDto paimai) {
		return paimaiService.getPmAuctionList(session, paimai);
	}
	/**
	 * getPaimaiList
	 * 根据条件获取‘拍卖会管理’信息
	 */
	@RequestMapping(value = "/getPaimaiList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Paimai> getPaimaiList(HttpSession session,PaimaiDto paimai) {
		return paimaiService.getPaimaiList(session, paimai);
	}
	/**
	 * getPaimaiTab
	 * 拍卖会管理列表页TAB
	 */
	@RequestMapping(value = "/getPaimaiTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getPaimaiTab(PaimaiDto paimai) {
		return paimaiService.getPaimaiTab(paimai);
	}
	/**
	 * getPaimaiById
	 * 根据id获取‘拍卖会管理’信息
	 */
	@RequestMapping(value = "/getPaimaiById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Paimai> getPaimaiById(String id) {
		return paimaiService.getPaimaiById(id);
	}
	/**
	 * addPaimai
	 * 新增拍卖会
	 */
	@RequestMapping(value = "/addPaimai", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addPaimai(HttpSession session,Paimai paimai) {
		return paimaiService.addPaimai(session,paimai);
	}
	/**
	 * deletePaimai
	 * 编辑拍卖会
	 */
	@RequestMapping(value = "/deletePaimai", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deletePaimai(String id) {
		return paimaiService.deletePaimai(id);
	}
	/**
	 * getAuctionCarList
	 * 获取拍卖会中-参拍车辆
	 */
	@RequestMapping(value = "/getAuctionCarList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionCar> getAuctionCarList(String pmhId) {
		return paimaiService.getAuctionCarList(pmhId);
	}
	/**
	 * addAuctionCarList
	 * 拍卖会新增参拍车辆
	 */
	@RequestMapping(value = "/addAuctionCarList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addAuctionCarList(Paimai paimai) {
		return paimaiService.addAuctionCarList(paimai);
	}
	
	/**
	 * editPmOrder
	 * 编辑拍卖序号
	 */
	@RequestMapping(value = "/editPmOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> editPmOrder(AuctionSet set) {
		return paimaiService.editPmOrder(set);
	}
	/**
	 * batchDeleteAuctionCar
	 * 批量删除拍卖会参拍车辆
	 */
	@RequestMapping(value = "/batchDeleteAuctionCar", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> batchDeleteAuctionCar(Paimai paimai) {
		return paimaiService.batchDeleteAuctionCar(paimai);
	}
	/**
	 * deleteAuctionCar
	 * 删除拍卖会参拍车辆
	 */
	@RequestMapping(value = "/deleteAuctionCar", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteAuctionCar(String auctionId) {
		return paimaiService.deleteAuctionCar(auctionId);
	}
	/**
	 * getPaimaiStatistics
	 * 获取‘拍卖会统计’信息
	 */
	@RequestMapping(value = "/getPaimaiStatistics", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<PaimaiStatisticsDto> getPaimaiStatistics(String paimaiId) {
		return paimaiService.getPaimaiStatistics(paimaiId);
	}
	/**
	 * returnAuction
	 * 撤拍
	 */
	@RequestMapping(value = "/returnAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> returnAuction(String auctionId) {
		return paimaiService.returnAuction(auctionId);
	}
	
	/**
	 * againAuction
	 * 本场复拍
	 */
	@RequestMapping(value = "/againAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> againAuction(String auctionSetId) {
		ResultDTO<String> res = paimaiService.againAuction(auctionSetId);
		if (StringUtils.isNotBlank(auctionSetId)) {
			try {
				String s = null;
				s = auctionResultService.sendData(Constants.OrderStatus.DUPLICATE_AUCTION, auctionSetId);
				if (!RtnMsgConstants.RETURN_CODE_SUCCESS.equals(s)) {
					res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_ERROR4);
					res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_ERROR41);
					return res;
				}
			} catch (Exception e) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_ERROR4);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_ERROR4 + e.getMessage());
				return res;
			}
		}
		return res;
	}
	/**
	 * publishPaimai
	 * 发布拍卖会
	 */
	@RequestMapping(value = "/publishPaimai", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> publishPaimai(String paimaiId) {
		return paimaiService.publishPaimai(paimaiId);
	}
	/**
	 * finishPaimai
	 * 结束拍卖会
	 */
	@RequestMapping(value = "/finishPaimai", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> finishPaimai(String paimaiId) {
		return paimaiService.finishPaimai(paimaiId);
	}
	/**
	 * getPaimaiInfo
	 * 获取拍卖信息
	 */
	@RequestMapping(value = "/getPaimaiInfo", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<PaimaiInfo> getPaimaiInfo(String auctionSetId) {
		return paimaiService.getPaimaiInfo(auctionSetId);
	}
	/**
	 * deleteAuction
	 * 删除拍品
	 */
	@RequestMapping(value = "/deleteAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteAuction(String auctionId) {
		return paimaiService.deleteAuction(auctionId);
	}
}

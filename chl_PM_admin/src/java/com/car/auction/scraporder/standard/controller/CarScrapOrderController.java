/**
 * 
 */
package com.car.auction.scraporder.standard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.scraporder.dto.CarScrapOrderAutopartsDto;
import com.car.auction.scraporder.dto.CarScrapOrderDto;
import com.car.auction.scraporder.standard.service.CarScrapOrderService;
import com.car.auction.scraporder.vo.CarScrapOrderVo;
import com.car.auction.sys.dto.UserInfoDto;

/**
 * 类名称：CarScrapOrderController
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-04-11 11:12
 */
@Controller
@RequestMapping(value = "/carscraporder")
public class CarScrapOrderController {
	@Autowired
	private CarScrapOrderService carScrapOrderService;
	
	/**
	 * getCarScrapOrderList
	 * 根据条件获取‘整车列表’信息
	 */
	@RequestMapping(value = "/getCarScrapOrderList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<CarScrapOrderDto> getCarScrapOrderList(
			@RequestParam(value="page",required=false,defaultValue="1")Integer page,
			@RequestParam(value="rows",required=false,defaultValue="10")Integer rows,
			CarScrapOrderVo vo) {
		return carScrapOrderService.getCarScrapOrderList(page,rows,vo);
	}
	/**
	 * getCarScrapOrderAutopartsList
	 * 根据条件获取‘旧件列表’信息
	 */
	@RequestMapping(value = "/getCarScrapOrderAutopartsList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<CarScrapOrderAutopartsDto> getCarScrapOrderAutopartsList(
			@RequestParam(value="page",required=true,defaultValue="1")Integer page,
			@RequestParam(value="rows",required=true,defaultValue="10")Integer rows,
			CarScrapOrderVo vo) {
		return carScrapOrderService.getCarScrapOrderAutopartsList(page,rows,vo);
	}
	/**
	 * addParts
	 */
	@RequestMapping(value = "/addParts", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addParts(HttpSession session,
			@RequestParam(value="orderType",required=true)Integer orderType,
			@RequestParam(value="orderId",required=true)String orderId,
			@RequestParam(value="auctionId",required=true)String auctionId) {
		ResultDTO<String> res = new ResultDTO<String>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		carScrapOrderService.addScrapOrder(orderType, orderId, auctionId, userInfo.getId());
		return res;
	}
	/**
	 * deleteParts
	 */
	@RequestMapping(value = "/deleteParts/{orderId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> deleteParts(HttpSession session,
			@PathVariable(value="orderId")String orderId,
			@RequestParam(value="orderType",required=true)Integer orderType,
			@RequestParam(value="auctionId",required=true)String auctionId) {
		ResultDTO<String> res = new ResultDTO<String>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		carScrapOrderService.deleteScrapOrder(orderType, orderId, auctionId, userInfo.getId());
		return res;
	}
}

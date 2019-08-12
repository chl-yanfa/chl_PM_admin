package com.car.auction.paimai.standard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.paimai.dto.ConfirmDto;
import com.car.auction.paimai.standard.service.AuctionResultService;
import com.car.auction.sys.dto.UserInfoDto;

/**
    * 项目名称：SDIC-Inner 
    * 类名称：AuctionResultController 
    * 类描述：拍卖结果处理控制类 创建人：刘民
    * 创建时间：2018年12月10日 上午10:50:56
    *  修改人：刘民 修改时间： 2018年12月10日 上午10:50:56 
    *  修改备注：
 * @version
 */

@Controller
@RequestMapping(value = "/auctionResult")
public class AuctionResultController {

	@Autowired
	private AuctionResultService auctionResultService;
	
	/**
	 * getToDealCost 获取要成交的费用信息
	 */
	@RequestMapping(value = "/getToDealCost", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Map<String,Object>> getToDealCost(String auctionId,String auctionSetId) {
		return auctionResultService.getToDealCost(auctionId,auctionSetId);
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> confirm(ConfirmDto confirmDto) {
		ResultDTO<String> res = new ResultDTO<String>();
		if (null == confirmDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		} else {
			res = auctionResultService.updateConfirm(confirmDto);
		}
		String orderId = res.getEntity();
		if (StringUtils.isNotBlank(orderId)) {
			try {
				String s = null;
				s = auctionResultService.sendData(Constants.OrderStatus.TO_SETTLE, orderId);
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
	 * abortiveAuction() 流拍
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/abortiveAuction", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> abortiveAuction(HttpSession session, ConfirmDto confirmDto) {
		ResultDTO<String> res = new ResultDTO<String>();
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
		if (null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		String userId = userInfoDto.getId();
		if (null == confirmDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		} else {
			res = auctionResultService.abortiveAuction(userId, confirmDto);
		}
		String orderId = confirmDto.getOrderId();
		if (StringUtils.isNotBlank(orderId)) {
			try {
				String s = null;
				s = auctionResultService.sendData(Constants.OrderStatus.TO_ABORTIVE, orderId);
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

}

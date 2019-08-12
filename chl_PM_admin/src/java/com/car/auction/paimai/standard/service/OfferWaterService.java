package com.car.auction.paimai.standard.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.ResultDTO;
import com.car.auction.paimai.dao.OfferWaterDao;
import com.car.auction.paimai.entity.HighestCustomerInfo;
import com.car.auction.paimai.entity.OfferWaterInfo;

/**
 * 项目名称：	sdicPM_interface
 * 类名称：	OfferWaterService
 * 类描述：	出价流水逻辑层
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年11月29日 下午2:03:12
 */
@Service
public class OfferWaterService {
	
	@Autowired 
	private OfferWaterDao offerWaterDao;
	
	/**
	 * 根据订单id查询本拍品出价记录
	 * @param orderId
	 */
	public ResultDTO<OfferWaterInfo> getOfferWaterListByOrderId(String auctionSetId){
		ResultDTO<OfferWaterInfo> res = new ResultDTO<OfferWaterInfo>();
		if(StringUtils.isNotBlank(auctionSetId)) {
			List<OfferWaterInfo> list = offerWaterDao.getOfferWaterListByOrderId(auctionSetId);
			/*if(list!=null&&list.size()>0) {
				for (OfferWaterInfo offerWaterInfo : list) {
					String nickname = offerWaterInfo.getNickname();
					if(StringUtils.isNotBlank(nickname)) {
						nickname = nickname.replaceAll("(.{2})(.*)(.{2})", "$1" + "****" + "$3");
						offerWaterInfo.setNickname(nickname);
					}
				}				
			}*/
			int total = offerWaterDao.getOfferWaterTotalByOrderId(auctionSetId);
			res.setRows(list);
			res.setTotal(total);
			return res;
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("参数为空");
			return res;
		}
	}
	/**
	 * 根据订单id查询本拍品出价人数
	 * @param orderId
	 */
	public ResultDTO<Integer> getBidCountByOrderId(String auctionSetId){
		ResultDTO<Integer> res = new ResultDTO<Integer>();
		if(StringUtils.isNotBlank(auctionSetId)) {
			Integer bidCount = offerWaterDao.getBidCountByOrderId(auctionSetId);
			res.setEntity(bidCount==null?0:bidCount);
			return res;
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("参数为空");
			return res;
		}
	}
	/**
	 * getHighestCustomerByOrderId 根据订单id查询本拍品最高出价信息客户
	 * @param orderId
	 */
	public ResultDTO<HighestCustomerInfo> getHighestCustomerByOrderId(String auctionSetId){
		ResultDTO<HighestCustomerInfo> res = new ResultDTO<HighestCustomerInfo>();
		if(StringUtils.isNotBlank(auctionSetId)) {
			HighestCustomerInfo highestCustomer = offerWaterDao.getHighestCustomerByOrderId(auctionSetId);
			res.setEntity(highestCustomer);
			return res;
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("参数为空");
			return res;
		}
	}
	
}

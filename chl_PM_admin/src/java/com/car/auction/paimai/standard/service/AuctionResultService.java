package com.car.auction.paimai.standard.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.aftersale.dao.AftersaleDao;
import com.car.auction.aftersale.entity.Aftersale;
import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dao.AuctionSetDao;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.CometUtil;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.SmsSender;
import com.car.auction.common.UUIDUtil;
import com.car.auction.customer.dao.CustomerDao;
import com.car.auction.customer.dao.CustomerMarginDao;
import com.car.auction.customer.dao.CustomerMarginWaterDao;
import com.car.auction.customer.entity.Customer;
import com.car.auction.customer.entity.CustomerMargin;
import com.car.auction.customer.entity.CustomerMarginWater;
import com.car.auction.paimai.dao.AuctionResultDao;
import com.car.auction.paimai.dao.PaimaiDao;
import com.car.auction.paimai.dto.ConfirmDto;
import com.car.auction.paimai.entity.Paimai;
import com.car.auction.paimai.entity.TraIndent;

@Service
public class AuctionResultService {
	
	public static ResourceBundle CONFIG = ResourceBundle.getBundle("application");
	private static String cometUrl = CONFIG.getString("cometUrl");
	
	@Autowired
	private AuctionResultDao  auctionResultDao;
	@Autowired
	private AuctionSetDao  auctionSetDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AftersaleDao aftersaleDao;
	@Autowired
	private PaimaiDao paimaiDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerMarginDao customerMarginDao;
	@Autowired
	private CustomerMarginWaterDao customerMarginWaterDao;
	
	/**
	 * getToDealCost
	 * 
	 */
	public ResultDTO<Map<String,Object>> getToDealCost(String auctionId,String auctionSetId) {
		ResultDTO<Map<String,Object>> res = new ResultDTO<Map<String,Object>>();
		if(StringUtils.isBlank(auctionId)||StringUtils.isBlank(auctionSetId)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		AuctionSet auctionSet = auctionSetDao.getToDealAuctionSet(auctionSetId);
		BigDecimal commissionPrice = auctionSet.getCommission()==null?BigDecimal.ZERO:auctionSet.getCommission();
		BigDecimal reservePrice = auctionSet.getReservePrice()==null?BigDecimal.ZERO:auctionSet.getReservePrice();
		BigDecimal otherPrice =auctionSet.getOtherPrice()==null?BigDecimal.ZERO:auctionSet.getOtherPrice();
		BigDecimal jointPrice = getJointPrice(auctionSet.getHighestPrice(), commissionPrice, otherPrice);
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("commissionPrice", commissionPrice);
		map.put("reservePrice", reservePrice);
		map.put("otherPrice", otherPrice);
		map.put("jointPrice", jointPrice);
		res.setEntity(map);
		return res;
	}
	
	@Transactional
	public ResultDTO<String> updateConfirm(ConfirmDto confirmDto){
		ResultDTO<String> res = new ResultDTO<String>();
		Date nowTime = new Date();
		String auctionId = confirmDto.getAuctionId();
		String orderId = confirmDto.getOrderId();
		if(StringUtils.isBlank(confirmDto.getMid())){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL1);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL1); 
			return res;
		}
		AuctionSet auctionSet = auctionSetDao.getToDealAuctionSet(orderId);
		Auction auctionC = auctionDao.getAuctionById(auctionId);
		Customer customerInfo = customerDao.getCustomerById(confirmDto.getMid());
		if(null==auctionSet||null==auctionC||null==customerInfo){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_INDENT2);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_INDENT2);
			return res;
		}
		/**
		 * 成交
		 */
		String userName = customerInfo.getUserName();
		if(StringUtils.isBlank(userName)) {
			userName = customerInfo.getLoginName();
		}
		BigDecimal commissionPrice = auctionSet.getCommission()==null?BigDecimal.ZERO:auctionSet.getCommission();
		BigDecimal otherPrice =auctionSet.getOtherPrice()==null?BigDecimal.ZERO:auctionSet.getOtherPrice();
		BigDecimal jointPrice = getJointPrice(auctionSet.getHighestPrice(), commissionPrice, otherPrice);
		AuctionSet nowOrder = new AuctionSet(); 
		nowOrder.setDealMember(userName);
		nowOrder.setDealTime(nowTime);
		nowOrder.setDealMid(confirmDto.getMid());
		nowOrder.setOrderState(Constants.OrderStatus.TO_SETTLE);
		nowOrder.setHighestPrice(auctionSet.getHighestPrice());
		nowOrder.setJointPrice(jointPrice); //合手价
		nowOrder.setId(orderId);
		auctionSetDao.enterAuctionSet(nowOrder);
		/**
		 * 发送短信
		 */
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日");
	    SmsSender.sendSms(confirmDto.getPhone(), SmsSender.formatSuccessMag, SmsSender.AUCTIONCODE,myFmt.format(new Date()),auctionC.getFullName(), auctionC.getAuctionNo());
		
		/**
		 * 修改下一拍品时间
		 */
		AuctionSet nextAuctionSet = auctionSetDao.getNextAuctionSet(auctionSet.getPmhId());
		if(null!=nextAuctionSet) {
			Calendar time = new GregorianCalendar();
			time.setTime(new Date());
//			time.add(Calendar.SECOND, 3);
			nextAuctionSet.setAuctionStartTime(time.getTime());
			time.add(Calendar.SECOND, nextAuctionSet.getSingleTime());
			time.add(Calendar.SECOND, nextAuctionSet.getIntervalTime());
			nextAuctionSet.setAuctionEndTime(time.getTime());
			nextAuctionSet.setOrderState("20");
			auctionSetDao.updateAuctionSetSelective(nextAuctionSet);			
		}else {
			AuctionSet auctioning = auctionSetDao.getAuctioningSet(auctionSet.getPmhId());
			if(null==auctioning) {
				/**
				 * 没有了，拍卖会结束
				 */
				Paimai paimai = paimaiDao.getPaimaiByPmhId(auctionSet.getPmhId());
				paimai.setPaimaiState(2);//
				paimai.setPaimaiFinishTime(new Date());
				paimaiDao.updatePaimaiSelective(paimai);				
			}
		}
		/**
		 * 修改成交状态,新增售后表
		 */
		Auction auction=new Auction();
		auction.setId(auctionId);
		auction.setAuctionState(5);
		auctionDao.updateAuctionState(auction);
		
		Aftersale aftersale=new Aftersale();
		aftersale.setAuctionId(auctionId);
		aftersale.setAuctionSetId(orderId);
		aftersale.setDealTime(nowTime);
		aftersale.setDealHandler(customerInfo.getUserName());
		aftersaleDao.addAftersale(aftersale);
		
		res.setReturnCode(RtnMsgConstants.RETURN_CODE_SUCCESS);
		res.setReturnMsg(RtnMsgConstants.RETURN_MSG_SUCCESS);
		res.setEntity(orderId);
		return  res;
	}
	
	/**
	 * abortiveAuction() 流拍车辆
	 * @param  
	 * @return 
	 */
	@Transactional(rollbackFor = Exception.class)
	public ResultDTO<String> abortiveAuction(String userCode, ConfirmDto confirmDto){
		ResultDTO<String> res = new ResultDTO<String>();
		String carCode = confirmDto.getAuctionId();
		String orderId = confirmDto.getOrderId();
		AuctionSet auctionSet = auctionSetDao.getToDealAuctionSet(orderId);
		Auction auctionC = auctionDao.getAuctionById(confirmDto.getAuctionId());
		if(null==auctionSet||null==auctionC){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_INDENT2);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_INDENT2);
			return res;
		}
		/**
		 * 流拍
		 */
		BigDecimal commissionPrice = auctionSet.getCommission()==null?BigDecimal.ZERO:auctionSet.getCommission();
		BigDecimal otherPrice =auctionSet.getOtherPrice()==null?BigDecimal.ZERO:auctionSet.getOtherPrice();
		BigDecimal jointPrice = getJointPrice(auctionSet.getHighestPrice(), commissionPrice, otherPrice);
		AuctionSet nowOrder = new AuctionSet(); 
		nowOrder.setOrderState(Constants.OrderStatus.TO_ABORTIVE);
		nowOrder.setJointPrice(jointPrice); //合手价
		nowOrder.setHighestPrice(auctionSet.getHighestPrice());
		nowOrder.setId(orderId);
		auctionSetDao.enterAuctionSet(nowOrder);
		/**
		 * 修改下一拍品时间
		 */
		AuctionSet nextAuctionSet = auctionSetDao.getNextAuctionSet(auctionSet.getPmhId());
		if(null!=nextAuctionSet) {
			Calendar time = new GregorianCalendar();
			time.setTime(new Date());
//			time.add(Calendar.SECOND, 3);
			nextAuctionSet.setAuctionStartTime(time.getTime());
			time.add(Calendar.SECOND, nextAuctionSet.getSingleTime());
			time.add(Calendar.SECOND, nextAuctionSet.getIntervalTime());
			nextAuctionSet.setAuctionEndTime(time.getTime());
			nextAuctionSet.setOrderState("20");
			auctionSetDao.updateAuctionSetSelective(nextAuctionSet);			
		}else {
			AuctionSet auctioning = auctionSetDao.getAuctioningSet(auctionSet.getPmhId());
			if(null==auctioning) {
				/**
				 * 没有了，拍卖会结束
				 */
				Paimai paimai = paimaiDao.getPaimaiByPmhId(auctionSet.getPmhId());
				paimai.setPaimaiState(2);//
				paimai.setPaimaiFinishTime(new Date());
				paimaiDao.updatePaimaiSelective(paimai);				
			}
		}
		/**
		 * 修改流拍状态
		 */
		Auction auction=new Auction();
		auction.setId(carCode);
		auction.setAuctionState(8);
		auctionDao.updateAuctionState(auction);
		
		/**
		 * 解冻保证金,更新保证金表,查询保证金流水表
		 */
		CustomerMarginWater param = new CustomerMarginWater();
		param.setOrderId(orderId);
		param.setOperatorType("1");//冻结
		CustomerMarginWater waterDB = customerMarginWaterDao.getCustomerMarginWaterByParam(param);
		if(null!=waterDB) {
			String mid = waterDB.getMid();
			BigDecimal auctionCashDeposit = auctionSet.getAuctionCashDeposit();//保证金
			CustomerMargin customerMargin = new CustomerMargin();
			customerMargin.setWalletPledge(auctionCashDeposit);
			customerMargin.setWalletPledgeFreeze(auctionCashDeposit);
			customerMargin.setUpdateUser(userCode);
			customerMargin.setId(mid);
			customerMarginDao.thawCustomerMargin(customerMargin);
			
			/**
			 * 新增保证金流水
			 */
			CustomerMarginWater water =new CustomerMarginWater();
			water.setId(UUIDUtil.getUuid());
			water.setMid(mid);
			water.setOperatorCash("+"+auctionCashDeposit.intValue());
			water.setMemo("车辆流拍解冻！拍品编号："+auctionC.getAuctionNo()+",订单号：" + auctionSet.getOrderNo());
			water.setOrderId(orderId);
			water.setAuctionNo(auctionC.getAuctionNo());
			water.setOperatorType("2");
			water.setCreateUser(userCode);
			customerMarginWaterDao.addCustomerMarginWater(water);			
		}
		res.setReturnCode(RtnMsgConstants.RETURN_CODE_SUCCESS);
		res.setReturnMsg(RtnMsgConstants.RETURN_MSG_SUCCESS);
		res.setEntity(orderId);
		return  res;
	}
	
	/**
	 * 查询当前订单
	 */
	public TraIndent getTraIndentByCarCode(String auctionId){
		TraIndent nowOrder = new TraIndent();
		nowOrder.setIsHistory(0);
		nowOrder.setIsDelete(0);
		nowOrder.setAuctionId(auctionId);
		List<TraIndent> traIndents = auctionResultDao.findTraIndent(nowOrder);
		if(null == traIndents || traIndents.size() !=1 || StringUtils.isBlank(traIndents.get(0).getOrderId())){
			return null;
		}
		return traIndents.get(0);
	}
	
	
	
	public String sendData(String flag,String orderId)throws Exception{
		if(Constants.OrderStatus.TO_SETTLE.equals(flag)||Constants.OrderStatus.DUPLICATE_AUCTION.equals(flag)||Constants.OrderStatus.TO_ABORTIVE.equals(flag)){
			flag = Constants.sendType.ACCOMPLISH;
		}else{
			return RtnMsgConstants.RETURN_CODE_SUCCESS;
		}
		StringBuffer content = new StringBuffer();
		TraIndent traSendData = null;
		if(Constants.sendType.ACCOMPLISH.equals(flag)){
			traSendData = auctionResultDao.getConfirmSendData(orderId);
			content.append(traSendData.getDataStr());
		}
		if(content.length()>0){
			CometUtil.send(cometUrl,flag,content.toString());
		}
		
		return RtnMsgConstants.RETURN_CODE_SUCCESS;
	}
	
	/**
	 * 计算合手价
	 * highestPrice
	 * commissionPrice
	 * transferMargin
	 * otherPrice
	 */
	public BigDecimal getJointPrice(BigDecimal highestPrice,BigDecimal commissionPrice,BigDecimal otherPrice){
		BigDecimal jointPrice = BigDecimal.ZERO;
		highestPrice=highestPrice==null?BigDecimal.ZERO:highestPrice;
		commissionPrice =commissionPrice==null?BigDecimal.ZERO:commissionPrice;
		otherPrice = otherPrice==null?BigDecimal.ZERO:otherPrice;
		jointPrice=jointPrice.add(highestPrice)
				.add(commissionPrice)
				.add(otherPrice);
		return jointPrice;
	}
	

}

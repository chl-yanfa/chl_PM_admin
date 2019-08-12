package com.car.auction.finance.standard.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.customer.dao.CustomerMarginDao;
import com.car.auction.customer.dao.CustomerMarginWaterDao;
import com.car.auction.customer.entity.CustomerMargin;
import com.car.auction.customer.entity.CustomerMarginWater;
import com.car.auction.finance.dao.FinanceDao;
import com.car.auction.finance.dao.PayDao;
import com.car.auction.finance.dao.PayWaterDao;
import com.car.auction.finance.dto.FinanceDto;
import com.car.auction.finance.entity.FinanceEntity;
import com.car.auction.finance.entity.Pay;
import com.car.auction.finance.entity.PayWater;
import com.car.auction.sys.dto.UserInfoDto; 

@Service
public class FinanceService {
	
	@Autowired
	private FinanceDao financeDao;
	@Autowired
	private PayDao payDao;
	@Autowired
	private PayWaterDao payWaterDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AftersaleDao aftersaleDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	@Autowired
	private CustomerMarginDao customerMarginDao;
	@Autowired
	private CustomerMarginWaterDao customerMarginWaterDao;
	
	/**
	 * getFinanceList()
	 * 根据条件获取‘财务列表’信息
	 */
	public ResultDTO<FinanceEntity> getFinanceList(HttpSession session,FinanceDto finance) {
		ResultDTO<FinanceEntity> result = new ResultDTO<FinanceEntity>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<FinanceEntity> auctionList = financeDao.getFinanceListByPage(finance);
		for (FinanceEntity financeEntity : auctionList) {
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(financeEntity.getId());
			if(null!=auctionSet) {
				financeEntity.setAuctionSetId(auctionSet.getId());
				financeEntity.setDealTime(auctionSet.getDealTime());
				financeEntity.setDealHandler(auctionSet.getDealMember());
			}
		}
		int total = financeDao.getFinanceListTotal(finance); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getFinanceTab()
	 * 获取列表TAB
	 */
	public List<Map<String, String>> getFinanceTab(HttpSession session,FinanceDto finance) {
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		if(finance == null) {
			finance = new FinanceDto();
		}
		return financeDao.getFinanceTab(finance);
	}
	/**
	 * addPay()
	 * 新增付款
	 */
	@Transactional
	public ResultDTO<String> addPay(HttpSession session,Pay pay) {
		ResultDTO<String> result = new ResultDTO<String>();
		if(pay == null||StringUtils.isBlank(pay.getAuctionId())
				||StringUtils.isBlank(pay.getAuctionSetId())
				||StringUtils.isBlank(pay.getMid())) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return result;
		}
		if(pay.getPayVoucherId()==null) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			result.setReturnMsg("请上传付款凭证");
			return result;
		}
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
		if(null == userInfoDto) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		String auctionSetId = pay.getAuctionSetId();
		String mid = pay.getMid();
		AuctionSet auctionSet = auctionSetDao.getAuctionSetById(auctionSetId);
		Auction auctionC = auctionDao.getAuctionById(pay.getAuctionId());
		CustomerMargin customerMargin = customerMarginDao.getCustomerMarginById(mid);
		if(null == auctionSet||null==auctionC|| null== customerMargin) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return result;
		}
		/**
		 * 新增付款信息
		 */
		pay.setId(UUIDUtil.getUuid());
		pay.setType("PAY");
		pay.setHighestPrice(auctionSet.getHighestPrice());
		pay.setCommission(auctionSet.getCommission());
		pay.setOtherPrice(auctionSet.getOtherPrice());
		payDao.addPay(pay);
		result.setEntity(pay.getId());
		//改状态
		Auction auction =new Auction();
		auction.setId(pay.getAuctionId());
		auction.setAftersaleState(1);
		auctionDao.updateAuctionState(auction);
		auctionSet.setOrderState(Constants.OrderStatus.TO_TAKECAR);//待提货
		auctionSetDao.updateAuctionSetSelective(auctionSet);
		Aftersale aftersale=new Aftersale();
		aftersale.setAuctionSetId(auctionSetId);
		aftersale.setPayState(1);
		aftersale.setPayTime(new Date());
		aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
		
		/**
		 * 解冻保证金,更新保证金表
		 */
		BigDecimal auctionCashDeposit = auctionSet.getAuctionCashDeposit();//参拍保证金
		
		customerMargin.setWalletPledge(auctionCashDeposit);
		customerMargin.setWalletPledgeFreeze(auctionCashDeposit);
		customerMargin.setUpdateUser(userInfoDto.getId());
		customerMarginDao.thawCustomerMargin(customerMargin);
		/**
		 * 新增保证金流水
		 */
		CustomerMarginWater water =new CustomerMarginWater();
		water.setId(UUIDUtil.getUuid());
		water.setMid(mid);
		water.setOperatorCash("+"+auctionCashDeposit.intValue());
		water.setMemo("车辆成交解冻！拍品编号："+auctionC.getAuctionNo()+",订单号：" + auctionSet.getOrderNo());
		water.setOrderId(auctionSetId);
		water.setAuctionNo(auctionC.getAuctionNo());
		water.setOperatorType("2");
		water.setCreateUser(userInfoDto.getId());
		customerMarginWaterDao.addCustomerMarginWater(water);

		PayWater payWater = new PayWater(UUIDUtil.getUuid(), mid, 
				"+"+pay.getPayMoney().intValue(), Constants.PayWaterState.PAY,
				"付款", userInfoDto.getId(), auctionSetId);
		payWaterDao.addPayWater(payWater);
		return result;
	}
	/**
	 * getPay()
	 * 获取付款信息
	 */
	public ResultDTO<Pay> getPay(String auctionSetId) {
		ResultDTO<Pay> result = new ResultDTO<Pay>();
		if(StringUtils.isNotBlank(auctionSetId)) {
			Pay pay = payDao.getPayByAuctionSetId(auctionSetId);
			if(pay!=null) {
				result.setEntity(pay);				
			}else {
				result.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				result.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			}
		}else {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return result;
	}
	/**
	 * getRefund()
	 * 获取退款信息
	 */
	public ResultDTO<Pay> getRefund(String auctionSetId) {
		ResultDTO<Pay> res = new ResultDTO<Pay>();
		if(StringUtils.isBlank(auctionSetId)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		Pay pay = payDao.getRefundByAuctionSetId(auctionSetId);
		if(pay!=null) {
			res.setEntity(pay);				
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * updatePaySelective()
	 * 自定义更新付款
	 */
	public int updatePaySelective(Pay pay) {
		return payDao.updatePaySelective(pay);
	}
}

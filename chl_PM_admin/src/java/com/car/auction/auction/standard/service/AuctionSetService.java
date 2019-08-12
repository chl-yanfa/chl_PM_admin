package com.car.auction.auction.standard.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.car.auction.organization.standard.service.PMNumberService;
import com.car.auction.sys.dto.UserInfoDto;

@Service
public class AuctionSetService {
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	@Autowired
	private PMNumberService pMNumberService;
	@Autowired
	private AftersaleDao aftersaleDao;
	
	public ResultDTO<AuctionSet> getAuctionSetByAuctionId(String auctionId) {
		ResultDTO<AuctionSet> res = new ResultDTO<AuctionSet>();
		if(null != auctionId){
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
			res.setEntity(auctionSet);
		}else{
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		return res;
	}
	public ResultDTO<AuctionSet> getAuctionSetById(String auctionSetId) {
		ResultDTO<AuctionSet> res = new ResultDTO<AuctionSet>();
		if(StringUtils.isNotBlank(auctionSetId)){
			AuctionSet auctionSet = auctionSetDao.getAuctionSetById(auctionSetId);
			res.setEntity(auctionSet);
		}else{
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		return res;
	}
	
	/**
	 * addAuctionSet(新增参拍设置)
	 */
	public ResultDTO<String> addAuctionSet(HttpServletRequest request,AuctionSet auctionSet) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null == auctionSet){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		if(StringUtils.isBlank(auctionSet.getAuctionId())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入auctionId");
			return res;
		}
		Auction auctionDB = auctionDao.getAuctionById(auctionSet.getAuctionId());
		if(auctionDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("auctionId无效");
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		auctionSet.setCreateUser(userInfoDto==null?null:userInfoDto.getId());//操作人
		auctionSet.setOrderState(Constants.OrderStatus.HAS_NO_AUCTION);
		AuctionSet entity = auctionSetDao.getAuctionSetByAuctionId(auctionSet.getAuctionId());
		if(entity==null) {
			//新增参拍设置
			auctionSet.setId(UUIDUtil.getUuid());
			//编号
			String orderNo = pMNumberService.getOrderNo();
			auctionSet.setOrderNo(orderNo);
			auctionSetDao.addAuctionSet(auctionSet);
			//修改参拍设置状态
			Auction auction=new Auction();
			auction.setId(auctionSet.getAuctionId());
			auction.setSetState(1);//已设置
			auction.setAuctionState(10);//拍品池
			auctionDao.updateAuctionState(auction);	
		}
		return res;
	}
	
	/**
	 * auctionAgainSet(再次拍卖设置)
	 */
	public ResultDTO<String> auctionAgainSet(HttpServletRequest request,AuctionSet auctionSet) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null == auctionSet){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		if(StringUtils.isBlank(auctionSet.getAuctionId())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入auctionId");
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		auctionSet.setCreateUser(userInfoDto==null?null:userInfoDto.getId());//操作人
		auctionSet.setOrderState(Constants.OrderStatus.HAS_NO_AUCTION);
		AuctionSet entity = auctionSetDao.getAuctionSetByAuctionId(auctionSet.getAuctionId());
		if(entity!=null&&"40".equals(entity.getOrderState())) {//流拍
			entity.setIsHistory(Constants.IS_HISTORY);
			auctionSetDao.updateAuctionSetSelective(entity);
			//新增参拍设置
			auctionSet.setId(UUIDUtil.getUuid());
			//编号
			String orderNo = pMNumberService.getOrderNo();
			auctionSet.setOrderNo(orderNo);
			auctionSetDao.addAuctionSet(auctionSet);
			//修改参拍设置状态
			Auction auction=new Auction();
			auction.setId(auctionSet.getAuctionId());
			auction.setSetState(1);//已设置
			auction.setAuctionState(10);//拍品池
			auction.setAbortiveState(0);
			auctionDao.updateAuctionState(auction);
			
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("数据异常");
		}
		return res;
	}
	/**
	 * auctionAgainSetTC( 新增再次参拍设置(退货后))
	 */
	public ResultDTO<String> auctionAgainSetTC(HttpServletRequest request,AuctionSet auctionSet) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null == auctionSet||StringUtils.isBlank(auctionSet.getAuctionId())){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		auctionSet.setCreateUser(userInfoDto.getId());//操作人
		auctionSet.setOrderState(Constants.OrderStatus.HAS_NO_AUCTION);
		AuctionSet entity = auctionSetDao.getAuctionSetByAuctionId(auctionSet.getAuctionId());
		if(entity!=null&&(Constants.OrderStatus.RETURNCAR_AUCTION).equals(entity.getOrderState())) {//已退货
			entity.setIsHistory(Constants.IS_HISTORY);
			auctionSetDao.updateAuctionSetSelective(entity);
			Aftersale aftersaleDB = aftersaleDao.getAftersaleByAuctionSetId(entity.getId());
			if(aftersaleDB!=null) {
				aftersaleDB.setIsHistory(Constants.IS_HISTORY);
				aftersaleDao.updateAftersaleSelective(aftersaleDB);
			}
			//新增参拍设置
			auctionSet.setId(UUIDUtil.getUuid());
			//编号
			String orderNo = pMNumberService.getOrderNo();
			auctionSet.setOrderNo(orderNo);
			auctionSet.setIsDelete(0);
			auctionSet.setIsHistory(0);
			auctionSetDao.addAuctionSet(auctionSet);
			//修改参拍设置状态
			Auction auction=new Auction();
			auction.setId(auctionSet.getAuctionId());			
			auction.setAuctionState(10);//拍品池
			auction.setSetState(1);//已设置
			auctionDao.updateAuctionState(auction);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("数据异常");
		}
		return res;
	}
}

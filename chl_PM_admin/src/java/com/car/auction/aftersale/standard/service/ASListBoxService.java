package com.car.auction.aftersale.standard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.aftersale.dao.ASListBoxDao;
import com.car.auction.aftersale.dto.AftersaleDto;
import com.car.auction.aftersale.entity.DealEntity;
import com.car.auction.auction.dao.AuctionSetDao;
import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.sys.dto.UserInfoDto; 

@Service
public class ASListBoxService {
	
	@Autowired
	private ASListBoxDao listBoxDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	/**
	 * getDealList()
	 * 根据条件获取‘成交列表’信息
	 */
	public ResultDTO<DealEntity> getDealList(HttpSession session,AftersaleDto aftersale) {
		if(aftersale.getAftersaleState()!=null) {
			if(aftersale.getAftersaleState()==2) {
				aftersale.setAftersaleState(1);
			}else if(aftersale.getAftersaleState()==4) {
				aftersale.setAftersaleState(3);
			}
		}
		ResultDTO<DealEntity> result = new ResultDTO<DealEntity>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<DealEntity> auctionList = listBoxDao.getDealListByPage(aftersale);
		for (DealEntity dealEntity : auctionList) {
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(dealEntity.getId());
			if(null!=auctionSet) {
				dealEntity.setAuctionSetId(auctionSet.getId());
				dealEntity.setDealTime(auctionSet.getDealTime());
			}
		}
		int total = listBoxDao.getDealListTotal(aftersale); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getDealTab()
	 * 获取成交列表TAB
	 */
	public List<Map<String, String>> getDealTab(HttpSession session,AftersaleDto aftersale) {
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		if(aftersale == null) {
			aftersale = new AftersaleDto();
		}
		return listBoxDao.getDealTab(aftersale);
	}
	
	/**
	 * getAbnormalList()
	 * 根据条件获取‘售后异常列表’信息
	 */
	public ResultDTO<DealEntity> getAbnormalList(HttpSession session,AftersaleDto aftersale) {
		ResultDTO<DealEntity> result = new ResultDTO<DealEntity>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<DealEntity> auctionList = listBoxDao.getAbnormalListByPage(aftersale);
		for (DealEntity dealEntity : auctionList) {
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(dealEntity.getId());
			if(null!=auctionSet) {
				dealEntity.setAuctionSetId(auctionSet.getId());
				dealEntity.setDealTime(auctionSet.getDealTime());
			}
		}
		int total = listBoxDao.getAbnormalListTotal(aftersale); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getAbnormalTab()
	 * 获取售后异常列表TAB
	 */
	public List<Map<String, String>> getAbnormalTab(HttpSession session,AftersaleDto aftersale) {
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		if(aftersale == null) {
			aftersale = new AftersaleDto();
		}
		return listBoxDao.getAbnormalTab(aftersale);
	}
	
	/**
	 * getAbortiveList()
	 * 根据条件获取‘流拍列表’信息
	 */
	public ResultDTO<DealEntity> getAbortiveList(HttpSession session,AftersaleDto aftersale) {
		ResultDTO<DealEntity> result = new ResultDTO<DealEntity>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<DealEntity> auctionList = listBoxDao.getAbortiveListByPage(aftersale); 
		for (DealEntity dealEntity : auctionList) {
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(dealEntity.getId());
			if(null!=auctionSet) {
				dealEntity.setAuctionSetId(auctionSet.getId());
			}
		}
		int total = listBoxDao.getAbortiveListTotal(aftersale); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getAbortiveTab()
	 * 获取流拍列表TAB
	 */
	public List<Map<String, String>> getAbortiveTab(HttpSession session,AftersaleDto aftersale) {
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		if(aftersale == null) {
			aftersale = new AftersaleDto();
		}
		return listBoxDao.getAbortiveTab(aftersale);
	}
	/**
	 * getBreachList()
	 * 根据条件获取‘违约列表’信息
	 */
	public ResultDTO<DealEntity> getBreachList(HttpSession session,AftersaleDto aftersale) {
		ResultDTO<DealEntity> result = new ResultDTO<DealEntity>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<DealEntity> auctionList = listBoxDao.getBreachListByPage(aftersale); 
		for (DealEntity dealEntity : auctionList) {
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(dealEntity.getId());
			if(null!=auctionSet) {
				dealEntity.setAuctionSetId(auctionSet.getId());
			}
		}
		int total = listBoxDao.getBreachListTotal(aftersale); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
}

package com.car.auction.aftersale.standard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.aftersale.dao.AftersaleDao;
import com.car.auction.aftersale.dao.AftersaleFileDao;
import com.car.auction.aftersale.dto.ApplyDto;
import com.car.auction.aftersale.dto.TrackDto;
import com.car.auction.aftersale.entity.AbnormalAudit;
import com.car.auction.aftersale.entity.AdjustPrice;
import com.car.auction.aftersale.entity.Aftersale;
import com.car.auction.aftersale.entity.AftersaleFile;
import com.car.auction.aftersale.entity.BackCar;
import com.car.auction.aftersale.entity.DealInfo;
import com.car.auction.aftersale.entity.Defer;
import com.car.auction.aftersale.entity.PayTrack;
import com.car.auction.aftersale.entity.Scrap;
import com.car.auction.aftersale.entity.SecondAsk;
import com.car.auction.aftersale.entity.TakeCarTrack;
import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dao.AuctionSetDao;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.customer.dao.CustomerMarginDao;
import com.car.auction.customer.entity.CustomerMargin;
import com.car.auction.finance.dao.PayDao;
import com.car.auction.finance.dao.PayWaterDao;
import com.car.auction.finance.entity.Pay;
import com.car.auction.finance.entity.PayWater;
import com.car.auction.sys.dto.UserInfoDto; 

@Service
public class AfterSaleService {
	
	@Autowired
	private AftersaleDao aftersaleDao;
	@Autowired
	private AftersaleFileDao aftersaleFileDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	@Autowired
	private PayDao payDao;
	@Autowired
	private CustomerMarginDao customerMarginDao;
	@Autowired
	private PayWaterDao payWaterDao;
	/**
	 * addPayTrack()
	 * 添加付款跟踪信息
	 */
	@Transactional
	public ResultDTO<String> addPayTrack(HttpServletRequest request,PayTrack payTrack) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(payTrack==null||StringUtils.isBlank(payTrack.getAuctionId())
				||StringUtils.isBlank(payTrack.getAuctionSetId())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		payTrack.setOperator(userInfoDto.getRealName());
		aftersaleDao.addPayTrack(payTrack);			
		return res;
	}
	
	/**
	 * getDealInfoByAuctionSetId()
	 * 获取成交信息
	 */
	public ResultDTO<DealInfo> getDealInfoByAuctionSetId(String auctionSetId) {
		ResultDTO<DealInfo> res = new ResultDTO<DealInfo>();
		DealInfo dealInfo = aftersaleDao.getDealInfoByAuctionSetId(auctionSetId);
		res.setEntity(dealInfo);
		return res;
	}
	/**
	 * getTrackByAuctionSetId()
	 * 获取跟踪信息
	 */
	public ResultDTO<TrackDto> getTrackByAuctionSetId(String auctionSetId) {
		ResultDTO<TrackDto> res = new ResultDTO<TrackDto>();
		List<PayTrack> payTrackList = aftersaleDao.getPayTrackByAuctionSetId(auctionSetId);
		Pay pay = payDao.getPayByAuctionSetId(auctionSetId);
		if(pay!=null) {
			PayTrack payTrack = new PayTrack();
			payTrack.setCreateTime(pay.getCreateTime());
			payTrack.setOperator(pay.getCreateUser());
			payTrack.setTrackMessage("已付款："+pay.getPayMoney());
			payTrack.setPayHandle(pay.getPayHandle());			
			payTrackList.add(payTrack);
		}
		List<TakeCarTrack> takeCarTrackList = aftersaleDao.getTakeCarTrackByAuctionSetId(auctionSetId);
		TrackDto dto=new TrackDto();
		dto.setPayTracks(payTrackList);
		dto.setTakeCarTracks(takeCarTrackList);
		res.setEntity(dto);
		return res;
	}
	/**
	 * addTakeCarTrack()
	 * 添加提货跟踪信息
	 */
	@Transactional
	public ResultDTO<String> addTakeCarTrack(HttpServletRequest request,TakeCarTrack takeCarTrack) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(takeCarTrack==null||StringUtils.isBlank(takeCarTrack.getAuctionId())
				||StringUtils.isBlank(takeCarTrack.getAuctionSetId())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		String auctionSetId = takeCarTrack.getAuctionSetId();
		AuctionSet auctionSet = auctionSetDao.getAuctionSetById(auctionSetId);
		Auction auctionC = auctionDao.getAuctionById(takeCarTrack.getAuctionId());
		if(null == auctionSet||null==auctionC) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		takeCarTrack.setOperator(userInfoDto==null?null:userInfoDto.getRealName());
		takeCarTrack.setId(UUIDUtil.getUuid());
		aftersaleDao.addTakeCarTrack(takeCarTrack);
		//状态更新
		Auction auction=new Auction();
		auction.setId(takeCarTrack.getAuctionId());
		auction.setAftersaleState(3);
		auctionDao.updateAuctionState(auction);
		auctionSet.setOrderState(Constants.OrderStatus.FINISHED);
		auctionSetDao.updateAuctionSetSelective(auctionSet);
		Aftersale aftersale=new Aftersale();
		aftersale.setAuctionSetId(takeCarTrack.getAuctionSetId());
		aftersale.setTakeCarTime(new Date());
		aftersale.setTakeCarState(1);//已提货
		aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
		res.setEntity(takeCarTrack.getId());
		//新增图片
		if(StringUtils.isNotBlank(takeCarTrack.getFileIds())) {
			List<String> asList = Arrays.asList(takeCarTrack.getFileIds().split(","));
			if(asList!=null && asList.size()>0) {
				List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
				if(fileIdList!=null && fileIdList.size()>0) {
					List<AftersaleFile> list =new ArrayList<AftersaleFile>();
					AftersaleFile file;
					for (int i = 0;i<fileIdList.size();i++) {
						file = new AftersaleFile( 1, takeCarTrack.getId(), fileIdList.get(i), i);
						list.add(file);
					}
					aftersaleFileDao.addFileBatch(list);
				}
				
			}
		}
		return res;
	}
	
	/**
	 * addDefer()
	 * 新增申请延期信息
	 */
	@Transactional
	public ResultDTO<String> addDefer(Defer defer) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(defer!=null&&defer.getAuctionId()!=null) {
			defer.setAuditState(0);
			defer.setId(UUIDUtil.getUuid());
			aftersaleDao.addDefer(defer);
			res.setEntity(defer.getId());
			//修改状态
			Aftersale aftersale=new Aftersale();
			aftersale.setAuctionSetId(defer.getAuctionSetId());
			aftersale.setDeferState(1);//延期待审核
			aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
			//新增图片
			if(StringUtils.isNotBlank(defer.getFileIds())) {
				List<String> asList = Arrays.asList(defer.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<AftersaleFile> list =new ArrayList<AftersaleFile>();
						AftersaleFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new AftersaleFile( 3, defer.getId(), fileIdList.get(i), i);
							list.add(file);
						}
						aftersaleFileDao.addFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * addAdjustPrice()
	 * 新增申请调价信息
	 */
	@Transactional
	public ResultDTO<String> addAdjustPrice(AdjustPrice adjustPrice) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(adjustPrice!=null&&adjustPrice.getAuctionId()!=null) {
			adjustPrice.setAuditState(0);
			adjustPrice.setId(UUIDUtil.getUuid());
			aftersaleDao.addAdjustPrice(adjustPrice);
			res.setEntity(adjustPrice.getId());
			//修改状态
			Aftersale aftersale=new Aftersale();
			aftersale.setAuctionSetId(adjustPrice.getAuctionSetId());
			aftersale.setAdjustPriceState(1);//待审核
			aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
			//新增图片
			if(StringUtils.isNotBlank(adjustPrice.getFileIds())) {
				List<String> asList = Arrays.asList(adjustPrice.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<AftersaleFile> list =new ArrayList<AftersaleFile>();
						AftersaleFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new AftersaleFile( 4, adjustPrice.getId(), fileIdList.get(i), i);
							list.add(file);
						}
						aftersaleFileDao.addFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * addBackCar()
	 * 新增申请退货信息
	 */
	@Transactional
	public ResultDTO<String> addBackCar(BackCar backCar) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(backCar!=null&&backCar.getAuctionId()!=null) {
			backCar.setAuditState(0);
			backCar.setId(UUIDUtil.getUuid());
			aftersaleDao.addBackCar(backCar);	
			res.setEntity(backCar.getId());
			//修改状态
			Aftersale aftersale=new Aftersale();
			aftersale.setAuctionSetId(backCar.getAuctionSetId());
			aftersale.setBackCarState(1);//待审核
			aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
			
			//新增图片
			if(StringUtils.isNotBlank(backCar.getFileIds())) {
				List<String> asList = Arrays.asList(backCar.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<AftersaleFile> list =new ArrayList<AftersaleFile>();
						AftersaleFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new AftersaleFile( 5, backCar.getId(), fileIdList.get(i), i);
							list.add(file);
						}
						aftersaleFileDao.addFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * getApplyByAuctionId()
	 * 获取申请信息
	 */
	public ResultDTO<ApplyDto> getApplyByAuctionSetId(String auctionSetId) {
		ResultDTO<ApplyDto> res = new ResultDTO<ApplyDto>();
		Defer defer = aftersaleDao.getDeferByAuctionSetId(auctionSetId);
		AdjustPrice adjustPrice = aftersaleDao.getAdjustPriceByAuctionSetId(auctionSetId);
		BackCar backCar = aftersaleDao.getBackCarByAuctionSetId(auctionSetId);
		ApplyDto dto=new ApplyDto();
		dto.setDefer(defer);
		dto.setAdjustPrice(adjustPrice);
		dto.setBackCar(backCar);
		res.setEntity(dto);
		return res;
	}
	/**
	 * addAbnormalAudit()
	 * 新增售后审核
	 * IsPass 1:通过 -1:驳回
	 */
	@Transactional
	public ResultDTO<String> addAbnormalAudit(AbnormalAudit abnormalAudit) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(abnormalAudit==null||StringUtils.isBlank(abnormalAudit.getApplicationId())
				||null == abnormalAudit.getIsPass()) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		aftersaleDao.addAbnormalAudit(abnormalAudit);
		String applicationType = abnormalAudit.getApplicationType();
		Integer isPass = abnormalAudit.getIsPass();
		Integer isAudit = abnormalAudit.getIsPass()==1?2:-1;
		String applicationId = abnormalAudit.getApplicationId();
		String auctionId="";
		String auctionSetId="";
		String deferType ="";
		if("延期".equals(applicationType)) {
			Defer deferC = aftersaleDao.getDeferById(applicationId);
			auctionId=deferC.getAuctionId();
			auctionSetId = deferC.getAuctionSetId();
			deferType = deferC.getDeferType();
			Defer defer =new Defer(applicationId, isPass);
			aftersaleDao.updateDeferSelective(defer);
		}else if("调价".equals(applicationType)) {
			AdjustPrice adjustPriceC = aftersaleDao.getAdjustPriceById(applicationId);
			auctionId=adjustPriceC.getAuctionId();
			auctionSetId = adjustPriceC.getAuctionSetId();
			AdjustPrice adjustPrice=new AdjustPrice(applicationId, isPass);
			aftersaleDao.updateAdjustPriceSelective(adjustPrice);
		}else if("退货".equals(applicationType)) {
			BackCar backCarC = aftersaleDao.getBackCarById(applicationId);
			auctionId=backCarC.getAuctionId();
			auctionSetId = backCarC.getAuctionSetId();
			BackCar backCar =new BackCar(applicationId, isPass);
			aftersaleDao.updateBackCarSelective(backCar);
		}
		//修改售后表状态
		Aftersale aftersale=new Aftersale();
		aftersale.setAuctionSetId(auctionSetId);
		if("延期".equals(applicationType)) {
			aftersale.setDeferState(isAudit);
		}else if("调价".equals(applicationType)) {
			aftersale.setAdjustPriceState(isAudit);
		}else if("退货".equals(applicationType)) {
			aftersale.setBackCarState(isAudit);
		}
		aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
		if(isPass==1) {//通过 //修改拍品表状态
			if("延期".equals(applicationType)) {
				Auction auction=new Auction();
				auction.setId(auctionId);
				if(deferType.equals("打款延期")) {
					auction.setAftersaleState(-1);
				}else if(deferType.equals("提货延期")) {
					auction.setAftersaleState(-3);
				}else if(deferType.equals("过户延期")) {
					auction.setAftersaleState(-5);
				}
				auctionDao.updateAuctionState(auction);
			}
			if("退货".equals(applicationType)) {
				/**
				 * 更新参拍设置表，更新车辆表
				 */
				AuctionSet set = new AuctionSet();
				set.setId(auctionSetId);
				set.setOrderState(Constants.OrderStatus.RETURNCAR_AUCTION);
				auctionSetDao.updateAuctionSetSelective(set);
				Auction auction=new Auction();
				auction.setId(auctionId);
				auction.setAuctionState(Constants.AuctionStatus.RETURNCAR_AUCTION);
				auction.setAftersaleState(Constants.AuctionAftersaleState.RETURNCAR_AUCTION);
				auctionDao.updateAuctionState(auction);
				aftersale=new Aftersale();
				aftersale.setAuctionSetId(auctionSetId);
				aftersale.setTakeCarState(2);
				aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
			}
		}
		return res;
	}
	/**
	 * addScrap()
	 * 新增报废车
	 */
	@Transactional
	public ResultDTO<String> addScrap(HttpSession session,Scrap scrap) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(scrap!=null&&scrap.getAuctionId()!=null) {
			UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
			if(null == userInfoDto) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
				return res;
			}
			scrap.setOperator(userInfoDto.getRealName());
			scrap.setId(UUIDUtil.getUuid());
			scrap.setSubmitTime(new Date());
			aftersaleDao.addScrap(scrap);
			//修改车辆信息状态
			Auction auction =new Auction();
			auction.setId(scrap.getAuctionId());
			auction.setAuctionState(-1);
			auctionDao.updateAuctionState(auction);
			res.setEntity(scrap.getId());
			//新增图片
			if(StringUtils.isNotBlank(scrap.getFileIds())) {
				List<String> asList = Arrays.asList(scrap.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<AftersaleFile> list =new ArrayList<AftersaleFile>();
						AftersaleFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new AftersaleFile( 7, scrap.getId(), fileIdList.get(i), i);
							list.add(file);
						}
						aftersaleFileDao.addFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * getScrapByAuctionId()
	 * 获取报废信息
	 */
	public ResultDTO<Scrap> getScrapByAuctionId(String auctionId) {
		ResultDTO<Scrap> res = new ResultDTO<Scrap>();
		Scrap scrap = aftersaleDao.getScrapByAuctionId(auctionId);
		res.setEntity(scrap);
		return res;
	}
	/**
	 * confirmScrap()
	 * 确认报废
	 */
	@Transactional
	public ResultDTO<String> confirmScrap(Scrap scrap) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(scrap!=null&&scrap.getId()!=null) {
			scrap.setConfirmTime(new Date());
			scrap.setScrapState(1);
			scrap.setIsDelete(0);
			aftersaleDao.updateScrapSelective(scrap);
			res.setEntity(scrap.getId());
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * addSecondAsk()
	 * 新增二询
	 */
	@Transactional
	public ResultDTO<String> addSecondAsk(SecondAsk secondAsk) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(secondAsk!=null&&secondAsk.getAuctionId()!=null) {
			secondAsk.setId(UUIDUtil.getUuid());
			aftersaleDao.addSecondAsk(secondAsk);
			//修改车辆信息状态
			Auction auction =new Auction();
			auction.setId(secondAsk.getAuctionId());
			auction.setAuctionState(-8);
			auctionDao.updateAuctionState(auction);
			res.setEntity(secondAsk.getId());
			
			//新增图片
			if(StringUtils.isNotBlank(secondAsk.getFileIds())) {
				List<String> asList = Arrays.asList(secondAsk.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<AftersaleFile> list =new ArrayList<AftersaleFile>();
						AftersaleFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new AftersaleFile( 6, secondAsk.getId(), fileIdList.get(i), i);
							list.add(file);
						}
						aftersaleFileDao.addFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	public ResultDTO<Aftersale> getAftersaleByAuctionSetId(String auctionSetId) {
		ResultDTO<Aftersale> res = new ResultDTO<Aftersale>();
		Aftersale aftersale = aftersaleDao.getAftersaleByAuctionSetId(auctionSetId);
		res.setEntity(aftersale);
		return res;
	}
	
	/**
	 * addRefund()
	 * 新增退款--违约处理
	 */
	@Transactional
	public ResultDTO<String> addRefund(HttpSession session,Pay pay) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(pay == null||StringUtils.isBlank(pay.getAuctionId())
				||StringUtils.isBlank(pay.getAuctionSetId())) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		if(pay.getPayVoucherId() == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("请上传退款凭证");
			return res;
		}
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		String auctionSetId = pay.getAuctionSetId();
		AuctionSet auctionSet = auctionSetDao.getAuctionSetById(auctionSetId);
		Auction auctionC = auctionDao.getAuctionById(pay.getAuctionId());
		Pay payInfo = payDao.getPayByAuctionSetId(auctionSetId);
		String mid = payInfo.getMid();
		CustomerMargin customerMargin = customerMarginDao.getCustomerMarginById(mid);
		if(null == auctionSet||null==auctionC||null ==customerMargin) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		/**
		 * 新增退款信息
		 */
		pay.setId(UUIDUtil.getUuid());
		pay.setType("REF");
		payDao.addPay(pay);
		res.setEntity(pay.getId());
		//改状态
		Aftersale aftersale=new Aftersale();
		aftersale.setAuctionSetId(auctionSetId);
		aftersale.setPayState(2);
		aftersaleDao.updateAftersaleStateByAuctionSetId(aftersale);
		
		PayWater payWater = new PayWater(UUIDUtil.getUuid(), mid, 
				"+"+(pay.getPayMoney().intValue()), Constants.PayWaterState.REFUND,
				"违约退款", userInfoDto.getId(), auctionSetId);
		payWaterDao.addPayWater(payWater);
		return res;
	}
}

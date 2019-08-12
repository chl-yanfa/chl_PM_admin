package com.car.auction.paimai.standard.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.auction.dao.AuctionAuditDao;
import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dao.AuctionSetDao;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.organization.standard.service.PMNumberService;
import com.car.auction.paimai.dao.PaimaiDao;
import com.car.auction.paimai.dao.PaimaiWholeDao;
import com.car.auction.paimai.dto.PaimaiDto;
import com.car.auction.paimai.dto.PaimaiStatisticsDto;
import com.car.auction.paimai.entity.AuctionCar;
import com.car.auction.paimai.entity.Paimai;
import com.car.auction.paimai.entity.PaimaiHot;
import com.car.auction.paimai.entity.PaimaiInfo;
import com.car.auction.paimai.entity.PaimaiStatistics;
import com.car.auction.paimai.entity.PmAuction;
import com.car.auction.procedure.dao.ProcedureDao;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.warehouse.dao.WarehouseDao;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiService
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月16日 下午3:20:35
 * @version
 */
@Service
public class PaimaiService {
	@Autowired
	private PaimaiWholeDao paimaiWholeDao;
	@Autowired
	private PaimaiDao paimaiDao;
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AuctionAuditDao auctionAuditDao;
	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	@Autowired
	private PMNumberService pMNumberService;
	/**
	 * getPmAuctionList
	 * 根据条件获取‘拍品管理’信息
	 */
	public ResultDTO<PmAuction> getPmAuctionList(HttpSession session,PaimaiDto paimai) {
		ResultDTO<PmAuction> result = new ResultDTO<PmAuction>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<PmAuction> auctionList = paimaiWholeDao.getPmAuctionListByPage(paimai); 
		int total = paimaiWholeDao.getPmAuctionListTotal(paimai); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getPaimaiList
	 * 根据条件获取‘拍卖会管理’信息
	 */
	public ResultDTO<Paimai> getPaimaiList(HttpSession session,PaimaiDto paimai) {
		ResultDTO<Paimai> result = new ResultDTO<Paimai>();
		List<Paimai> paimaiList = paimaiDao.getPaimaiListByPage(paimai); 
		int total = paimaiDao.getPaimaiListTotal(paimai); 
		result.setRows(paimaiList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getPaimaiTab
	 * 拍卖会管理列表页TAB
	 */
	public List<Map<String, String>> getPaimaiTab(PaimaiDto paimai) {
		return paimaiDao.getPaimaiTab(paimai);
	}
	/**
	 * getPaimaiById
	 * 根据id获取‘拍卖会管理’信息
	 */
	public ResultDTO<Paimai> getPaimaiById(String id) {
		ResultDTO<Paimai> res = new ResultDTO<Paimai>();
		Paimai paimai = paimaiDao.getPaimaiById(id); 
		res.setEntity(paimai);
		return res;
	}
	/**
	 * addPaimai  新增拍卖会
	 */
	public ResultDTO<String> addPaimai(HttpSession session,Paimai paimai) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(paimai == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
		}
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
		if(null == userInfoDto) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		String pmhId = pMNumberService.getPmhID();
		paimai.setId(UUIDUtil.getUuid());
		paimai.setPmhId(pmhId);
		paimai.setCreateUser(userInfoDto.getId());
		paimaiDao.addPaimai(paimai);
		return res;
	}
	/**
	 * deletePaimai
	 * 删除拍卖会
	 */
	public ResultDTO<String> deletePaimai(String id) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(id)) {
			paimaiDao.deletePaimai(id);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * getAuctionCarList
	 * 根据条件获取‘拍卖会-拍品’信息
	 */
	public ResultDTO<AuctionCar> getAuctionCarList(String pmhId) {
		ResultDTO<AuctionCar> res = new ResultDTO<AuctionCar>();
		List<AuctionCar> list = paimaiWholeDao.getAuctionCarList(pmhId); 
		res.setRows(list);
		return res;
	}
	/**
	 * addAuctionCarList
	 * 拍卖会新增参拍车辆
	 */
	public ResultDTO<String> addAuctionCarList(Paimai paimai) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(paimai.getId())) {
			Paimai paimaiById = paimaiDao.getPaimaiById(paimai.getId());
			if(paimaiById.getPaimaiState()==2) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("拍卖会已结束");
				return res;
			}
			String selAuctionCarStr = paimai.getSelAuctionCarStr();
			String[] split = selAuctionCarStr.split(",");
			Auction auction;
			for (String auctionId : split) {
				auction=new Auction();
				auction.setId(auctionId);
				auction.setAuctionState(2);
				auctionDao.updateAuctionState(auction);
				AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
				auctionSet.setPmhId(paimaiById.getPmhId());
				auctionSet.setPmOrder(auctionSetDao.getPmOrderByPmhId(paimaiById.getPmhId()));
				if(paimaiById.getPaimaiState()==1) {//进行中
					auctionSet.setOrderState("10");
					auctionSet.setAuctionStartTime(null);
					Integer intervalTime = paimaiById.getIntervalTime();
					Integer singleTime = paimaiById.getSingleTime();//单车拍卖时长
					auctionSet.setIntervalTime(intervalTime);
					auctionSet.setSingleTime(singleTime);
				}
				auctionSetDao.updateAuctionSetAdd(auctionSet);
			}
		}
		return res;
	}
	
	/**
	 * editPmOrder
	 * 编辑拍卖序号
	 */
	public ResultDTO<String> editPmOrder(AuctionSet set) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(set.getAuctionId())) {
			AuctionSet aSet = auctionSetDao.getAuctionSetByAuctionId(set.getAuctionId());
			aSet.setPmOrder(set.getPmOrder());
			auctionSetDao.updatePmOrder(aSet);
		}
		return res;
	}
	/**
	 * batchDeleteAuctionCar
	 * 批量删除拍卖会参拍车辆
	 */
	public ResultDTO<String> batchDeleteAuctionCar(Paimai paimai) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(paimai.getSelAuctionCarStr())) {
			String selAuctionCarStr = paimai.getSelAuctionCarStr();
			String[] split = selAuctionCarStr.split(",");
			Auction auction;
			for (String auctionId : split) {
				auction=new Auction();
				auction.setId(auctionId);
				auction.setAuctionState(10);
				auctionDao.updateAuctionState(auction);
				AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
				auctionSet.setPmhId(null);
				auctionSet.setPmOrder(null);				
				auctionSet.setAuctionStartTime(null);
				auctionSet.setIntervalTime(null);
				auctionSet.setAuctionEndTime(null);
				auctionSet.setSingleTime(null);
				auctionSetDao.clearAuctionSetPmh(auctionSet);
			}
		}
		return res;
	}
	/**
	 * deleteAuctionCar
	 * 删除拍卖会参拍车辆
	 */
	public ResultDTO<String> deleteAuctionCar(String auctionId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(auctionId)) {
			Auction auction=new Auction();
			auction.setId(auctionId);
			auction.setAuctionState(10);
			auctionDao.updateAuctionState(auction);
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
			auctionSet.setPmhId(null);
			auctionSet.setPmOrder(null);				
			auctionSet.setAuctionStartTime(null);
			auctionSet.setIntervalTime(null);
			auctionSet.setAuctionEndTime(null);
			auctionSet.setSingleTime(null);
			auctionSetDao.clearAuctionSetPmh(auctionSet);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * returnAuction 撤拍
	 */
	public ResultDTO<String> returnAuction(String auctionId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(auctionId)) {
			Auction auction=new Auction();
			auction.setId(auctionId);
			auction.setAuctionState(Constants.AuctionStatus.SUBMIT);
			auction.setAuctionAuditState(0);//拍品审核状态-未审核
			auction.setAuditState(0);//（登记手续入库）审核状态-未审核
			auction.setSetState(0);//参拍设置-未设置
			auction.setRegistState(1);
			auction.setProcedureState(1);
			auction.setStockState(1);
			auctionDao.updateAuctionState(auction);
			
			//删除审核记录
			auctionAuditDao.deleteAuctionAudit(auctionId);
			//更新 [手续][入库]审核状态；
			procedureDao.updateProcedureState(auctionId);
			warehouseDao.updateWarehouseState(auctionId);
			//更改参拍设置
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
			auctionSet.setOrderState(Constants.OrderStatus.OVERTURN_AUCTION);
			auctionSet.setIsHistory(1);
			auctionSetDao.updateAuctionSetSelective(auctionSet);
			if("20".equals(auctionSet.getOrderState())) {
				/**
				 * 修改下一拍品时间
				 */
				AuctionSet nextAuctionSet = auctionSetDao.getNextAuctionSet(auctionSet.getPmhId());
				if(null!=nextAuctionSet) {
					Calendar time = new GregorianCalendar();
					time.setTime(new Date());
//					time.add(Calendar.SECOND, 3);
					nextAuctionSet.setAuctionStartTime(time.getTime());
					time.add(Calendar.SECOND, nextAuctionSet.getSingleTime());
					time.add(Calendar.SECOND, nextAuctionSet.getIntervalTime());
					nextAuctionSet.setAuctionEndTime(time.getTime());
					nextAuctionSet.setOrderState(Constants.OrderStatus.COMPETE);
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
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	
	/**
	 * againAuction
	 * 本场复拍
	 */
	@Transactional
	public ResultDTO<String> againAuction(String auctionSetId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(auctionSetId)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg("参数为空");
			return res;
		}
		AuctionSet auctionSet = auctionSetDao.getAuctionSetById(auctionSetId);
		auctionSet.setOrderState(Constants.OrderStatus.DUPLICATE_AUCTION);
		auctionSet.setIsHistory(Constants.IS_HISTORY);
		auctionSetDao.updateAuctionSetSelective(auctionSet);
		AuctionSet auctionSet2 = new AuctionSet();
		BeanUtils.copyProperties(auctionSet, auctionSet2);
		auctionSet2.setId(UUIDUtil.getUuid());
		auctionSet2.setOrderNo(pMNumberService.getOrderNo());
		auctionSet2.setPmOrder(auctionSetDao.getPmOrderByPmhId(auctionSet.getPmhId()));
		auctionSet2.setOrderState(Constants.OrderStatus.TO_AUCTION);
		auctionSet2.setIsHistory(Constants.NOT_HISTORY);
		auctionSet2.setAuctionStartTime(null);
		auctionSet2.setAuctionEndTime(null);
		auctionSetDao.addAuctionSet(auctionSet2);
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
			nextAuctionSet.setOrderState(Constants.OrderStatus.COMPETE);
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
		return res;
	}
	
	/**
	 * publishPaimai
	 * 发布拍卖会
	 */
	@Transactional
	public ResultDTO<String> publishPaimai(String paimaiId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(paimaiId)) {
			Paimai paimai = paimaiDao.getPaimaiById(paimaiId);
			paimai.setPaimaiState(1);//进行中
			paimai.setPublishTime(new Date());
			paimaiDao.updatePaimaiSelective(paimai);
			String pmhId = paimai.getPmhId();
			List<AuctionSet> list = auctionSetDao.getAuctionSetByPmhId(pmhId);
			Date auctionStartTime = paimai.getPaimaiStartTime();
			Integer intervalTime = paimai.getIntervalTime();
			Integer singleTime = paimai.getSingleTime();//单车拍卖时长
			Calendar time = new GregorianCalendar();
			time.setTime(auctionStartTime);
			int count=0;
			for (AuctionSet auctionSet : list) {
				auctionSet.setOrderState(Constants.OrderStatus.TO_AUCTION);
				if(count==0) {
					auctionSet.setAuctionStartTime(paimai.getPaimaiStartTime());
					time.add(Calendar.SECOND, singleTime);
					time.add(Calendar.SECOND, intervalTime);
					auctionSet.setAuctionEndTime(time.getTime());
				}else {
					auctionSet.setAuctionStartTime(null);
				}
				auctionSet.setIntervalTime(intervalTime);
				auctionSet.setSingleTime(singleTime);//单车拍卖时间
				auctionSetDao.updateAuctionSetPublish(auctionSet);
				count++;
				/**
				 * 更新车辆状态
				 */
				Auction auction=new Auction();
				auction.setId(auctionSet.getAuctionId());
				auction.setAuctionState(Constants.AuctionStatus.PAIMAIING);
				auctionDao.updateAuctionState(auction);
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * finishPaimai
	 * 结束拍卖会
	 */
	public ResultDTO<String> finishPaimai(String paimaiId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(paimaiId)) {
			Paimai paimai = paimaiDao.getPaimaiById(paimaiId);
			paimai.setPaimaiState(2);//进行中
			paimai.setPaimaiFinishTime(new Date());
			paimaiDao.updatePaimaiSelective(paimai);
			
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * getPaimaiInfo
	 * 
	 */
	public ResultDTO<PaimaiInfo> getPaimaiInfo(String auctionSetId) {
		ResultDTO<PaimaiInfo> res = new ResultDTO<PaimaiInfo>();
		if(StringUtils.isNotBlank(auctionSetId)) {
			PaimaiInfo paimaiInfo = paimaiDao.getPaimaiInfo(auctionSetId);
			res.setEntity(paimaiInfo);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	/**
	 * getPaimaiStatistics
	 * 获取‘拍卖会统计’信息
	 */
	public ResultDTO<PaimaiStatisticsDto> getPaimaiStatistics(String paimaiId) {
		ResultDTO<PaimaiStatisticsDto> res = new ResultDTO<PaimaiStatisticsDto>();
		Map<String,String> param=new HashMap<String,String>();
		Paimai paimai = paimaiDao.getPaimaiById(paimaiId);
		param.put("pmhId",paimai.getPmhId());
		param.put("auctionType","0");
		List<PaimaiStatistics> vehicleStatistics = paimaiDao.getPaimaiStatistics(param);
		vehicleStatistics = getList(vehicleStatistics);
		param.put("auctionType","1");
		List<PaimaiStatistics> goodsStatistics = paimaiDao.getPaimaiStatistics(param);
		goodsStatistics = getList(goodsStatistics);
		PaimaiStatistics all1 = vehicleStatistics.get(vehicleStatistics.size()-1);
		PaimaiStatistics all2 = goodsStatistics.get(goodsStatistics.size()-1);
		Integer auctionCount=all1.getAuctionCount()+all2.getAuctionCount();
		BigDecimal dealPercent=new BigDecimal("0.00");
		BigDecimal hasPricePercent=new BigDecimal("0.00");
		BigDecimal hasNoPricePercent=new BigDecimal("0.00");
		BigDecimal abortivePercent=new BigDecimal("0.00");
		if(auctionCount>0) {
			Double dealPercentD=(all1.getDealCount()+all2.getDealCount())*1d/auctionCount*100;
			dealPercent = new BigDecimal(dealPercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double hasPricePercentD=(all1.getHasPriceCount()+all2.getHasPriceCount())*1d/auctionCount*100;
			hasPricePercent = new BigDecimal(hasPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double hasNoPricePercentD=(all1.getHasNoPriceCount()+all2.getHasNoPriceCount())*1d/auctionCount*100;
			hasNoPricePercent = new BigDecimal(hasNoPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double abortivePercentD=(all1.getAbortiveCount()+all2.getAbortiveCount())*1d/auctionCount*100;
			abortivePercent = new BigDecimal(abortivePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		PaimaiStatistics all=new PaimaiStatistics("总合计", auctionCount, 
				all1.getDealCount()+all2.getDealCount(), 
				dealPercent, all1.getHasPriceCount()+all2.getHasPriceCount(), 
				hasPricePercent, all1.getHasNoPriceCount()+all2.getHasNoPriceCount(),
				hasNoPricePercent, all1.getAbortiveCount()+all2.getAbortiveCount(), 
				abortivePercent, (all1.getAveDealPrice()+all2.getAveDealPrice())/2,
				(all1.getAveProfit()+all2.getAveProfit())/2,(all1.getTotalProfit()+all2.getTotalProfit())/2);
		
		Integer paimaiPersonTotal = paimaiDao.getPaimaiPersonTotal(paimai.getPmhId());
		Integer paimaiHandleTotal = paimaiDao.getPaimaiHandleTotal(paimai.getPmhId());
		PaimaiHot paimaiHot = new PaimaiHot(paimaiPersonTotal, paimaiHandleTotal);
		
		PaimaiStatisticsDto dto=new PaimaiStatisticsDto();
		dto.setVehicleList(vehicleStatistics);
		dto.setGoodsList(goodsStatistics);
		dto.setAllStatistics(all);
		dto.setPaimaiHot(paimaiHot);
		res.setEntity(dto);
		return res;
	}
	public static List<PaimaiStatistics> getList(List<PaimaiStatistics> list){
		Integer auctionCount=0;
		Integer dealCount=0;			
		Integer hasPriceCount=0;		
		Integer hasNoPriceCount=0;		
		Integer abortiveCount=0;		
		Integer aveDealPrice=0;		/*平均成交价*/
		Integer aveProfit=0;		/*平均利润*/	
		Integer totalProfit=0;		/*总利润*/	
		for (PaimaiStatistics vehicle : list) {
			if(vehicle.getAuctionCount()>0) {
				Double dealPercentD = vehicle.getDealCount()*1d/vehicle.getAuctionCount()*100;
				vehicle.setDealPercent(new BigDecimal(dealPercentD).setScale(2,BigDecimal.ROUND_HALF_UP));
				Double hasPricePercentD = vehicle.getHasPriceCount()*1d/vehicle.getAuctionCount()*100;
				vehicle.setHasPricePercent(new BigDecimal(hasPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP));
				Double hasNoPricePercentD = vehicle.getHasNoPriceCount()*1d/vehicle.getAuctionCount()*100;
				vehicle.setHasNoPricePercent(new BigDecimal(hasNoPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP));
				Double abortivePercentD = vehicle.getAbortiveCount()*1d/vehicle.getAuctionCount()*100;
				vehicle.setAbortivePercent(new BigDecimal(abortivePercentD).setScale(2,BigDecimal.ROUND_HALF_UP));				
			}
			auctionCount+=vehicle.getAuctionCount();
			dealCount+=vehicle.getDealCount();
			hasPriceCount+=vehicle.getHasPriceCount();
			hasNoPriceCount+=vehicle.getHasNoPriceCount();
			abortiveCount+=vehicle.getAbortiveCount();
			aveDealPrice+=vehicle.getAveDealPrice();
			aveProfit+=vehicle.getAveProfit();
			totalProfit+=vehicle.getTotalProfit();
		}
		BigDecimal dealPercent = new BigDecimal("0.00");
		BigDecimal hasPricePercent = new BigDecimal("0.00");
		BigDecimal hasNoPricePercent = new BigDecimal("0.00");
		BigDecimal abortivePercent = new BigDecimal("0.00");
		if(auctionCount>0) {
			Double dealPercentD=dealCount*1d/auctionCount*100;
			dealPercent = new BigDecimal(dealPercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double hasPricePercentD=hasPriceCount*1d/auctionCount*100;
			hasPricePercent = new BigDecimal(hasPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double hasNoPricePercentD=hasNoPriceCount*1d/auctionCount*100;
			hasNoPricePercent = new BigDecimal(hasNoPricePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
			Double abortivePercentD=abortiveCount*1d/auctionCount*100;
			abortivePercent = new BigDecimal(abortivePercentD).setScale(2,BigDecimal.ROUND_HALF_UP);
		}
		PaimaiStatistics vehicleAll=new PaimaiStatistics("合计", auctionCount, dealCount, 
				dealPercent, hasPriceCount, 
				hasPricePercent, hasNoPriceCount,
				hasNoPricePercent, abortiveCount, 
				abortivePercent, aveDealPrice/list.size(), aveProfit/list.size(), totalProfit/list.size());
		list.add(vehicleAll);
		return list;
	}
	/**
	 * deleteAuction
	 * 删除拍品
	 */
	public ResultDTO<String> deleteAuction(String auctionId) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(auctionId)) {
			Auction auction=new Auction();
			auction.setId(auctionId);
			auction.setAuctionState(1);//未上拍
			auction.setSetState(0);//参拍设置-未设置
			auction.setRegistState(1);
			auction.setProcedureState(1);
			auction.setStockState(1);
			auctionDao.updateAuctionState(auction);
			//更改参拍设置
			AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionId);
			auctionSet.setIsDelete(1);//删除
			auctionSetDao.updateAuctionSetSelective(auctionSet);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
}

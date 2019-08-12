package com.car.auction.auction.standard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dao.AuctionFileDao;
import com.car.auction.auction.dao.AuctionSetDao;
import com.car.auction.auction.dao.AuctionWholeDao;
import com.car.auction.auction.dto.AuctionDto;
import com.car.auction.auction.dto.EntityDto;
import com.car.auction.auction.entity.Auction;
import com.car.auction.auction.entity.AuctionFile;
import com.car.auction.auction.entity.AuctionSave;
import com.car.auction.auction.entity.AuctionScrap;
import com.car.auction.auction.entity.AuctionSet;
import com.car.auction.auction.entity.AuctionWhole;
import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.organization.standard.service.PMNumberService;
import com.car.auction.procedure.dao.ProcedureDao;
import com.car.auction.procedure.entity.Procedure;
import com.car.auction.scraporder.dao.CarScrapOrderAutopartsDao;
import com.car.auction.scraporder.dao.CarScrapOrderDao;
import com.car.auction.scraporder.standard.service.CarScrapOrderService;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.warehouse.dao.WarehouseDao;
import com.car.auction.warehouse.entity.Warehouse; 

@Service
public class AuctionService {
	
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private AuctionFileDao auctionFileDao;
	@Autowired
	private AuctionSetDao auctionSetDao;
	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private PMNumberService pMNumberService;
	@Autowired
	private AuctionWholeDao auctionWholeDao;
	@Autowired
	private CarScrapOrderService carScrapOrderService;
	@Autowired
	private CarScrapOrderDao carScrapOrderDao;
	@Autowired
	private CarScrapOrderAutopartsDao carScrapOrderAutopartsDao;
	
	/**
	 * addAuction(新增拍品)
	 */
	@Transactional
	public ResultDTO<String> addAuction(HttpSession session,Auction auction) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null == auction || auction.getDataState() == null){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		if(auction.getDataState() == 0) {
			auction.setAuctionState(0);
			auction.setRegistState(0);
		}else {
			auction.setAuctionState(1);
			auction.setRegistState(1);
		}
		Integer auctionType = auction.getAuctionType();
		//对VIN中字母转换
		if(StringUtils.isNotBlank(auction.getVin())) {
			auction.setVin(auction.getVin().toUpperCase());				
		}
		String auctionNo = pMNumberService.getAuctionNo();
		auction.setAuctionNo(auctionNo);
		//新增报价信息
		auction.setId(UUIDUtil.getUuid());
		auctionDao.insertAuction(auction);
		
		//新增图片
		if(StringUtils.isNotBlank(auction.getFileIds())) {
			List<String> asList = Arrays.asList(auction.getFileIds().split(","));
			if(asList!=null && asList.size()>0) {
				List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
				if(fileIdList!=null && fileIdList.size()>0) {
					List<AuctionFile> list =new ArrayList<AuctionFile>();
					AuctionFile file;
					for (int i = 0;i<fileIdList.size();i++) {
						file = new AuctionFile( auction.getId(), 0, fileIdList.get(i), i, 0, null);
						list.add(file);
					}
					auctionFileDao.addAuctionFileBatch(list);
				}
				
			}
		}
		//设置整车/配件
		if(auctionType == 0) {
			String scrapOrderId = auction.getScrapOrderId();
			if(StringUtils.isNotBlank(scrapOrderId)) {
				//修改状态以及绑定id
				carScrapOrderService.addScrapOrder(Constants.ScrapOrderStatus.SCRAP_CAR, scrapOrderId, auction.getId(),userInfo.getId());
			}
		}else {
			List<String> autopartsIdList = auction.getAutopartsIdList();
			if(autopartsIdList != null && autopartsIdList.size()>0) {
				for (String autopartsId : autopartsIdList) {
					//修改状态以及绑定id
					carScrapOrderService.addScrapOrder(Constants.ScrapOrderStatus.SCRAP_AUTOPARTS, autopartsId, auction.getId(),userInfo.getId());
				}
			}
		}
		
		return res;
	}
	
	/**
	 * updateAuction(编辑拍品信息)
	 */
	@Transactional
	public ResultDTO<String> updateAuction(Auction auction) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(auction==null || StringUtils.isBlank(auction.getId()) || auction.getDataState() == null){
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Auction auctionDB = auctionDao.getAuctionById(auction.getId());
		if(auctionDB == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_ERROR);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_ERROR);
			return res;
		}
		//对VIN中字母转换
		if(StringUtils.isNotBlank(auction.getVin())) {
			auction.setVin(auction.getVin().toUpperCase());				
		}
		if(auction.getDataState() == 1) { //提交
			if(auction.getDataState()==1){
				if(auctionDB.getAuctionState()==0) {
					auction.setAuctionState(1);//提交 - 车辆列表	
					auction.setRegistState(1);
				}
			}
		}
		//编辑报价信息
		auctionDao.updateAuction(auction);
		return res;
	}
	/**
	 * deleteAuction(删除拍品信息)
	 */
	@Transactional
	public ResultDTO<String> deleteAuction(HttpSession session,String id) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isBlank(id)){
		}
		Auction auctionDB = auctionDao.getAuctionById(id);
		if(auctionDB==null) {			
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		auctionDB.setIsDelete(1);
		//编辑报价信息
		auctionDao.updateAuctionSel(auctionDB);
		//删除配件、车辆
		if(auctionDB.getAuctionType() == 0) {
			String orderId = carScrapOrderDao.getOrderIdByAuctionId(id);
			carScrapOrderService.deleteScrapOrder(Constants.ScrapOrderStatus.SCRAP_CAR, 
					orderId, id, userInfo.getId());
		}else {
			List<String> autopartsIdList = carScrapOrderAutopartsDao.getAutopartsIdByAuctionId(id);
			for (String autopartsId : autopartsIdList) {
				carScrapOrderService.deleteScrapOrder(Constants.ScrapOrderStatus.SCRAP_AUTOPARTS, 
						autopartsId, id, userInfo.getId());
			}
		}
		return res;
	}
	/**
	 * getAuctionById(根据id查询拍品信息)
	 */
	public Auction getAuctionById(String id) {
		return auctionDao.getAuctionById(id);
	}
	public ResultDTO<EntityDto> getVPWById(String id) {
		ResultDTO<EntityDto> res = new ResultDTO<EntityDto>();
		EntityDto dto=new EntityDto();
		Auction auction = auctionDao.getAuctionById(id);
		Procedure procedure = procedureDao.getProcedureByAuctionId(id);
		Warehouse warehouse = warehouseDao.getWarehouseByAuctionId(id);
		dto.setAuction(auction);
		dto.setProcedure(procedure);
		dto.setWarehouse(warehouse);
		res.setEntity(dto);
		return res;
	}
	/**
	 * getAuctionWholeList()
	 * 根据条件获取‘车辆列表’信息
	 */
	public ResultDTO<AuctionWhole> getAuctionList(HttpSession session,AuctionDto dto) {
		ResultDTO<AuctionWhole> result = new ResultDTO<AuctionWhole>();
		if(dto.getAuctionState()!=null&&dto.getAuctionState()==1) {
			List<Integer> as=new ArrayList<Integer>();
			as.add(1);
			as.add(10);
			dto.setAuctionStateList(as);
			dto.setAuctionState(null);
		}else if(dto.getAuctionState()!=null&&dto.getAuctionState()==6) {
			List<Integer> as=new ArrayList<Integer>();
			as.add(5);
			as.add(8);
			dto.setAuctionStateList(as);
			dto.setAuctionState(null);
		}
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		if(dto.getAuctionState()!=null&&dto.getAuctionState()==7) {
			List<AuctionWhole> auctionList = auctionWholeDao.getAuctionFinishListByPage(dto); 
			if(auctionList!=null&&auctionList.size()>0) {
				for (AuctionWhole auctionWhole : auctionList) {
					AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionWhole.getId());
					if(null!=auctionSet) {
						auctionWhole.setPaimaiDate(auctionSet.getAuctionStartTime());
					}
				}
			}
			int total = auctionWholeDao.getAuctionFinishListTotal(dto);
			result.setRows(auctionList);
			result.setTotal(total);
			return result;
		}
		List<AuctionWhole> auctionList = auctionWholeDao.getAuctionWholeListByPage(dto); 
		if(auctionList!=null&&auctionList.size()>0) {
			for (AuctionWhole auctionWhole : auctionList) {
				AuctionSet auctionSet = auctionSetDao.getAuctionSetByAuctionId(auctionWhole.getId());
				if(null!=auctionSet) {
					auctionWhole.setPaimaiDate(auctionSet.getAuctionStartTime());
				}
			}
		}
		int total = auctionWholeDao.getAuctionWholeListTotal(dto); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getAuctionList()
	 * 根据条件获取暂存车辆，报废车辆列表信息
	 */
	public ResultDTO<AuctionSave> getAuctionSaveList(HttpSession session,AuctionDto auction) {
		ResultDTO<AuctionSave> result = new ResultDTO<AuctionSave>();
		auction.setAuctionState(0);
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<AuctionSave> auctionList = auctionWholeDao.getAuctionSaveListByPage(auction); 
		int total = auctionWholeDao.getAuctionSaveListTotal(auction); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getCarTabCount()
	 * 获取车辆列表TAB
	 */
	public List<Map<String, String>> getAuctionTab(HttpSession session,AuctionDto dto) {
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		if(dto == null) {
			dto = new AuctionDto();
		}
		return auctionWholeDao.getAuctionWholeTab(dto);
	}
	/**
	 * getAuctionScrapList()
	 * 根据条件获取报废车辆，报废车辆列表信息
	 */
	public ResultDTO<AuctionScrap> getAuctionScrapList(HttpSession session,AuctionDto auction) {
		ResultDTO<AuctionScrap> result = new ResultDTO<AuctionScrap>();
		auction.setAuctionState(-1);
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return result;
		}
		List<AuctionScrap> auctionList = auctionWholeDao.getScrapCarListByPage(auction);
		int total = auctionWholeDao.getScrapCarListTotal(auction); 
		result.setRows(auctionList);
		result.setTotal(total);
		return result;
	}
	/**
	 * getScrapCarTabCount()
	 * 获取报废列表TAB
	 */
	public List<Map<String, String>> getScrapCarTab(HttpSession session,AuctionDto auction) {
		if(auction == null) {
			auction = new AuctionDto();
		}
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		return auctionWholeDao.getScrapCarTab(auction);
	}
}

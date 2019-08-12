package com.car.auction.warehouse.standard.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dto.EntityDto;
import com.car.auction.auction.entity.Auction;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.warehouse.dao.WarehouseDao;
import com.car.auction.warehouse.dao.WarehouseFileDao;
import com.car.auction.warehouse.dao.WarehousePlacingAuditDao;
import com.car.auction.warehouse.dao.WarehousePlacingDao;
import com.car.auction.warehouse.dao.WarehouseWholeDao;
import com.car.auction.warehouse.dto.WarehouseDto;
import com.car.auction.warehouse.entity.StockStatistics;
import com.car.auction.warehouse.entity.StockTakeCar;
import com.car.auction.warehouse.entity.TakeCarBill;
import com.car.auction.warehouse.entity.Warehouse;
import com.car.auction.warehouse.entity.WarehouseFile;
import com.car.auction.warehouse.entity.WarehousePlacing;
import com.car.auction.warehouse.entity.WarehousePlacingAudit;
import com.car.auction.warehouse.entity.WarehouseSave;
import com.car.auction.warehouse.entity.WarehouseWhole;
import com.car.auction.warehouse.vo.WarehouseVo;


/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseService
 * 类描述：入库管理
 * 创建人：张婉欣
 * 创建时间： 2018年8月8日 下午5:17:43
 * @version
 */
@Service
public class WarehouseService {
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private WarehouseWholeDao warehouseWholeDao;
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private WarehouseFileDao warehouseFileDao;
	@Autowired
	private WarehousePlacingDao warehousePlacingDao;
	@Autowired
	private WarehousePlacingAuditDao warehousePlacingAuditDao;
	
	/**
	 * getWarehouseList
	 * 仓库管理列表查询
	 */
	public ResultDTO<WarehouseWhole> getWarehouseList(HttpSession session,WarehouseDto warehouseWhole) {
		ResultDTO<WarehouseWhole> res = new ResultDTO<WarehouseWhole>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		List<WarehouseWhole> list = warehouseWholeDao.getWarehouseListByPage(warehouseWhole);
		res.setRows(list);
		int total = warehouseWholeDao.getWarehouseListTotal(warehouseWhole);
		res.setTotal(total);
		return res;
	}
	/**
	 * getWarehouseTab
	 * 仓库管理列表页TAB
	 */
	public List<Map<String, String>> getWarehouseTab(HttpSession session,WarehouseDto warehouseWhole) {
		if(warehouseWhole == null) {
			warehouseWhole = new WarehouseDto();
		}
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			return null;
		}
		return warehouseWholeDao.getWarehouseTab(warehouseWhole);
	}
	/**
	 * addWarehouse 车辆入库
	 */
	public ResultDTO<String> addWarehouse(HttpServletRequest request,WarehouseVo vo) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(vo!=null) {
			Warehouse warehouse = new Warehouse();
			BeanUtils.copyProperties(vo, warehouse);
			if(warehouse.getDataState()==0) {
				warehouse.setIsWarehouse(-1);//暂存入库			
			}else {
				warehouse.setIsWarehouse(1);//入库	
				warehouse.setAuditState(1);//2018年11月30日14:20:02 更改：0-1  入库免审核
			}
			UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
			String userName=userInfoDto==null?"":userInfoDto.getRealName();
			warehouse.setSalesman(userName);
			warehouse.setIsDelete(0);
			if(StringUtils.isNotBlank(warehouse.getId())) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
				return res;
			}
			Warehouse ware = warehouseDao.getWarehouseByAuctionId(warehouse.getAuctionId());
			if(null!=ware) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
				return res;
			}
			//新增
			warehouse.setId(UUIDUtil.getUuid());
			warehouseDao.addWarehouse(warehouse);
			Auction auction=new Auction();
			auction.setId(warehouse.getAuctionId());
			if(warehouse.getDataState()==0) {
				auction.setStockState(-1);//
			}else {
				auction.setStockState(1);//已入库
			}
			auctionDao.updateAuctionState(auction);	
			//新增图片
			if(StringUtils.isNotBlank(vo.getFileIds())) {
				List<String> asList = Arrays.asList(vo.getFileIds().split(","));
				if(asList!=null && asList.size()>0) {
					List<Integer> fileIdList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
					if(fileIdList!=null && fileIdList.size()>0) {
						List<WarehouseFile> list =new ArrayList<WarehouseFile>();
						WarehouseFile file;
						for (int i = 0;i<fileIdList.size();i++) {
							file = new WarehouseFile(warehouse.getId(), 0, fileIdList.get(i), i);
							list.add(file);
						}
						warehouseFileDao.addWarehouseFileBatch(list);
					}
					
				}
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("参数为空");
		}
		return res;
	}
	
	/**
	 * transToWarehouse 转为入库
	 */
	public ResultDTO<String> transToWarehouse(Warehouse warehouse) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(warehouse!=null) {
			warehouse.setAuditState(0);
			warehouse.setIsWarehouse(1);//已入库
			warehouse.setIsDelete(0);
			if(StringUtils.isNotBlank(warehouse.getId())) {
				//编辑
				warehouseDao.updateWarehouse(warehouse);
				//修改拍品入库状态
				Auction auction=new Auction();
				auction.setId(warehouse.getAuctionId());
				auction.setStockState(1);//已入库
				auctionDao.updateAuctionState(auction);	
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("请传入入库id");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		return res;
	}
	
	/**
	 * getWarehouseById()
	 * 根据id查询车辆入库信息
	 */
	public ResultDTO<Warehouse> getWarehouseById(String id) {
		ResultDTO<Warehouse> res = new ResultDTO<Warehouse>();
		Warehouse warehouse = warehouseDao.getWarehouseById(id);
		res.setEntity(warehouse);
		return res;
	}
	/**
	 * getWarehouseByAuctionId
	 * 根据auctionId查询车辆入库信息
	 */
	public ResultDTO<Warehouse> getWarehouseByAuctionId(String auctionId) {
		ResultDTO<Warehouse> res = new ResultDTO<Warehouse>();
		Warehouse warehouse = warehouseDao.getWarehouseByAuctionId(auctionId);
		res.setEntity(warehouse);
		return res;
	}
	/**
	 * getAuctionWarehouseById
	 * 根据auctionId查询
	 */
	public ResultDTO<EntityDto> getAuctionWarehouseById(String auctionId) {
		ResultDTO<EntityDto> res = new ResultDTO<EntityDto>();
		EntityDto dto=new EntityDto();
		Warehouse warehouse = warehouseDao.getWarehouseByAuctionId(auctionId);
		Auction auction = auctionDao.getAuctionById(auctionId);
		dto.setAuction(auction);
		dto.setWarehouse(warehouse);
		res.setEntity(dto);
		return res;
	}
	/**
	 * getAuctionSaveWarehouse
	 * 根据auctionId查询
	 */
	public ResultDTO<EntityDto> getAuctionSaveWarehouse(String auctionId) {
		ResultDTO<EntityDto> res = new ResultDTO<EntityDto>();
		EntityDto dto=new EntityDto();
		Warehouse warehouse = warehouseDao.getSaveWarehouseByAuctionId(auctionId);
		Auction auction = auctionDao.getAuctionById(auctionId);
		dto.setAuction(auction);
		dto.setWarehouse(warehouse);
		res.setEntity(dto);
		return res;
	}
	/**
	 * outOfWarehouseAbnormal()
	 * 车辆异常出库
	 */
	public ResultDTO<String> outOfWarehouseAbnormal(HttpServletRequest request,WarehousePlacing warehousePlacing) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(warehousePlacing!=null&&StringUtils.isNotBlank(warehousePlacing.getStockId())) {
			UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
			warehousePlacing.setId(UUIDUtil.getUuid());
			warehousePlacing.setProposer(userInfoDto==null?null:userInfoDto.getRealName());
			warehousePlacing.setAuditState(1);//待审核
			warehousePlacingDao.addWarehousePlacing(warehousePlacing);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入入库id（stockId）");
			return res;
		}
		return res;
	}
	/**
	 * getOutOfWarehouseToAudit
	 * 查询车辆异常出库待审核信息
	 */
	public ResultDTO<WarehousePlacing> getOutOfWarehouseToAudit(String stockId) {
		ResultDTO<WarehousePlacing> res = new ResultDTO<WarehousePlacing>();
		if(StringUtils.isNotBlank(stockId)) {
			WarehousePlacing dto = warehousePlacingDao.getWarehousePlacingToAudit(stockId);
			if(dto!=null) {
				res.setEntity(dto);
				return res;							
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("没有要审核的车辆异常出库信息，或者请传入正确的入库id（stockId）");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入入库id（stockId）");
			return res;
		}
	}
	/**
	 * addPlacingAudit
	 * 新增车辆异常出库审核数据
	 */
	public ResultDTO<String> addPlacingAudit(WarehousePlacingAudit warehousePlacingAudit) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(warehousePlacingAudit!=null&&StringUtils.isNotBlank(warehousePlacingAudit.getPlacingId())) {
			String placingId = warehousePlacingAudit.getPlacingId();
			WarehousePlacing placing = warehousePlacingDao.getWarehousePlacingById(placingId);
			if(placing!=null) {
				Integer isPass = warehousePlacingAudit.getIsPass();
				if(isPass==0) {//通过
					placing.setAuditState(2);
				}else {//驳回
					placing.setAuditState(-2);
				}
				//修改审核状态
				warehousePlacingDao.updateWarehousePlacingSelective(placing);
				warehousePlacingAudit.setId(UUIDUtil.getUuid());
				//新增审核数据
				warehousePlacingAuditDao.addPlacingAudit(warehousePlacingAudit);
				if(isPass==0) {
					//修改拍品入库状态
					Warehouse warehouse = warehouseDao.getWarehouseById(placing.getStockId());
					Auction auction=new Auction();
					auction.setId(warehouse.getAuctionId());
					auction.setStockState(2);//已出库
					auctionDao.updateAuctionState(auction);
					//修改入库表状态
					Warehouse warehouse2 =new Warehouse();
					warehouse2.setId(warehouse.getId());
					warehouse2.setIsWarehouse(2);
					warehouse2.setOutStockTime(placing.getCarPlacingTime());
					warehouseDao.updateWarehouseSelective(warehouse2);
				}
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("没有待审核出库数据，或请传入正确的待审核出库数据的id");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入待审核出库数据的id");
			return res;
		}
		return res;
	}
	/**
	 * outOfWarehouse
	 * 车辆办理出库
	 */
	public ResultDTO<String> outOfWarehouse(HttpServletRequest request, WarehousePlacing warehousePlacing) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(warehousePlacing!=null&&StringUtils.isNotBlank(warehousePlacing.getStockId())) {
			Warehouse warehouse = warehouseDao.getWarehouseById(warehousePlacing.getStockId());
			if(warehouse.getIsPrint()==1) {//已打印提货单
				UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
				warehousePlacing.setProposer(userInfoDto==null?null:userInfoDto.getRealName());
				warehousePlacing.setAuditState(0);//无需审核
				warehousePlacing.setId(UUIDUtil.getUuid());
				warehousePlacingDao.addWarehousePlacing(warehousePlacing);
				//修改拍品入库状态
				Auction auction=new Auction();
				auction.setId(warehouse.getAuctionId());
				auction.setStockState(2);//已出库
				auctionDao.updateAuctionState(auction);
				//修改入库表状态
				Warehouse warehouse2 =new Warehouse();
				warehouse2.setId(warehouse.getId());
				warehouse2.setIsWarehouse(2);
				warehouse2.setOutStockTime(warehousePlacing.getCarPlacingTime());
				warehouseDao.updateWarehouseSelective(warehouse2);
				return res;
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("请先打印提货单");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入入库id（stockId）");
			return res;
		}
	}
	/**
	 * getWarehouseSaveList
	 * 暂存入库列表查询
	 */
	public ResultDTO<WarehouseSave> getWarehouseSaveList(HttpSession session,WarehouseDto warehouseWhole) {
		ResultDTO<WarehouseSave> res = new ResultDTO<WarehouseSave>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		List<WarehouseSave> list = warehouseWholeDao.getWarehouseSaveListByPage(warehouseWhole);
		res.setRows(list);
		int total = warehouseWholeDao.getWarehouseSaveListTotal(warehouseWhole);
		res.setTotal(total);
		return res;
	}
	
	
	/**
	 * getTakeCarList
	 * 获取打印提货单列表页
	 */
	public ResultDTO<StockTakeCar> getTakeCarList(HttpSession session,WarehouseDto warehouseWhole) {
		ResultDTO<StockTakeCar> res = new ResultDTO<StockTakeCar>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		List<StockTakeCar> list = warehouseWholeDao.getTakeCarListByPage(warehouseWhole);
		res.setRows(list);
		int total = warehouseWholeDao.getTakeCarListTotal(warehouseWhole);
		res.setTotal(total);
		return res;
	}
	/**
	 * getTakeCarBill
	 * 获取提货单
	 */
	public ResultDTO<TakeCarBill> getTakeCarBill(String auctionId) {
		ResultDTO<TakeCarBill> res = new ResultDTO<TakeCarBill>();
		TakeCarBill takeCarBill = warehouseWholeDao.getTakeCarBill(auctionId);
		res.setEntity(takeCarBill);
		return res;
	}
	/**
	 * getStockStatistics
	 * 获取库存统计
	 */
	public ResultDTO<StockStatistics> getStockStatistics(HttpSession session,WarehouseDto warehouseWhole) {
		ResultDTO<StockStatistics> res = new ResultDTO<StockStatistics>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		if(warehouseWhole == null) {
			warehouseWhole = new WarehouseDto();
		}
		List<StockStatistics> list = warehouseWholeDao.getStockStatistics(warehouseWhole);
		for (StockStatistics stockStatistics : list) {
			int one=stockStatistics.getNoTakeCarCount();
			int two=stockStatistics.getNoWarehouseCount();
			int three=stockStatistics.getPaimaiCount();
			int four=stockStatistics.getPassInCount();
			int five=stockStatistics.getToPaimaiCount();
			stockStatistics.setStatistics(one+two+three+four+five);
		}
		res.setRows(list);
		int total = warehouseWholeDao.getStockStatisticsTotal(warehouseWhole);
		res.setTotal(total);
		return res;
	}
	public int updateWarehouse(Warehouse warehouse) {
		return warehouseDao.updateWarehouseSelective(warehouse);
	}
	/*public int addWarehousePhotoBatch(List<WarehouseFile> list) {
		return warehousePhotoDao.addWarehousePhotoBatch(list);
	}
	public int addWarehousePhoto(WarehousePhoto photo) {
		return warehousePhotoDao.addWarehousePhoto(photo);
	}*/
}

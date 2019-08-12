package com.car.auction.warehouse.standard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.auction.dto.EntityDto;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.warehouse.dto.WarehouseDto;
import com.car.auction.warehouse.entity.StockStatistics;
import com.car.auction.warehouse.entity.StockTakeCar;
import com.car.auction.warehouse.entity.TakeCarBill;
import com.car.auction.warehouse.entity.Warehouse;
import com.car.auction.warehouse.entity.WarehousePlacing;
import com.car.auction.warehouse.entity.WarehousePlacingAudit;
import com.car.auction.warehouse.entity.WarehouseSave;
import com.car.auction.warehouse.entity.WarehouseWhole;
import com.car.auction.warehouse.standard.service.WarehouseService;
import com.car.auction.warehouse.vo.WarehouseVo;


/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseController
 * 类描述：入库管理
 * 创建人：张婉欣
 * 创建时间： 2018年8月8日 下午5:23:40
 * @version
 */
@Controller
@RequestMapping(value = "/warehouse")
public class WarehouseController extends BaseController{
	
	@Autowired
	private WarehouseService warehouseService;
	/**
	 * getWarehouseList
	 * 仓库管理列表查询
	 */
	@RequestMapping(value = "/getWarehouseList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<WarehouseWhole> getWarehouseList(HttpSession session,WarehouseDto warehouseWhole) {
		return warehouseService.getWarehouseList(session,warehouseWhole);
	}
	/**
	 * getWarehouseTab
	 * 仓库管理列表页TAB
	 */
	@RequestMapping(value = "/getWarehouseTab", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, String>> getWarehouseTab(HttpSession session,WarehouseDto warehouseWhole) {
		return warehouseService.getWarehouseTab(session,warehouseWhole);
	}
	/**
	 * getWarehouseById
	 * 根据id查询车辆入库信息
	 */
	@RequestMapping(value = "/getWarehouseById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Warehouse> getWarehouseById(String id) {
		return warehouseService.getWarehouseById(id);
	}
	/**
	 * getWarehouseByAuctionId
	 * 根据auctionId查询车辆入库信息
	 */
	@RequestMapping(value = "/getWarehouseByAuctionId", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Warehouse> getWarehouseByAuctionId(String auctionId) {
		return warehouseService.getWarehouseByAuctionId(auctionId);
	}
	/**
	 * getWarehouseByAuctionId
	 * 根据auctionId查询车辆入库信息
	 */
	@RequestMapping(value = "/getAuctionWarehouseById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<EntityDto> getAuctionWarehouseById(String auctionId) {
		return warehouseService.getAuctionWarehouseById(auctionId);
	}
	/**
	 * 根据auctionId查询车辆暂存入库信息
	 */
	@RequestMapping(value = "/getAuctionSaveWarehouse", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<EntityDto> getAuctionSaveWarehouse(String auctionId) {
		return warehouseService.getAuctionSaveWarehouse(auctionId);
	}
	/**
	 * addWarehouse
	 * 车辆入库
	 */
	@RequestMapping(value = "/addWarehouse", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addWarehouse(HttpServletRequest request,WarehouseVo warehouseVo) {
		return warehouseService.addWarehouse(request,warehouseVo);
	}
	/**
	 * transToWarehouse
	 * 转为入库
	 */
	@RequestMapping(value = "/transToWarehouse", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> transToWarehouse(Warehouse warehouse) {
		return warehouseService.transToWarehouse(warehouse);
	}
	/**
	 * outOfWarehouseAbnormal
	 * 车辆异常出库
	 */
	@RequestMapping(value = "/outOfWarehouseAbnormal", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> outOfWarehouseAbnormal(HttpServletRequest request,WarehousePlacing warehousePlacing) {
		return warehouseService.outOfWarehouseAbnormal(request,warehousePlacing);
	}
	/**
	 * getOutOfWarehouseToAudit
	 * 查询车辆异常出库待审核数据
	 */
	@RequestMapping(value = "/getOutOfWarehouseToAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<WarehousePlacing> getOutOfWarehouseToAudit(String stockId) {
		return warehouseService.getOutOfWarehouseToAudit(stockId);
	}
	/**
	 * addPlacingAudit
	 * 新增车辆异常出库审核数据
	 */
	@RequestMapping(value = "/addPlacingAudit", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addPlacingAudit(WarehousePlacingAudit warehousePlacingAudit) {
		return warehouseService.addPlacingAudit(warehousePlacingAudit);
	}
	/**
	 * outOfWarehouse
	 * 车辆办理出库
	 */
	@RequestMapping(value = "/outOfWarehouse", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> outOfWarehouse(HttpServletRequest request,WarehousePlacing warehousePlacing) {
		return warehouseService.outOfWarehouse(request,warehousePlacing);
	}
	/**
	 * getWarehouseSaveList
	 * 暂存入库列表查询
	 */
	@RequestMapping(value = "/getWarehouseSaveList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<WarehouseSave> getWarehouseSaveList(HttpSession session,WarehouseDto warehouseWhole) {
		return warehouseService.getWarehouseSaveList(session,warehouseWhole);
	}
	
	/**
	 * getTakeCarList
	 * 获取打印提货单列表页
	 */
	@RequestMapping(value = "/getTakeCarList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<StockTakeCar> getTakeCarList(HttpSession session,WarehouseDto warehouseWhole) {
		return warehouseService.getTakeCarList(session,warehouseWhole);
	}
	/**
	 * getTakeCarBill
	 * 获取提货单
	 */
	@RequestMapping(value = "/getTakeCarBill", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<TakeCarBill> getTakeCarBill(String auctionId) {
		return warehouseService.getTakeCarBill(auctionId);
	}
	/**
	 * getStockStatistics
	 * 获取库存统计
	 */
	@RequestMapping(value = "/getStockStatistics", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<StockStatistics> getStockStatistics(HttpSession session,WarehouseDto warehouseWhole) {
		return warehouseService.getStockStatistics(session,warehouseWhole);
	}
}

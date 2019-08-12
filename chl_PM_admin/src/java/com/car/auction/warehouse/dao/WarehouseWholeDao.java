package com.car.auction.warehouse.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.dto.WarehouseDto;
import com.car.auction.warehouse.entity.StockStatistics;
import com.car.auction.warehouse.entity.StockTakeCar;
import com.car.auction.warehouse.entity.TakeCarBill;
import com.car.auction.warehouse.entity.WarehouseSave;
import com.car.auction.warehouse.entity.WarehouseWhole;


/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseWholeDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月11日 下午1:43:05
 * @version
 */
@Component
public class WarehouseWholeDao {
	
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询仓库管理列表
	 */
	public List<WarehouseWhole> getWarehouseListByPage(WarehouseDto warehouseWhole) {
		int start = 0;
		int length = 10;
		if(warehouseWhole.getPage()!=null) {
			start = warehouseWhole.getPage();			
		}
		if(warehouseWhole.getPage()!=null) {
			length = warehouseWhole.getRows();			
		}
		return baseDao.findList("mapper.standard.WarehouseWholeMapper.getWarehouseListByPage", warehouseWhole,start,length);
	}
	/**
	 * 查询仓库管理列表总条数
	 */
	public int getWarehouseListTotal(WarehouseDto warehouseWhole) {
		return baseDao.findOne("mapper.standard.WarehouseWholeMapper.getWarehouseListTotal", warehouseWhole);
	}
	/**
	 * 查询仓库管理列表TAB
	 */
	public List<Map<String, String>> getWarehouseTab(WarehouseDto warehouseWhole) {
		return baseDao.findList("mapper.standard.WarehouseWholeMapper.getWarehouseTab", warehouseWhole);
	}
	/**
	 * 获取暂存入库列表
	 */
	public List<WarehouseSave> getWarehouseSaveListByPage(WarehouseDto warehouseWhole) {
		int start = 0;
		int length = 10;
		if(warehouseWhole.getPage()!=null) {
			start = warehouseWhole.getPage();			
		}
		if(warehouseWhole.getPage()!=null) {
			length = warehouseWhole.getRows();			
		}
		return baseDao.findList("mapper.standard.WarehouseWholeMapper.getWarehouseSaveListByPage", warehouseWhole,start,length);
	}
	/**
	 * 查询暂存入库列表总条数
	 */
	public int getWarehouseSaveListTotal(WarehouseDto warehouseWhole) {
		return baseDao.findOne("mapper.standard.WarehouseWholeMapper.getWarehouseSaveListTotal", warehouseWhole);
	}
	/**
	 * getTakeCarListByPage
	 */
	public List<StockTakeCar> getTakeCarListByPage(WarehouseDto warehouseWhole) {
		int start = 0;
		int length = 10;
		if(warehouseWhole.getPage()!=null) {
			start = warehouseWhole.getPage();			
		}
		if(warehouseWhole.getPage()!=null) {
			length = warehouseWhole.getRows();			
		}
		return baseDao.findList("mapper.standard.WarehouseWholeMapper.getTakeCarListByPage", warehouseWhole,start,length);
	}
	/**
	 * getTakeCarListTotal
	 */
	public int getTakeCarListTotal(WarehouseDto warehouseWhole) {
		return baseDao.findOne("mapper.standard.WarehouseWholeMapper.getTakeCarListTotal", warehouseWhole);
	}
	/**
	 * getTakeCarBill
	 */
	public TakeCarBill getTakeCarBill(String auctionId) {
		return baseDao.findOne("mapper.standard.WarehouseWholeMapper.getTakeCarBill", auctionId);
	}
	/**
	 * getStockStatistics
	 */
	public List<StockStatistics> getStockStatistics(WarehouseDto warehouseWhole) {
		return baseDao.findList("mapper.standard.WarehouseWholeMapper.getStockStatistics",warehouseWhole);
	}
	public int getStockStatisticsTotal(WarehouseDto warehouseWhole) {
		return baseDao.findOne("mapper.standard.WarehouseWholeMapper.getStockStatisticsTotal", warehouseWhole);
	}
}

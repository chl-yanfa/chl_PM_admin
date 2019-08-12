package com.car.auction.warehouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.entity.Warehouse;



/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseDao
 * 类描述：车辆入库登记
 * 创建人：张婉欣
 * 创建时间： 2018年8月8日 下午5:17:20
 * @version
 */
@Component
public class WarehouseDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id查询车辆入库信息
	 */
	public Warehouse getWarehouseById(String id) {
		return baseDao.findOne("mapper.standard.WarehouseMapper.getWarehouseById", id);
	}
	/**
	 * 根据auctionId查询车辆入库信息
	 */
	public Warehouse getWarehouseByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.WarehouseMapper.getWarehouseByAuctionId", auctionId);
	}
	/**
	 * 根据auctionId查询车辆暂存入库信息
	 */
	public Warehouse getSaveWarehouseByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.WarehouseMapper.getSaveWarehouseByAuctionId", auctionId);
	}
	/**
	 * 车辆入库（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 */
	public int addWarehouse(Warehouse warehouse) {
		return baseDao.create("mapper.standard.WarehouseMapper.addWarehouse", warehouse);
	}
	/**
	 * updateWarehouseSelective
	 * 自定义编辑
	 */
	public int updateWarehouseSelective(Warehouse warehouse) {
		return baseDao.update("mapper.standard.WarehouseMapper.updateWarehouseSelective", warehouse);
	}
	/**
	 * updateWarehouse
	 * 编辑
	 */
	public int updateWarehouse(Warehouse warehouse) {
		return baseDao.update("mapper.standard.WarehouseMapper.updateWarehouse", warehouse);
	}
	/**
	 * updateWarehouseState
	 * 修改入库状态
	 */
	public int updateWarehouseState(String auctionId) {
		return baseDao.update("mapper.standard.WarehouseMapper.updateWarehouseState", auctionId);
	}
	

}

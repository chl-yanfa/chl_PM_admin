package com.car.auction.warehouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.entity.WarehousePlacing;


/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehousePlacingDao
 * 类描述：出库单(异常出库，正常出库)
 * 创建人：张婉欣
 * 创建时间： 2018年8月9日 下午4:21:45
 * @version
 */
@Component
public class WarehousePlacingDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id查询出库信息
	 */
	public WarehousePlacing getWarehousePlacingById(String id) {
		return baseDao.findOne("mapper.standard.WarehousePlacingMapper.getWarehousePlacingById", id);
	}
	/**
	 * 车辆出库（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 * 异常出库 audit_state 1;
	 * 办理出库 audit_state 0
	 */
	public int addWarehousePlacing(WarehousePlacing warehousePlacing) {
		return baseDao.create("mapper.standard.WarehousePlacingMapper.addWarehousePlacing", warehousePlacing);
	}
	/**
	 * 查询异常出库待审核信息
	 */
	public WarehousePlacing getWarehousePlacingToAudit(String stockId) {
		return baseDao.findOne("mapper.standard.WarehousePlacingMapper.getWarehousePlacingToAudit", stockId);
	}
	/**
	 * updateWarehousePlacingSelective
	 * 自定义编辑
	 */
	public int updateWarehousePlacingSelective(WarehousePlacing warehousePlacing) {
		return baseDao.update("mapper.standard.WarehousePlacingMapper.updateWarehousePlacingSelective", warehousePlacing);
	}
	

}

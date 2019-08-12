package com.car.auction.warehouse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.entity.WarehousePlacingAudit;


/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehousePlacingAuditDao
 * 类描述：异常出库申请单审核
 * 创建人：张婉欣
 * 创建时间： 2018年8月11日 下午4:45:51
 * @version
 */
@Component
public class WarehousePlacingAuditDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id查询异常出库审核信息
	 */
	public WarehousePlacingAudit getPlacingAuditById(String id) {
		return baseDao.findOne("mapper.standard.WarehousePlacingAuditMapper.getPlacingAuditById", id);
	}
	/**
	 * 新增异常出库审核信息
	 */
	public int addPlacingAudit(WarehousePlacingAudit warehousePlacingAudit) {
		return baseDao.create("mapper.standard.WarehousePlacingAuditMapper.addPlacingAudit", warehousePlacingAudit);
	}
	

}

package com.car.auction.procedure.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.entity.ProcedurePlacingAudit;



/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedurePlacingAuditDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午12:11:35
 * @version
 */
@Component
public class ProcedurePlacingAuditDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	
	/**
	 * 新增手续审核（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 */
	public int addProcedureAudit(ProcedurePlacingAudit procedureAudit) {
		return baseDao.create("mapper.standard.ProcedurePlacingAuditMapper.addProcedureAudit", procedureAudit);
	}
	
	/**
	 * 根据id查询手续审核
	 */
	public ProcedurePlacingAudit getProcedureAuditById(String id) {
		return baseDao.findOne("mapper.standard.ProcedurePlacingAuditMapper.getProcedureAuditById", id);
	}

}

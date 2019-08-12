package com.car.auction.procedure.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.entity.ProcedureRecord;



/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureRecordDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午3:28:19
 * @version
 */
@Component
public class ProcedureRecordDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	
	/**
	 * 手续完结归档（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 */
	public int addProcedureRecord(ProcedureRecord procedureRecord) {
		return baseDao.create("mapper.standard.ProcedureRecordMapper.addProcedureRecord", procedureRecord);
	}
	
	/**
	 * 根据id查询手续审核
	 */
	public ProcedureRecord getProcedureRecordById(String id) {
		return baseDao.findOne("mapper.standard.ProcedureRecordMapper.getProcedureRecordById", id);
	}
	/**
	 * 根据手续id查手续完结归档
	 * procedureId
	 */
	public ProcedureRecord getProcedureRecordByProcedureId(String procedureId) {
		return baseDao.findOne("mapper.standard.ProcedureRecordMapper.getProcedureRecordByProcedureId", procedureId);
	}
	/**
	 * 更新手续完结归档（全字段更新，为null的也会插入）is_delete 0;createtime now()
	 */
	public int updateProcedureRecord(ProcedureRecord procedureRecord) {
		return baseDao.update("mapper.standard.ProcedureRecordMapper.updateProcedureRecord", procedureRecord);
	}
	public int updateProcedureRecordSelective(ProcedureRecord procedureRecord) {
		return baseDao.update("mapper.standard.ProcedureRecordMapper.updateProcedureRecordSelective", procedureRecord);
	}
}

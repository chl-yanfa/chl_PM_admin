package com.car.auction.procedure.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.entity.ProcedureHistory;


/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureHistoryDao
 * 类描述：手续出入库记录
 * 创建人：张婉欣
 * 创建时间： 2018年8月6日 下午3:02:30
 * @version
 */
@Component
public class ProcedureHistoryDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id
	 * 查询手续出入库记录
	 */
	public ProcedureHistory getProcedureHistoryById(String id) {
		return baseDao.findOne("mapper.standard.ProcedureHistoryMapper.getProcedureHistoryById", id);
	}
	/**
	 * 根据手续procedureId
	 * 查询出入库记录
	 */
	public List<ProcedureHistory> getProcedureHistoryByPage(String procedureId,Integer page,Integer rows) {
		return baseDao.findList("mapper.standard.ProcedureHistoryMapper.getProcedureHistory", 
					procedureId,page,rows);
	}
	/**
	 * 根据手续procedureId
	 * 查询出入库记录总条数
	 */
	public int getProcedureHistoryCount(String procedureId) {
		return baseDao.findOne("mapper.standard.ProcedureHistoryMapper.getProcedureHistoryCount", procedureId);
	}
	/**
	 * 根据id or procedureId or auditState
	 * 查询出入库记录
	 */
	public List<ProcedureHistory> getProcedureHistoryByCondition(ProcedureHistory procedureHistory) {
		return baseDao.findList("mapper.standard.ProcedureHistoryMapper.getProcedureHistoryByCondition", procedureHistory);
	}
	/**
	 * 新增手续出入库记录（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 */
	public int addProcedureHistory(ProcedureHistory procedureHistory) {
		return baseDao.create("mapper.standard.ProcedureHistoryMapper.addProcedureHistory", procedureHistory);
	}
	/**
	 */
	public int updateProcedureHistorySelective(ProcedureHistory procedureHistory) {
		return baseDao.update("mapper.standard.ProcedureHistoryMapper.updateProcedureHistorySelective", procedureHistory);
	}

}

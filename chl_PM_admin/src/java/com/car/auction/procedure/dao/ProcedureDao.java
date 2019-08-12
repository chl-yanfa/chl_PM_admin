package com.car.auction.procedure.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.entity.Procedure;


/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureDao
 * 类描述：手续入库单DAO
 * 创建人：张婉欣
 * 创建时间： 2018年7月14日 
 * @version
 */
@Component
public class ProcedureDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id查询入库手续
	 */
	public Procedure getProcedureById(String id) {
		return baseDao.findOne("mapper.standard.ProcedureMapper.getProcedureById", id);
	}
	/**
	 * 根据auctionId查询入库手续
	 */
	public Procedure getProcedureByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.ProcedureMapper.getProcedureByAuctionId", auctionId);
	}
	/**
	 * 新增手续入库（全字段新增，为null的也会插入）is_delete 0;createtime now()
	 */
	public int addProcedure(Procedure procedure) {
		return baseDao.create("mapper.standard.ProcedureMapper.addProcedure", procedure);
	}
	/**
	 * 新增手续入库（自由组合，为null的不插）is_delete 0;createtime now()
	 */
	public int addProcedureSelective(Procedure procedure) {
		return baseDao.create("mapper.standard.ProcedureMapper.addProcedureSelective", procedure);
	}
	/**
	 * 编辑手续入库（自定义修改 ，为null的不改）
	 */
	public int updateProcedureSelective(Procedure procedure) {
		return baseDao.update("mapper.standard.ProcedureMapper.updateProcedureSelective", procedure);
	}
	/**
	 * 编辑手续入库（全部修改 ，为null的也会覆盖旧值）
	 */
	public int updateProcedureAll(Procedure procedure) {
		return baseDao.update("mapper.standard.ProcedureMapper.updateProcedureAll", procedure);
	}
	/**
	 * 修改手续入库的状态
	 */
	public int updateProcedureState(String auctionId) {
		return baseDao.update("mapper.standard.ProcedureMapper.updateProcedureState", auctionId);
	}
}

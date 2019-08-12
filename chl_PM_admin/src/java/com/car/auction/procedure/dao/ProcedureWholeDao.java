package com.car.auction.procedure.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.dto.ProcedureDto;
import com.car.auction.procedure.entity.ProcedureStatistics;
import com.car.auction.procedure.entity.ProcedureWhole;


/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureDao
 * 类描述：手续管理-列表页显示
 * 创建人：张婉欣
 * 创建时间： 2018年8月10日 下午2:28:52
 * @version
 */
@Component
public class ProcedureWholeDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 查询手续管理列表
	 */
	public List<ProcedureWhole> getProcedureListByPage(ProcedureDto procedure) {
		int start = 0;
		int length = 10;
		if(procedure.getPage()!=null) {
			start = procedure.getPage();			
		}
		if(procedure.getPage()!=null) {
			length = procedure.getRows();			
		}
		return baseDao.findList("mapper.standard.ProcedureWholeMapper.getProcedureListByPage", procedure,start,length);
	}
	/**
	 * 查询手续管理列表总条数
	 */
	public int getProcedureListTotal(ProcedureDto procedure) {
		return baseDao.findOne("mapper.standard.ProcedureWholeMapper.getProcedureListTotal", procedure);
	}
	/**
	 * 获取手续统计
	 */
	public List<ProcedureStatistics> getProcedureStatisticsByPage(ProcedureDto procedure) {
		int start = 0;
		int length = 10;
		if(procedure.getPage()!=null) {
			start = procedure.getPage();			
		}
		if(procedure.getPage()!=null) {
			length = procedure.getRows();			
		}
		return baseDao.findList("mapper.standard.ProcedureWholeMapper.getProcedureStatisticsByPage", procedure,start,length);
	}
	public int getProcedureStatisticsTotal(ProcedureDto procedure) {
		return baseDao.findOne("mapper.standard.ProcedureWholeMapper.getProcedureStatisticsTotal", procedure);
	}
}

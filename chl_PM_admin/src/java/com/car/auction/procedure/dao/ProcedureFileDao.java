package com.car.auction.procedure.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.procedure.entity.ProcedureFile;


/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureFileDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月6日 上午11:30:42
 * @version
 */
@Component
public class ProcedureFileDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据procedureId or fileId
	 * 条件查询手续文件
	 */
	public ProcedureFile getProcedureFileById(Integer id) {
		return baseDao.findOne("mapper.standard.ProcedureFileMapper.getProcedureFileById", id);
	}
	/**
	 * 根据procedureId or fileId
	 * 条件查询手续文件
	 */
	public ProcedureFile getProcedureFileByCondition(ProcedureFile procedureFile) {
		return baseDao.findOne("mapper.standard.ProcedureFileMapper.getProcedureFileByCondition", procedureFile);
	}
	/**
	 * 根据procedureId 和 procedureState
	 * 查询入库手续文件
	 */
	public List<Integer> getFileTypeByProcedureState(ProcedureFile procedureFile) {
		return baseDao.findList("mapper.standard.ProcedureFileMapper.getFileTypeByProcedureState", procedureFile);
	}
	/**
	 * 新增文件
	 */
	public int addProcedureFile(ProcedureFile procedureFile) {
		return baseDao.create("mapper.standard.ProcedureFileMapper.addProcedureFile", procedureFile);
	}
	/**
	 * 批量新增文件 is_delete 0;createtime now()
	 */
	public int addProcedureFileBatch(List<ProcedureFile> list) {
		return baseDao.create("mapper.standard.ProcedureFileMapper.addProcedureFileBatch", list);
	}
	/**
	 * 根据procedureId和fileId
	 * 编辑手续文件
	 * （自定义修改 ，为null的不改）updatetime now()
	 * 用于文件出库
	 */
	public int updateProcedureFileByCondition(ProcedureFile procedureFile) {
		return baseDao.update("mapper.standard.ProcedureFileMapper.updateProcedureFileByCondition", procedureFile);
	}
	
	/**
	 * 编辑手续文件
	 * （自定义修改 ，为null的不改）updatetime now()
	 * 
	 */
	public int updateProcedureFile(ProcedureFile procedureFile) {
		return baseDao.update("mapper.standard.ProcedureFileMapper.updateProcedureFile", procedureFile);
	}

}

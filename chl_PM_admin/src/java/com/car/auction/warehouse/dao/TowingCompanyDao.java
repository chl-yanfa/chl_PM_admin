package com.car.auction.warehouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.entity.TowingCompany;

/**
 * 项目名称：SDIC-Inner
 * 类名称：TowingCompanyDao
 * 类描述：拖车公司数据
 * 创建时间： 2018年8月11日 下午3:24:46
 * @version
 */
@Component
public class TowingCompanyDao {
	
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询拖车公司数据列表
	 */
	public List<TowingCompany> getTowingCompanyListByPage(TowingCompany towingCompany) {
		int start = 0;
		int length = 10;
		if(towingCompany.getPage()!=null) {
			start = towingCompany.getPage();			
		}
		if(towingCompany.getPage()!=null) {
			length = towingCompany.getRows();			
		}
		return baseDao.findList("mapper.standard.TowingCompanyMapper.getTowingCompanyListByPage",towingCompany,start,length);
	}
	/**
	 * getTowingCompanyListTotal
	 *  查询拖车公司数据列表总条数
	 */
	public int getTowingCompanyListTotal(TowingCompany towingCompany) {
		return baseDao.findOne("mapper.standard.TowingCompanyMapper.getTowingCompanyListTotal", towingCompany);
	}
	
	/**
	 * 根据id查询拖车公司数据
	 */
	public TowingCompany getTowingCompanyById(String id) {
		return baseDao.findOne("mapper.standard.TowingCompanyMapper.getTowingCompanyById", id);
	}
	/**
	 * 新增拖车公司数据 全字段新增（createTime now();isdelete 0）
	 */
	public int addTowingCompany(TowingCompany towingCompany) {
		return baseDao.create("mapper.standard.TowingCompanyMapper.addTowingCompany", towingCompany);
	}
	/**
	 * 更新拖车公司数据 全字段更新（updateTime now();isdelete 0）
	 */
	public int updateTowingCompany(TowingCompany towingCompany) {
		return baseDao.update("mapper.standard.TowingCompanyMapper.updateTowingCompany", towingCompany);
	}
	/**
	 * 更新拖车公司数据-只修改数据状态
	 * @param towingCompany
	 * @return
	 */
	public int updateTowingCompanySelective(TowingCompany towingCompany) {
		return baseDao.update("mapper.standard.TowingCompanyMapper.updateTowingCompanySelective", towingCompany);
	}
}
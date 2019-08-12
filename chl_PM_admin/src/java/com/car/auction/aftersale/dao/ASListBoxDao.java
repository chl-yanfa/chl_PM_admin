package com.car.auction.aftersale.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.aftersale.dto.AftersaleDto;
import com.car.auction.aftersale.entity.DealEntity;
import com.car.auction.common.BaseDao;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ASListBoxDao
 * 类描述：售后管理列表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月11日 下午12:01:16
 */
@Component
public class ASListBoxDao {
	
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询成交列表--分页
	 */
	public List<DealEntity> getDealListByPage(AftersaleDto aftersale) {
		int start = 0;
		int length = 10;
		try {
			start =aftersale.getPage();
		} catch (Exception e) {}
		try {
			length =aftersale.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.ASListBoxMapper.getDealListByPage",
				aftersale,start,length);
		
	}
	public int getDealListTotal(AftersaleDto aftersale) {
		return baseDao.findOne("mapper.standard.ASListBoxMapper.getDealListTotal", aftersale);
	}
	/**
	 * 查询成交列表--TAB
	 */
	public List<Map<String, String>> getDealTab(AftersaleDto aftersale) {
		return baseDao.findList("mapper.standard.ASListBoxMapper.getDealTab", aftersale);
	}
	
	
	/**
	 * 查询售后异常列表--分页
	 */
	public List<DealEntity> getAbnormalListByPage(AftersaleDto aftersale) {
		int start = 0;
		int length = 10;
		try {
			start =aftersale.getPage();
		} catch (Exception e) {}
		try {
			length =aftersale.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.ASListBoxMapper.getAbnormalListByPage",
				aftersale,start,length);
		
	}
	public int getAbnormalListTotal(AftersaleDto aftersale) {
		return baseDao.findOne("mapper.standard.ASListBoxMapper.getAbnormalListTotal", aftersale);
	}
	/**
	 * 查询售后异常列表--TAB
	 */
	public List<Map<String, String>> getAbnormalTab(AftersaleDto aftersale) {
		return baseDao.findList("mapper.standard.ASListBoxMapper.getAbnormalTab", aftersale);
	}
	
	/**
	 * 查询流拍列表--分页
	 */
	public List<DealEntity> getAbortiveListByPage(AftersaleDto aftersale) {
		int start = 0;
		int length = 10;
		try {
			start =aftersale.getPage();
		} catch (Exception e) {}
		try {
			length =aftersale.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.ASListBoxMapper.getAbortiveListByPage",
				aftersale,start,length);
		
	}
	public int getAbortiveListTotal(AftersaleDto aftersale) {
		return baseDao.findOne("mapper.standard.ASListBoxMapper.getAbortiveListTotal", aftersale);
	}
	/**
	 * 查询流拍列表--TAB
	 */
	public List<Map<String, String>> getAbortiveTab(AftersaleDto aftersale) {
		return baseDao.findList("mapper.standard.ASListBoxMapper.getAbortiveTab", aftersale);
	}
	
	/**
	 * 查询违约列表--分页
	 */
	public List<DealEntity> getBreachListByPage(AftersaleDto aftersale) {
		int start = 0;
		int length = 10;
		try {
			start =aftersale.getPage();
		} catch (Exception e) {}
		try {
			length =aftersale.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.ASListBoxMapper.getBreachListByPage",
				aftersale,start,length);
		
	}
	public int getBreachListTotal(AftersaleDto aftersale) {
		return baseDao.findOne("mapper.standard.ASListBoxMapper.getBreachListTotal", aftersale);
	}
	
}

package com.car.auction.finance.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.finance.dto.FinanceDto;
import com.car.auction.finance.entity.FinanceEntity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：FinanceDao
 * 类描述：财务管理
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月18日 下午3:14:54
 */
@Component
public class FinanceDao {
	
	@Autowired 
	private BaseDao baseDao;
	/**
	 * 查询财务列表--分页
	 */
	public List<FinanceEntity> getFinanceListByPage(FinanceDto finance) {
		int start = 0;
		int length = 10;
		try {
			start =finance.getPage();
		} catch (Exception e) {}
		try {
			length =finance.getRows();
		} catch (Exception e) {}
		
		return baseDao.findList("mapper.standard.FinanceMapper.getFinanceListByPage",
				finance,start,length);
		
	}
	public int getFinanceListTotal(FinanceDto finance) {
		return baseDao.findOne("mapper.standard.FinanceMapper.getFinanceListTotal", finance);
	}
	/**
	 * 查询成交列表--TAB
	 */
	public List<Map<String, String>> getFinanceTab(FinanceDto finance) {
		return baseDao.findList("mapper.standard.FinanceMapper.getFinanceTab", finance);
	}
	
}

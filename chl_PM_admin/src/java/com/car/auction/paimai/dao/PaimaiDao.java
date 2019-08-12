package com.car.auction.paimai.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.paimai.dto.PaimaiDto;
import com.car.auction.paimai.entity.Paimai;
import com.car.auction.paimai.entity.PaimaiInfo;
import com.car.auction.paimai.entity.PaimaiStatistics;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiDao
 * 类描述：拍卖会管理
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月28日 上午10:44:22
 */
@Component
public class PaimaiDao {
	@Autowired 
	private BaseDao baseDao;
	public String getPMHID() {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPMHID");
	}
	public List<Paimai> getPaimaiListByPage(PaimaiDto paimai) {
		int start = 0;
		int length = 10;
		try {
			start =paimai.getPage();
		} catch (Exception e) {}
		try {
			length =paimai.getRows();
		} catch (Exception e) {}
		return baseDao.findList("mapper.standard.PaimaiMapper.getPaimaiListByPage",paimai,start,length);
	}
	public int getPaimaiListTotal(PaimaiDto paimai) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiListTotal", paimai);
	}
	public List<Map<String, String>> getPaimaiTab(PaimaiDto paimai) {
		return baseDao.findList("mapper.standard.PaimaiMapper.getPaimaiTab", paimai);
	}
	public Paimai getPaimaiById(String id) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiById",id);
	}
	public Paimai getPaimaiByPmhId(String pmhId) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiByPmhId",pmhId);
	}
	public int addPaimai(Paimai paimai) {
		return baseDao.create("mapper.standard.PaimaiMapper.addPaimai", paimai);
	}
	public int updatePaimai(Paimai paimai) {
		return baseDao.update("mapper.standard.PaimaiMapper.updatePaimai", paimai);
	}
	public int updatePaimaiSelective(Paimai paimai) {
		return baseDao.update("mapper.standard.PaimaiMapper.updatePaimaiSelective", paimai);
	}
	public int deletePaimai(String id) {
		return baseDao.update("mapper.standard.PaimaiMapper.deletePaimai", id);
	}
	public List<PaimaiStatistics> getPaimaiStatistics(Map<String,String> param) {
		return baseDao.findList("mapper.standard.PaimaiMapper.getPaimaiStatistics",param);
	}
	public Integer getPaimaiPersonTotal(String pmhId) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiPersonTotal",pmhId);
	}
	public Integer getPaimaiHandleTotal(String pmhId) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiHandleTotal",pmhId);
	}
	public PaimaiInfo getPaimaiInfo(String auctionSetId) {
		return baseDao.findOne("mapper.standard.PaimaiMapper.getPaimaiInfo",auctionSetId);
	}
}

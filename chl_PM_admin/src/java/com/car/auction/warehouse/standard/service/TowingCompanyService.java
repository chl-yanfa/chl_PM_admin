package com.car.auction.warehouse.standard.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.warehouse.dao.TowingCompanyDao;
import com.car.auction.warehouse.entity.TowingCompany;

/**
 *TowingCompanyService
 */
@Service
public class TowingCompanyService {

	@Autowired
	private TowingCompanyDao towingCompanyDao;
	
	public ResultDTO<TowingCompany> getTowingCompanyList(TowingCompany towingCompany) {
		ResultDTO<TowingCompany> res = new ResultDTO<TowingCompany>();
		List<TowingCompany> list = towingCompanyDao.getTowingCompanyListByPage(towingCompany);
		res.setRows(list);
		int total = towingCompanyDao.getTowingCompanyListTotal(towingCompany);
		res.setTotal(total);
		return res;
	}

	public ResultDTO<TowingCompany> getTowingCompanyById(String id) {
		ResultDTO<TowingCompany> res = new ResultDTO<TowingCompany>();
		if(StringUtils.isNotBlank(id)) {
			TowingCompany towingCompany = towingCompanyDao.getTowingCompanyById(id);
			res.setEntity(towingCompany);			
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}

	public ResultDTO<String> addTowingCompany(TowingCompany towingCompany) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null!=towingCompany) {
			towingCompany.setId(UUIDUtil.getUuid());
			towingCompanyDao.addTowingCompany(towingCompany);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
	
	public ResultDTO<String> updateTowingCompany(TowingCompany towingCompany) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(null!=towingCompany&&StringUtils.isNotBlank(towingCompany.getId())) {
			towingCompany.setIsDelete(0);
			towingCompanyDao.updateTowingCompany(towingCompany);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}

	public ResultDTO<String> deleteTowingCompany(String id) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(StringUtils.isNotBlank(id)) {
			TowingCompany towingCompany =new TowingCompany();
			towingCompany.setId(id);
			towingCompany.setIsDelete(1);
			towingCompanyDao.updateTowingCompanySelective(towingCompany);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
		}
		return res;
	}
}

package com.car.auction.aftersale.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.aftersale.entity.AftersaleFile;
import com.car.auction.common.BaseDao;

@Component
public class AftersaleFileDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public AftersaleFile getFileById(Integer id) {
		return baseDao.findOne("mapper.standard.AftersaleFileMapper.getFileById", id);
	}
	
	public int addFile(AftersaleFile aftersaleFile) {
		return baseDao.create("mapper.standard.AftersaleFileMapper.addFile", aftersaleFile);
	}
	
	public int addFileBatch(List<AftersaleFile> list) {
		if(list!=null&&list.size()>0) {
			return baseDao.create("mapper.standard.AftersaleFileMapper.addFileBatch", list);
		}
		return 0;
	}
	
	public int updateFile(AftersaleFile aftersaleFile) {
		return baseDao.update("mapper.standard.AftersaleFileMapper.updateFile", aftersaleFile);
	}
}
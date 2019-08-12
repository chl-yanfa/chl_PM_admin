package com.car.auction.organization.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.organization.entity.LyMessage;


/**
 * 类名称：LyMessageDao
 * @version
 */
@Component
public class LyMessageDao {
	
	@Autowired
	private BaseDao baseDao;
	
	public List<LyMessage> getLyMessageByLyids(List<String> lyidList,Integer page,Integer rows) {
		int start = 0;
		int length = 10;
		if(page!=null) {
			start=page;
		}
		if(rows!=null) {
			length=rows;
		}
		return baseDao.findList("mapper.standard.LyMessageMapper.getLyMessageByLyids",
				lyidList,start,length);
	}
	public int getLyMessageCountByLyids(List<String> lyidList) {
		return baseDao.findOne("mapper.standard.LyMessageMapper.getLyMessageCountByLyids", lyidList);
	}
}

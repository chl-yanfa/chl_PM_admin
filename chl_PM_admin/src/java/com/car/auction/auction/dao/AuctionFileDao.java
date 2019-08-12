package com.car.auction.auction.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.auction.entity.AuctionFile;
import com.car.auction.common.BaseDao;

@Component
public class AuctionFileDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public AuctionFile getAuctionFileById(Integer id) {
		return baseDao.findOne("mapper.standard.AuctionFileMapper.getAuctionFileById", id);
	}
	
	public int addAuctionFile(AuctionFile auctionFile) {
		return baseDao.create("mapper.standard.AuctionFileMapper.addAuctionFile", auctionFile);
	}
	
	public int addAuctionFileBatch(List<AuctionFile> list) {
		if(list!=null&&list.size()>0) {
			return baseDao.create("mapper.standard.AuctionFileMapper.addAuctionFileBatch", list);
		}
		return 0;
	}
	
	public int updateAuctionFile(AuctionFile auctionFile) {
		return baseDao.update("mapper.standard.AuctionFileMapper.updateAuctionFile", auctionFile);
	}
	
}
package com.car.auction.auction.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.auction.entity.AuctionAudit;
import com.car.auction.common.BaseDao;
import com.car.auction.common.UUIDUtil;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionAuditDao
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月2日 上午10:38:09
 * @version
 */
@Component
public class AuctionAuditDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 新增拍品审核信息
	 * createtime now()
	 * is_delete 0
	 */
	public void addAuctionAudit(AuctionAudit auctionAudit) {
		auctionAudit.setId(UUIDUtil.getUuid());
		baseDao.create("mapper.standard.AuctionAuditMapper.addAuctionAudit", auctionAudit);
	}
	/**
	 * 新增拍品审核信息-自定义
	 */
	public void addAuctionAuditSelective(AuctionAudit auctionAudit) {
		baseDao.create("mapper.standard.AuctionAuditMapper.addAuctionAuditSelective", auctionAudit);
	}
	/**
	 * 更改拍品审核信息-all null会覆盖原值
	 */
	public void updateAuctionAudit(AuctionAudit auctionAudit){
		baseDao.update("mapper.standard.AuctionAuditMapper.updateAuctionAudit",auctionAudit);
	}
	/**
	 * 更改拍品审核信息-自定义 null不作处理
	 */
	public void updateAuctionAuditSelective(AuctionAudit auctionAudit){
		baseDao.update("mapper.standard.AuctionAuditMapper.updateAuctionAuditSelective",auctionAudit);
	}
	/**
	 * 根据ID查询拍品审核信息
	 */
	public AuctionAudit getAuctionAuditById(String id) {
		return baseDao.findOne("mapper.standard.AuctionAuditMapper.getAuctionAuditById", id);
	}
	/**
	 * 根据条件查询拍品审核信息
	 */
	public List<AuctionAudit> getAuctionAuditByCondition(AuctionAudit auctionAudit) {
		return baseDao.findList("mapper.standard.AuctionAuditMapper.getAuctionAuditByCondition", auctionAudit);
	}
	/**
	 * 撤拍-->删除审核信息
	 */
	public int deleteAuctionAudit(String auctionId){
		return baseDao.update("mapper.standard.AuctionAuditMapper.deleteAuctionAudit",auctionId);
	}
	
}

package com.car.auction.aftersale.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.aftersale.entity.AbnormalAudit;
import com.car.auction.aftersale.entity.AdjustPrice;
import com.car.auction.aftersale.entity.Aftersale;
import com.car.auction.aftersale.entity.BackCar;
import com.car.auction.aftersale.entity.DealInfo;
import com.car.auction.aftersale.entity.Defer;
import com.car.auction.aftersale.entity.PayTrack;
import com.car.auction.aftersale.entity.Scrap;
import com.car.auction.aftersale.entity.SecondAsk;
import com.car.auction.aftersale.entity.TakeCarTrack;
import com.car.auction.common.BaseDao;
import com.car.auction.common.UUIDUtil;


/**
 * 项目名称：SDIC-Inner
 * 类名称：AftersaleDao
 * 类描述：售后
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月18日 下午2:06:55
 */
@Component
public class AftersaleDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 售后状态管理
	 */
	public int addAftersale(Aftersale aftersale) {
		aftersale.setId(UUIDUtil.getUuid());
		return baseDao.create("mapper.standard.AftersaleMapper.addAftersale", aftersale);
	}
	public Aftersale getAftersaleByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.AftersaleMapper.getAftersaleByAuctionSetId", auctionSetId);
	}
	public int updateAftersaleSelective(Aftersale aftersale) {
		return baseDao.update("mapper.standard.AftersaleMapper.updateAftersaleSelective", aftersale);
	}
	public int updateAftersaleStateByAuctionSetId(Aftersale aftersale) {
		return baseDao.update("mapper.standard.AftersaleMapper.updateAftersaleStateByAuctionSetId", aftersale);
	}
	public DealInfo getDealInfoByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.AftersaleMapper.getDealInfoByAuctionSetId", auctionSetId);
	}
	/**
	 * 付款跟踪
	 */
	public int addPayTrack(PayTrack payTrack) {
		payTrack.setId(UUIDUtil.getUuid());
		return baseDao.create("mapper.standard.PayTrackMapper.addPayTrack", payTrack);
	}
	public List<PayTrack> getPayTrackByAuctionSetId(String auctionSetId) {
		return baseDao.findList("mapper.standard.PayTrackMapper.getPayTrackByAuctionSetId", auctionSetId);
	}
	/**
	 * 提货跟踪
	 */
	public int addTakeCarTrack(TakeCarTrack takeCarTrack) {
		return baseDao.create("mapper.standard.TakeCarTrackMapper.addTakeCarTrack", takeCarTrack);
	}
	public List<TakeCarTrack> getTakeCarTrackByAuctionSetId(String auctionSetId) {
		return baseDao.findList("mapper.standard.TakeCarTrackMapper.getTakeCarTrackByAuctionSetId", auctionSetId);
	}
	/**
	 * 延期（打款，提货，过户）
	 */
	public int addDefer(Defer defer) {
		return baseDao.create("mapper.standard.DeferMapper.addDefer", defer);
	}
	public Defer getDeferById(String id) {
		return baseDao.findOne("mapper.standard.DeferMapper.getDeferById", id);
	}
	public Defer getDeferByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.DeferMapper.getDeferByAuctionSetId", auctionSetId);
	}
	public int updateDeferSelective(Defer defer) {
		return baseDao.update("mapper.standard.DeferMapper.updateDeferSelective", defer);
	}
	/**
	 * 调价
	 */
	public int addAdjustPrice(AdjustPrice adjustPrice) {
		return baseDao.create("mapper.standard.AdjustPriceMapper.addAdjustPrice", adjustPrice);
	}
	public AdjustPrice getAdjustPriceById(String id) {
		return baseDao.findOne("mapper.standard.AdjustPriceMapper.getAdjustPriceById", id);
	}
	public AdjustPrice getAdjustPriceByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.AdjustPriceMapper.getAdjustPriceByAuctionSetId", auctionSetId);
	}
	public int updateAdjustPriceSelective(AdjustPrice adjustPrice) {
		return baseDao.update("mapper.standard.AdjustPriceMapper.updateAdjustPriceSelective", adjustPrice);
	}
	/**
	 * 退货
	 */
	public int addBackCar(BackCar backCar) {
		return baseDao.create("mapper.standard.BackCarMapper.addBackCar", backCar);
	}
	public BackCar getBackCarById(String id) {
		return baseDao.findOne("mapper.standard.BackCarMapper.getBackCarById", id);
	}
	public BackCar getBackCarByAuctionSetId(String auctionSetId) {
		return baseDao.findOne("mapper.standard.BackCarMapper.getBackCarByAuctionSetId", auctionSetId);
	}
	public int updateBackCarSelective(BackCar backCar) {
		return baseDao.update("mapper.standard.BackCarMapper.updateBackCarSelective", backCar);
	}
	/**
	 * 售后异常
	 */
	public int addAbnormalAudit(AbnormalAudit abnormalAudit) {
		abnormalAudit.setId(UUIDUtil.getUuid());
		return baseDao.create("mapper.standard.AbnormalAuditMapper.addAbnormalAudit", abnormalAudit);
	}
	public AbnormalAudit getAbnormalAuditById(String id) {
		return baseDao.findOne("mapper.standard.AbnormalAuditMapper.getAbnormalAuditById", id);
	}
	/**
	 * 报废车
	 */
	public int addScrap(Scrap scrap) {
		return baseDao.create("mapper.standard.ScrapMapper.addScrap", scrap);
	}
	public Scrap getScrapByAuctionId(String auctionId) {
		return baseDao.findOne("mapper.standard.ScrapMapper.getScrapByAuctionId", auctionId);
	}
	public int updateScrapSelective(Scrap scrap) {
		return baseDao.update("mapper.standard.ScrapMapper.updateScrapSelective", scrap);
	}
	/**
	 * 二询
	 */
	public int addSecondAsk(SecondAsk secondAsk) {
		return baseDao.create("mapper.standard.SecondAskMapper.addSecondAsk", secondAsk);
	}
	public int updateSecondAskSelective(SecondAsk secondAsk) {
		return baseDao.update("mapper.standard.SecondAskMapper.updateSecondAskSelective", secondAsk);
	}
}

package com.car.auction.paimai.entity;

import java.math.BigDecimal;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiStatistics
 * 类描述：拍卖会统计
 * 创建人：zhangwanxin
 * 创建时间： 2018年10月9日 上午11:57:10
 */
public class PaimaiStatistics {
	private String lotAreas;				//所属区域
	private Integer auctionCount=0;			//上拍数
	private Integer dealCount=0;			//成交数
	private BigDecimal dealPercent=new BigDecimal("0.00");		//成交百分比
	private Integer hasPriceCount=0;		//有人出价数
	private BigDecimal hasPricePercent=new BigDecimal("0.00");	//有人出价百分比
	private Integer hasNoPriceCount=0;		//没人出价数
	private BigDecimal hasNoPricePercent=new BigDecimal("0.00");	//没人出价百分比
	private Integer abortiveCount=0;		//流拍数
	private BigDecimal abortivePercent=new BigDecimal("0.00");	//流拍百分比
	private Integer aveDealPrice=0;			//平均成交价格
	private Integer aveProfit=0;			//平均利润
	private Integer totalProfit=0;			//总利润
	
	public PaimaiStatistics() {}
	public PaimaiStatistics(String lotAreas, Integer auctionCount, Integer dealCount, BigDecimal dealPercent,
			Integer hasPriceCount, BigDecimal hasPricePercent, Integer hasNoPriceCount, BigDecimal hasNoPricePercent,
			Integer abortiveCount, BigDecimal abortivePercent, Integer aveDealPrice, Integer aveProfit,
			Integer totalProfit) {
		this.lotAreas = lotAreas;
		this.auctionCount = auctionCount;
		this.dealCount = dealCount;
		this.dealPercent = dealPercent;
		this.hasPriceCount = hasPriceCount;
		this.hasPricePercent = hasPricePercent;
		this.hasNoPriceCount = hasNoPriceCount;
		this.hasNoPricePercent = hasNoPricePercent;
		this.abortiveCount = abortiveCount;
		this.abortivePercent = abortivePercent;
		this.aveDealPrice = aveDealPrice;
		this.aveProfit = aveProfit;
		this.totalProfit = totalProfit;
	}
	public String getLotAreas() {
		return lotAreas;
	}
	public void setLotAreas(String lotAreas) {
		this.lotAreas = lotAreas;
	}
	public Integer getAuctionCount() {
		return auctionCount;
	}
	public void setAuctionCount(Integer auctionCount) {
		this.auctionCount = auctionCount;
	}
	public Integer getDealCount() {
		return dealCount;
	}
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	public BigDecimal getDealPercent() {
		return dealPercent;
	}
	public void setDealPercent(BigDecimal dealPercent) {
		this.dealPercent = dealPercent;
	}
	public Integer getHasPriceCount() {
		return hasPriceCount;
	}
	public void setHasPriceCount(Integer hasPriceCount) {
		this.hasPriceCount = hasPriceCount;
	}
	public BigDecimal getHasPricePercent() {
		return hasPricePercent;
	}
	public void setHasPricePercent(BigDecimal hasPricePercent) {
		this.hasPricePercent = hasPricePercent;
	}
	public Integer getHasNoPriceCount() {
		return hasNoPriceCount;
	}
	public void setHasNoPriceCount(Integer hasNoPriceCount) {
		this.hasNoPriceCount = hasNoPriceCount;
	}
	public BigDecimal getHasNoPricePercent() {
		return hasNoPricePercent;
	}
	public void setHasNoPricePercent(BigDecimal hasNoPricePercent) {
		this.hasNoPricePercent = hasNoPricePercent;
	}
	public Integer getAbortiveCount() {
		return abortiveCount;
	}
	public void setAbortiveCount(Integer abortiveCount) {
		this.abortiveCount = abortiveCount;
	}
	public BigDecimal getAbortivePercent() {
		return abortivePercent;
	}
	public void setAbortivePercent(BigDecimal abortivePercent) {
		this.abortivePercent = abortivePercent;
	}
	public Integer getAveDealPrice() {
		return aveDealPrice;
	}
	public void setAveDealPrice(Integer aveDealPrice) {
		this.aveDealPrice = aveDealPrice;
	}
	public Integer getAveProfit() {
		return aveProfit;
	}
	public void setAveProfit(Integer aveProfit) {
		this.aveProfit = aveProfit;
	}
	public Integer getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(Integer totalProfit) {
		this.totalProfit = totalProfit;
	}
	
}

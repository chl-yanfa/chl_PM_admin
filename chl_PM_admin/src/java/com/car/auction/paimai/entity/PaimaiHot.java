package com.car.auction.paimai.entity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiHot
 * 类描述：拍卖会统计
 * 创建人：zhangwanxin
 * 创建时间： 2018年10月9日 上午11:57:10
 */
public class PaimaiHot {
	private Integer auctionPersonTotal=0;
	private Integer auctionHandleTotal=0;
	
	public PaimaiHot() {
	}
	public PaimaiHot(Integer auctionPersonTotal, Integer auctionHandleTotal) {
		this.auctionPersonTotal = auctionPersonTotal;
		this.auctionHandleTotal = auctionHandleTotal;
	}
	public Integer getAuctionPersonTotal() {
		return auctionPersonTotal;
	}
	public void setAuctionPersonTotal(Integer auctionPersonTotal) {
		this.auctionPersonTotal = auctionPersonTotal;
	}
	public Integer getAuctionHandleTotal() {
		return auctionHandleTotal;
	}
	public void setAuctionHandleTotal(Integer auctionHandleTotal) {
		this.auctionHandleTotal = auctionHandleTotal;
	}
}

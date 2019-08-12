package com.car.auction.paimai.entity;

import java.util.Date;

public class TraIndent {
	private Integer isHistory = null;
	private Integer isDelete = null ;
	private String auctionId = null;
	private String orderId = null;
	private String orderState = null;
	private String dealMember = null;
	private Date dealTime = null;
	private String dealMid =null;
	private String jointPrice = null;
	private String dataStr = null;
	
	public Integer getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getDealMember() {
		return dealMember;
	}
	public void setDealMember(String dealMember) {
		this.dealMember = dealMember;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getDealMid() {
		return dealMid;
	}
	public void setDealMid(String dealMid) {
		this.dealMid = dealMid;
	}
	public String getJointPrice() {
		return jointPrice;
	}
	public void setJointPrice(String jointPrice) {
		this.jointPrice = jointPrice;
	}
	public String getDataStr() {
		return dataStr;
	}
	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}
	
}

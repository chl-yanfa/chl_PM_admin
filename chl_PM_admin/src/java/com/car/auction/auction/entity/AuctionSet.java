package com.car.auction.auction.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuctionSet {
    private String id;
    private String orderNo;						//订单号
    private String auctionId;					//拍品id
    private BigDecimal startingPrice;			//起拍价
    private BigDecimal reservePrice;			//保留价
    private BigDecimal auctionCashDeposit;		//参拍保证金
    private BigDecimal commission;				//佣金
    private BigDecimal otherPrice;				//其他费用
    private String remark;						//备注
    private String pmhId;						//拍卖会ID
    private Integer pmOrder;					//拍卖会序号
    private Date auctionStartTime;				//拍卖开始时间
    private Date auctionEndTime;				//本车结束时间
    private Integer intervalTime;				//间隔时间（S）
    private Integer singleTime;					//单车拍卖时间
    private Date dealTime;						//成交时间
    private String dealType;					//成交类型   0. 竞价
    private String dealMid;						//成交人memberCode
    private String dealMember;					//成交人memberCode
    private BigDecimal jointPrice;				//合手价
    private BigDecimal highestPrice;			//最终车款
    private String orderState;					//订单状态 0-未上拍;10-待竞拍;20-竞拍中;30-待处理;31-已成交;40-流拍;50-待付款;60-待提货;70-待过户;80-交易完成;90-已下架 -8：已复拍;-9：已撤拍;-10:已退货
    private Integer isDelete;					//是否删除(0:有效，1:删除)
    private Integer isHistory;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public BigDecimal getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(BigDecimal startingPrice) {
		this.startingPrice = startingPrice;
	}
	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}
	public BigDecimal getAuctionCashDeposit() {
		return auctionCashDeposit;
	}
	public void setAuctionCashDeposit(BigDecimal auctionCashDeposit) {
		this.auctionCashDeposit = auctionCashDeposit;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(BigDecimal otherPrice) {
		this.otherPrice = otherPrice;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPmhId() {
		return pmhId;
	}
	public void setPmhId(String pmhId) {
		this.pmhId = pmhId;
	}
	public Integer getPmOrder() {
		return pmOrder;
	}
	public void setPmOrder(Integer pmOrder) {
		this.pmOrder = pmOrder;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getAuctionStartTime() {
		return auctionStartTime;
	}
	public void setAuctionStartTime(Date auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getAuctionEndTime() {
		return auctionEndTime;
	}
	public void setAuctionEndTime(Date auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	public Integer getSingleTime() {
		return singleTime;
	}
	public void setSingleTime(Integer singleTime) {
		this.singleTime = singleTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getDealMid() {
		return dealMid;
	}
	public void setDealMid(String dealMid) {
		this.dealMid = dealMid;
	}
	public String getDealMember() {
		return dealMember;
	}
	public void setDealMember(String dealMember) {
		this.dealMember = dealMember;
	}
	public BigDecimal getJointPrice() {
		return jointPrice;
	}
	public void setJointPrice(BigDecimal jointPrice) {
		this.jointPrice = jointPrice;
	}
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(Integer isHistory) {
		this.isHistory = isHistory;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
}
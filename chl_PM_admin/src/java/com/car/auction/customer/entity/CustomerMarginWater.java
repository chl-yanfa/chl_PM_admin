package com.car.auction.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerMarginWater {
    private String id;
    private String mid;
    private BigDecimal chargeAmount;	//充值金额
    private String operatorCash;
    private String memo;
    private String orderId;
    private String orderNo;
    private String auctionNo;
    private String operatorType;
    private String chargeOrder;
    private String chargeSource;
    private Integer payVoucherId;
    private Integer isRefund;			//是否退款0-未退款  1-已退款
    private Integer isDelete;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private String payVoucherPath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}
	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	public String getOperatorCash() {
		return operatorCash;
	}
	public void setOperatorCash(String operatorCash) {
		this.operatorCash = operatorCash;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(String auctionNo) {
		this.auctionNo = auctionNo;
	}
	public String getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	public String getChargeOrder() {
		return chargeOrder;
	}
	public void setChargeOrder(String chargeOrder) {
		this.chargeOrder = chargeOrder;
	}
	public String getChargeSource() {
		return chargeSource;
	}
	public void setChargeSource(String chargeSource) {
		this.chargeSource = chargeSource;
	}
	public Integer getPayVoucherId() {
		return payVoucherId;
	}
	public void setPayVoucherId(Integer payVoucherId) {
		this.payVoucherId = payVoucherId;
	}
	public Integer getIsRefund() {
		return isRefund;
	}
	public void setIsRefund(Integer isRefund) {
		this.isRefund = isRefund;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
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
	public String getPayVoucherPath() {
		return payVoucherPath;
	}
	public void setPayVoucherPath(String payVoucherPath) {
		this.payVoucherPath = payVoucherPath;
	}
	
}
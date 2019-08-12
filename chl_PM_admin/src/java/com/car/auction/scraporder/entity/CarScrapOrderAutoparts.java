package com.car.auction.scraporder.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CarScrapOrderAutoparts {
    private String id;
    private String orderId;
    private Integer partsType;
    private String partsName;
    private String partsNum;
    private String isDismantle;
    private String isCollision;
    private String isWear;
    private String isFlood;
    private String remark;
    private BigDecimal subQuote;
    private BigDecimal amount;
    private Integer orderAutopartsStatus;
    private String sortingState;
    private Integer unableReceiveReason;
    private String auctionId;
    private Boolean isValid;
    private String qrPic;
    private String creater;
    private String operator;
    private Date createtime;
    private Date operatortime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPartsType() {
		return partsType;
	}
	public void setPartsType(Integer partsType) {
		this.partsType = partsType;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getPartsNum() {
		return partsNum;
	}
	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}
	public String getIsDismantle() {
		return isDismantle;
	}
	public void setIsDismantle(String isDismantle) {
		this.isDismantle = isDismantle;
	}
	public String getIsCollision() {
		return isCollision;
	}
	public void setIsCollision(String isCollision) {
		this.isCollision = isCollision;
	}
	public String getIsWear() {
		return isWear;
	}
	public void setIsWear(String isWear) {
		this.isWear = isWear;
	}
	public String getIsFlood() {
		return isFlood;
	}
	public void setIsFlood(String isFlood) {
		this.isFlood = isFlood;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public BigDecimal getSubQuote() {
		return subQuote;
	}
	public void setSubQuote(BigDecimal subQuote) {
		this.subQuote = subQuote;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Integer getOrderAutopartsStatus() {
		return orderAutopartsStatus;
	}
	public void setOrderAutopartsStatus(Integer orderAutopartsStatus) {
		this.orderAutopartsStatus = orderAutopartsStatus;
	}
	public String getSortingState() {
		return sortingState;
	}
	public void setSortingState(String sortingState) {
		this.sortingState = sortingState;
	}
	public Integer getUnableReceiveReason() {
		return unableReceiveReason;
	}
	public void setUnableReceiveReason(Integer unableReceiveReason) {
		this.unableReceiveReason = unableReceiveReason;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public Boolean getIsValid() {
		return isValid;
	}
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	public String getQrPic() {
		return qrPic;
	}
	public void setQrPic(String qrPic) {
		this.qrPic = qrPic;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getOperatortime() {
		return operatortime;
	}
	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}
}
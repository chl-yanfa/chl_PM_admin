package com.car.auction.aftersale.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Aftersale
 * 类描述：售后表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月18日 下午1:46:58
 */
public class Aftersale {
    private String id;
    private String auctionId;
    private String auctionSetId;
    private Date dealTime;
    private String dealHandler;			//成交人
    private Date payTime;				//付款时间
    private Integer payState;			//付款状态：0-未付款；1-已付款；2-已退款
    private Date takeCarTime;			//提货时间
    private Integer takeCarState;		//提货状态：0-未提货；1-已提货；2-已退货（提货后）
    private Integer deferState;			//延期审核：0-无延期；1-待审核；2-审核通过；-1：审核驳回
    private Integer adjustPriceState;	//调价审核：0-无调价；1-待审核；2-审核通过；-1：审核驳回
    private Integer backCarState;		//退货审核：0-无退货；1-待审核；2-审核通过；-1：审核驳回
    private Integer isHistory;
    private Integer isDelete;
    private Date createTime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getAuctionSetId() {
		return auctionSetId;
	}
	public void setAuctionSetId(String auctionSetId) {
		this.auctionSetId = auctionSetId;
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getDealHandler() {
		return dealHandler;
	}
	public void setDealHandler(String dealHandler) {
		this.dealHandler = dealHandler;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getPayState() {
		return payState;
	}
	public void setPayState(Integer payState) {
		this.payState = payState;
	}
	public Date getTakeCarTime() {
		return takeCarTime;
	}
	public void setTakeCarTime(Date takeCarTime) {
		this.takeCarTime = takeCarTime;
	}
	public Integer getTakeCarState() {
		return takeCarState;
	}
	public void setTakeCarState(Integer takeCarState) {
		this.takeCarState = takeCarState;
	}
	public Integer getDeferState() {
		return deferState;
	}
	public void setDeferState(Integer deferState) {
		this.deferState = deferState;
	}
	public Integer getAdjustPriceState() {
		return adjustPriceState;
	}
	public void setAdjustPriceState(Integer adjustPriceState) {
		this.adjustPriceState = adjustPriceState;
	}
	public Integer getBackCarState() {
		return backCarState;
	}
	public void setBackCarState(Integer backCarState) {
		this.backCarState = backCarState;
	}
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
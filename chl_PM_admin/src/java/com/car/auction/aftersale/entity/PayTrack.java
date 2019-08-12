package com.car.auction.aftersale.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：payTrack
 * 类描述：打款跟踪表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月13日 下午5:33:15
 */
public class PayTrack {
    private String id;
    private String auctionId;
    private String auctionSetId;
    private String trackMessage;
    private String payHandle;
    private String operator;
    private Date createTime;
    private Integer isDelete;
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
	public String getTrackMessage() {
		return trackMessage;
	}
	public void setTrackMessage(String trackMessage) {
		this.trackMessage = trackMessage;
	}
	public String getPayHandle() {
		return payHandle;
	}
	public void setPayHandle(String payHandle) {
		this.payHandle = payHandle;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
    
}
package com.car.auction.aftersale.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.car.auction.aftersale.dto.AftersaleFileDto;

public class AdjustPrice {
    private String id;
    private String auctionId;
    private String auctionSetId;
    private String carPriceType;
    private String carPriceValue;
    private String carPriceAfter;
    private String commissionType;
    private String commissionValue;
    private String commissionAfter;
    private String remark;
    private Integer auditState;
    private Date createTime;
    private Integer isDelete;
    private List<AftersaleFileDto> fileList;
    private String fileIds;
    
	public AdjustPrice() {}
	
	public AdjustPrice(String id, Integer auditState) {
		this.id = id;
		this.auditState = auditState;
	}
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

	public String getCarPriceType() {
		return carPriceType;
	}
	public void setCarPriceType(String carPriceType) {
		this.carPriceType = carPriceType;
	}
	public String getCarPriceValue() {
		return carPriceValue;
	}
	public void setCarPriceValue(String carPriceValue) {
		this.carPriceValue = carPriceValue;
	}
	public String getCarPriceAfter() {
		return carPriceAfter;
	}
	public void setCarPriceAfter(String carPriceAfter) {
		this.carPriceAfter = carPriceAfter;
	}
	public String getCommissionType() {
		return commissionType;
	}
	public void setCommissionType(String commissionType) {
		this.commissionType = commissionType;
	}
	public String getCommissionValue() {
		return commissionValue;
	}
	public void setCommissionValue(String commissionValue) {
		this.commissionValue = commissionValue;
	}
	public String getCommissionAfter() {
		return commissionAfter;
	}
	public void setCommissionAfter(String commissionAfter) {
		this.commissionAfter = commissionAfter;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
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
	public List<AftersaleFileDto> getFileList() {
		return fileList==null?new ArrayList<AftersaleFileDto>():fileList;
	}
	public void setFileList(List<AftersaleFileDto> fileList) {
		this.fileList = fileList;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
    
}
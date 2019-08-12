package com.car.auction.aftersale.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.car.auction.aftersale.dto.AftersaleFileDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Scrap
 * 类描述：报废车辆
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月19日 上午11:40:43
 */
public class Scrap {
    private String id;
    private String auctionId;
    private String operator;
    private String scrapCompany;
    private String scrapMoney;
    private String remark;
    private Integer scrapState;
    private Date submitTime;
    private Date confirmTime;
    private String confirmRemark;
    private Date createTime;
    private Integer isDelete;
    private List<AftersaleFileDto> fileList;
    private String fileIds;
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
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getScrapCompany() {
		return scrapCompany;
	}
	public void setScrapCompany(String scrapCompany) {
		this.scrapCompany = scrapCompany;
	}
	public String getScrapMoney() {
		return scrapMoney;
	}
	public void setScrapMoney(String scrapMoney) {
		this.scrapMoney = scrapMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getScrapState() {
		return scrapState;
	}
	public void setScrapState(Integer scrapState) {
		this.scrapState = scrapState;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getConfirmTime() {
		return confirmTime;
	}
	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
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
	public String getConfirmRemark() {
		return confirmRemark;
	}
	public void setConfirmRemark(String confirmRemark) {
		this.confirmRemark = confirmRemark;
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
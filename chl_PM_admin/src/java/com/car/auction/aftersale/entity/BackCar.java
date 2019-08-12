package com.car.auction.aftersale.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.car.auction.aftersale.dto.AftersaleFileDto;

/**
 * 项目名称：SDIC-Inner
 * 类名称：BackCar
 * 类描述：申请退货表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月14日 下午6:41:08
 */
public class BackCar {
    private String id;
    private String auctionId;
    private String auctionSetId;
    private String isInStock;
    private String remark;
    private Integer auditState;
    private Date createTime;
    private Integer isDelete;
    private List<AftersaleFileDto> fileList;
    private String fileIds;
    
	public BackCar() {}
	public BackCar(String id, Integer auditState) {
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
	public String getIsInStock() {
		return isInStock;
	}
	public void setIsInStock(String isInStock) {
		this.isInStock = isInStock;
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
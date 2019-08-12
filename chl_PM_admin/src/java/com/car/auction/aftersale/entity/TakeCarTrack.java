package com.car.auction.aftersale.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.car.auction.aftersale.dto.AftersaleFileDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：TakeCarTrack
 * 类描述：提货跟踪表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月14日 下午2:25:48
 */
public class TakeCarTrack {
    private String id;
    private String auctionId;
    private String auctionSetId;
    private String remark;
    private String operator;
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
	public String getAuctionSetId() {
		return auctionSetId;
	}
	public void setAuctionSetId(String auctionSetId) {
		this.auctionSetId = auctionSetId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
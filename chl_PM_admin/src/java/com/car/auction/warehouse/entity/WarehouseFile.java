package com.car.auction.warehouse.entity;

import java.util.Date;

public class WarehouseFile {
    private Integer id;
    private String stockId;			//库存id
    private Integer fileType;		//文件类型(0:图片,1:附件)
    private Integer attachmentId;	//图片
    private Integer sort;			//排序
    private Integer isDelete;		//是否删除 0-未删除 1-已删除
    private Date createTime;
    private String createUser;

    public WarehouseFile() {}
	public WarehouseFile(String stockId, Integer fileType, Integer attachmentId, Integer sort) {
		this.stockId = stockId;
		this.fileType = fileType;
		this.attachmentId = attachmentId;
		this.sort = sort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
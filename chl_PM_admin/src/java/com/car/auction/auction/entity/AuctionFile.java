package com.car.auction.auction.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionFile
 * 类描述：入库文件(拍品信息)
 * 创建人：zhangwanxin
 * 创建时间： 2018年8月31日 上午11:54:06
 */
public class AuctionFile {
    private Integer id;
    private String auctionId;
    private Integer fileType;		//附件类型(0:拍品图片,1:行驶证,2:银行卡,3:银行卡正面,4:扫描件,5:物资附件)
    private Integer attachmentId;	//附件存储id
    private Integer sort;			//图片排序
    private Integer picType;		//图片类型：0-原图；1-缩略图
    private Integer isDelete;
    private Date createTime;
    private String createUser;
    
	public AuctionFile() {}
	
	public AuctionFile(String auctionId, Integer fileType, Integer attachmentId, Integer sort,
			Integer picType, String createUser) {
		this.auctionId = auctionId;
		this.fileType = fileType;
		this.attachmentId = attachmentId;
		this.sort = sort;
		this.picType = picType;
		this.createUser = createUser;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
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
	public Integer getPicType() {
		return picType;
	}
	public void setPicType(Integer picType) {
		this.picType = picType;
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
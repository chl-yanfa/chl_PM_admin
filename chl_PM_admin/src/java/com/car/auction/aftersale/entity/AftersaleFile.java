package com.car.auction.aftersale.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AftersaleFile
 * 类描述：售后管理图片库
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月14日 下午2:36:08
 */
public class AftersaleFile {
    private Integer id;
    private Integer fileType;//图片类型：1:提货;2:过户;3:延期；4:调价；5:退货；6:二询；7:报废；
    private String parentId;
    private Integer attachmentId;
    private Integer sort;
    private Integer isDelete;
    private Date createTime;
    private String createUser;
    
	public AftersaleFile() {}
	public AftersaleFile(Integer fileType, String parentId, Integer attachmentId,Integer sort) {
		this.fileType = fileType;
		this.parentId = parentId;
		this.attachmentId = attachmentId;
		this.sort = sort;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
/**
 * 
 */
package com.car.auction.attachment.entity;

import java.util.Date;

/**
 * 类名称：Attachment
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-03-27 18:43
 */

public class Attachment {
	private Integer id;
    private String originalName;
    private String storagePath;
    private String ossKey;
    private String creater;
    private String operator;
    private Date createtime;
    private Date operatortime;
    private Boolean isvalid;
    
	public Attachment() {}
	
	public Attachment(Integer id, Date operatortime, Boolean isvalid) {
		this.id = id;
		this.isvalid = isvalid;
	}

	public Attachment(String originalName, String storagePath, Date createtime, Boolean isvalid) {
		this.originalName = originalName;
		this.storagePath = storagePath;
		this.createtime = createtime;
		this.isvalid = isvalid;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getStoragePath() {
		return storagePath;
	}
	public void setStoragePath(String storagePath) {
		this.storagePath = storagePath;
	}
	public String getOssKey() {
		return ossKey;
	}
	public void setOssKey(String ossKey) {
		this.ossKey = ossKey;
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
	public Boolean getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(Boolean isvalid) {
		this.isvalid = isvalid;
	}
	
}

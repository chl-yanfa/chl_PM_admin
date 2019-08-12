package com.car.auction.aftersale.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AbnormalAudit
 * 类描述：售后异常审核表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月17日 上午10:32:34
 */
public class AbnormalAudit {
    private String id;
    private String applicationType;
    private String applicationId;
    private Integer isPass;				//通过|驳回 1:通过 -1:驳回
    private String noPassReason;
    private Date createTime;
    private Integer isDelete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
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
}
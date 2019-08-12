package com.car.auction.procedure.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedurePlacingAudit
 * 类描述：手续出库申请审核表
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 上午11:59:18
 * @version
 */
public class ProcedurePlacingAudit {
    private String id;
    private String applicationId;	//手续出库申请id
    private Integer isPass;			//同意或拒绝 0-同意 1-拒绝
    private String noPassReason;	//拒绝原因
    private Integer isDelete;		//是否删除 0-未删除 1-已删除
    private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
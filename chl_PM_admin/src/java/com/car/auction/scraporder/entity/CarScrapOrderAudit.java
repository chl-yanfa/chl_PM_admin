package com.car.auction.scraporder.entity;

import java.util.Date;

public class CarScrapOrderAudit {
    private Integer id;
    private String orderId;
    private Integer lasttimeId;
    private String businessType;
    private String stauts;
    private String remark;
    private String auditor;
    private Date auditTime;
    private String creater;
    private String operator;
    private Date createtime;
    private Date operatortime;
	public CarScrapOrderAudit() {}
	public CarScrapOrderAudit(String orderId, Integer lasttimeId, String businessType, String stauts,
			String remark, String auditor, Date auditTime, String creater, String operator, Date createtime,
			Date operatortime) {
		this.orderId = orderId;
		this.lasttimeId = lasttimeId;
		this.businessType = businessType;
		this.stauts = stauts;
		this.remark = remark;
		this.auditor = auditor;
		this.auditTime = auditTime;
		this.creater = creater;
		this.operator = operator;
		this.createtime = createtime;
		this.operatortime = operatortime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getLasttimeId() {
		return lasttimeId;
	}
	public void setLasttimeId(Integer lasttimeId) {
		this.lasttimeId = lasttimeId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
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
    
}
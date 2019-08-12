package com.car.auction.customer.entity;

import java.util.Date;

public class Customer {
	private String id;						//用户id
    private String userName;				//用户名
    private String phone;					//手机号
    private String userType;				//客户类型（1.个人 2.大客户）
    private String weixincode;
    private String loginName;				//登录名
    private String password;
    private String passwordSalt;
    private Integer companyid;				//大客户用户所属公司
    private String businessType;
    private String idCard;					//个人-身份证号
    private Integer frontIdCard;
    private Integer backIdCard;
    private Integer handIdCard;
    private Date certificationSubmitTime;	//实名认证提交时间
    private Integer certificationState;		//审批结果 0：未实名；1：正在审核； 2：已实名；-1：审核驳回
    private String certificationStateS;
    private Date certificationAuditTime;	//实名认证审核时间
    private String certificationAuditUser;	//实名认证审核人
    private String certificationMemo;		//实名认证备注
    private String status;					//数据状态,1:正常,2:删除,3:禁用账号
    private String creater;
    private String operator;
    private Date createtime;
    private Date operatortime;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getWeixincode() {
		return weixincode;
	}
	public void setWeixincode(String weixincode) {
		this.weixincode = weixincode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public Integer getCompanyid() {
		return companyid;
	}
	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getFrontIdCard() {
		return frontIdCard;
	}
	public void setFrontIdCard(Integer frontIdCard) {
		this.frontIdCard = frontIdCard;
	}
	public Integer getBackIdCard() {
		return backIdCard;
	}
	public void setBackIdCard(Integer backIdCard) {
		this.backIdCard = backIdCard;
	}
	public Integer getHandIdCard() {
		return handIdCard;
	}
	public void setHandIdCard(Integer handIdCard) {
		this.handIdCard = handIdCard;
	}
	public Date getCertificationSubmitTime() {
		return certificationSubmitTime;
	}
	public void setCertificationSubmitTime(Date certificationSubmitTime) {
		this.certificationSubmitTime = certificationSubmitTime;
	}
	public Integer getCertificationState() {
		return certificationState;
	}
	public void setCertificationState(Integer certificationState) {
		this.certificationState = certificationState;
	}
	public String getCertificationStateS() {
		return certificationStateS;
	}
	public void setCertificationStateS(String certificationStateS) {
		this.certificationStateS = certificationStateS;
	}
	public Date getCertificationAuditTime() {
		return certificationAuditTime;
	}
	public void setCertificationAuditTime(Date certificationAuditTime) {
		this.certificationAuditTime = certificationAuditTime;
	}
	public String getCertificationAuditUser() {
		return certificationAuditUser;
	}
	public void setCertificationAuditUser(String certificationAuditUser) {
		this.certificationAuditUser = certificationAuditUser;
	}
	public String getCertificationMemo() {
		return certificationMemo;
	}
	public void setCertificationMemo(String certificationMemo) {
		this.certificationMemo = certificationMemo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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

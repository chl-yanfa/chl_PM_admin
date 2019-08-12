package com.car.auction.sys.dto;

import java.util.Date;

public class UserInfoDto {
	
	private String id;
	private String loginName;
	private String realName;
	private String password;
	private String passwordSalt;
	private String status;
	private String creater;
	private String operator;
	private Date createtime;
	private Date operatortime;
	
	private String roleIds;
	private String roleNms;
	private String areaIds;
	private String areaNms;
	
	private boolean isAdmin;
	
	
	public UserInfoDto() {}
	
	public UserInfoDto(String loginName) {
		this.loginName = loginName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNms() {
		return roleNms;
	}
	public void setRoleNms(String roleNms) {
		this.roleNms = roleNms;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getAreaIds() {
		return areaIds;
	}
	public void setAreaIds(String areaIds) {
		this.areaIds = areaIds;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"loginName\":\"" + loginName + "\",\"realName\":\"" + realName
				+ "\",\"password\":\"" + password + "\",\"passwordSalt\":\"" + passwordSalt + "\",\"status\":\""
				+ status + "\",\"creater\":\"" + creater + "\",\"operator\":\"" + operator + "\",\"createtime\":\""
				+ createtime + "\",\"operatortime\":\"" + operatortime + "\",\"roleIds\":\"" + roleIds
				+ "\",\"roleNms\":\"" + roleNms + "\",\"areaIds\":\"" + areaIds + "\",\"areaNms\":\"" + areaNms
				+ "\",\"isAdmin\":\"" + isAdmin + "\"}";
	}

	public String getAreaNms() {
		return areaNms;
	}
	public void setAreaNms(String areaNms) {
		this.areaNms = areaNms;
	}
	
}

package com.car.auction.organization.entity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：User
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午4:24:22
 * @version
 */
public class User {
	private String id;
	private String userName;
	private String roleId;
	private String roleName;
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
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}

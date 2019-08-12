package com.car.auction.sys.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：SysMenuInfoDto
 * 类描述：菜单表实体类
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午1:41:02
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午1:41:02
 * 修改备注： 
 * @version 
 *
 */
public class SysMenuInfoDto {
	private String id;
	private String menuName;
	private String menuUrl;
	private String menuDescribe;
	private String parentId;
	private String menuLevel;
	private String menuType;
	private int sort;
	private String icon;
	private String isEnable;
	private Integer isDelete;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	
	private String userId;
	private String roleId;
	private String menuChecked;
	
	private String isNotMenuLevel;
	
	private List<SysMenuInfoDto> children = new ArrayList<SysMenuInfoDto>();

	public SysMenuInfoDto clean() {
		return new SysMenuInfoDto();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuDescribe() {
		return menuDescribe;
	}

	public void setMenuDescribe(String menuDescribe) {
		this.menuDescribe = menuDescribe;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(String menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuChecked() {
		return menuChecked;
	}

	public void setMenuChecked(String menuChecked) {
		this.menuChecked = menuChecked;
	}

	public String getIsNotMenuLevel() {
		return isNotMenuLevel;
	}

	public void setIsNotMenuLevel(String isNotMenuLevel) {
		this.isNotMenuLevel = isNotMenuLevel;
	}

	public List<SysMenuInfoDto> getChildren() {
		return children;
	}

	public void setChildren(List<SysMenuInfoDto> children) {
		this.children = children;
	}

}

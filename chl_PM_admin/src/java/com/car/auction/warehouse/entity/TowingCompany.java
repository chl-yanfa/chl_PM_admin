package com.car.auction.warehouse.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：CarTowingCompany
 * 类描述：拖车公司数据
 * 创建时间： 2018年8月11日 下午3:16:17
 * @version
 */
public class TowingCompany {
	private String id; 				// id
	private String companyName; 	// 拖车公司
	private String contactPerson; 	// 联系人
	private String contactPhone; 	// 联系电话
	private String areas; 			// 所属分公司
	private Integer areasId; 		// 分公司id
	private String remark; 			// 备注
	private Date createTime; 		// 创建时间
	private Date updateTime; 		// 更新时间
	private Integer isDelete; 		// 是否删除
	private Integer page;
	private Integer rows;
	private Integer total;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName == null ? null : companyName.trim();
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson == null ? null : contactPerson.trim();
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone == null ? null : contactPhone.trim();
	}
	public String getAreas() {
		return areas;
	}
	public void setAreas(String areas) {
		this.areas = areas;
	}
	public Integer getAreasId() {
		return areasId;
	}
	public void setAreasId(Integer areasId) {
		this.areasId = areasId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}

}
package com.car.auction.warehouse.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehousePlacingAudit
 * 类描述：异常出库申请单审核表
 * 创建人：张婉欣
 * 创建时间： 2018年8月11日 下午3:33:49
 * @version
 */
public class WarehousePlacingAudit {
    private String id;					//审核id
    private String placingId;			//申请出库id
    private Integer isPass;				//同意|拒绝  0-同意 1-拒绝
    private String remark;				//备注
    private Date createTime;
    private Integer isDelete;			//是否删除 0-未删除 1-已删除
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlacingId() {
		return placingId;
	}
	public void setPlacingId(String placingId) {
		this.placingId = placingId;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
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
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
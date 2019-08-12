package com.car.auction.warehouse.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseAbnormalPlacing
 * 类描述：出库单(异常出库，正常出库)
 * 创建人：张婉欣
 * 创建时间： 2018年8月9日 下午3:38:52
 * @version
 */
public class WarehousePlacing {
    private String id;
    private String stockId;			//库存id
    private String carModel;		//车型
    private String licenseNum;		//车牌号
    private String carPlacing;		//车辆出库
    private Date carPlacingTime;	//车辆出库时间
    private String keyPlacing;		//钥匙出库
    private Date keyPlacingTime;	//钥匙出库时间
    private String licensePlacing;	//车牌出库
    private Date licensePlacingTime;//车牌出库时间
    private String amount;			//费用金额
    private String placingReason;	//出库原因
    private String proposer;		//申请人
    private Integer auditState;		//审核状态：0：无需审核；1：待审核；2：审核通过；-2：审核驳回
    private Integer isDelete;		//是否删除 ： 0-未删除；1-已删除
    private Date createTime;
    private Date updateTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getCarPlacing() {
		return carPlacing;
	}
	public void setCarPlacing(String carPlacing) {
		this.carPlacing = carPlacing;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCarPlacingTime() {
		return carPlacingTime;
	}
	public void setCarPlacingTime(Date carPlacingTime) {
		this.carPlacingTime = carPlacingTime;
	}
	public String getKeyPlacing() {
		return keyPlacing;
	}
	public void setKeyPlacing(String keyPlacing) {
		this.keyPlacing = keyPlacing;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getKeyPlacingTime() {
		return keyPlacingTime;
	}
	public void setKeyPlacingTime(Date keyPlacingTime) {
		this.keyPlacingTime = keyPlacingTime;
	}
	public String getLicensePlacing() {
		return licensePlacing;
	}
	public void setLicensePlacing(String licensePlacing) {
		this.licensePlacing = licensePlacing;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getLicensePlacingTime() {
		return licensePlacingTime;
	}
	public void setLicensePlacingTime(Date licensePlacingTime) {
		this.licensePlacingTime = licensePlacingTime;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPlacingReason() {
		return placingReason;
	}
	public void setPlacingReason(String placingReason) {
		this.placingReason = placingReason;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
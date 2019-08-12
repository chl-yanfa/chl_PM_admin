package com.car.auction.auction.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionWhole
 * 类描述：车辆列表页
 * 创建人：张婉欣
 * 创建时间： 2018年8月13日 下午2:16:27
 * @version
 */
public class AuctionWhole {
    private String id;					//拍品id
    private String auctionNo;			//拍品编号
    private String fullName;			//车辆标题
    private String lotAreas;			//区域
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private Date paimaiDate;			//拍卖日期
    private String registState;			//业务登记 
    private Integer registStateFlag;	//业务登记 
    private String procedureState;		//手续状态
    private String stockState;			//库存状态 
    private String subCompanyAudit;		//分公司审核
    private Integer subCompanyAuditFlag;//分公司审核
    private String setState;			//参拍设置
    private Integer setStateFlag;		//参拍设置
    private String vehicleState="未上拍";	//拍品状态
    private String imgPath;				//列表头像
    private Integer auctionTypeFlag;	//拍品类型
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(String auctionNo) {
		this.auctionNo = auctionNo;
	}
	public String getLotAreas() {
		return lotAreas;
	}
	public void setLotAreas(String lotAreas) {
		this.lotAreas = lotAreas;
	}
	public String getVehicleBrand() {
		return vehicleBrand;
	}
	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVehicleSystem() {
		return vehicleSystem;
	}
	public void setVehicleSystem(String vehicleSystem) {
		this.vehicleSystem = vehicleSystem;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPaimaiDate() {
		return paimaiDate;
	}
	public void setPaimaiDate(Date paimaiDate) {
		this.paimaiDate = paimaiDate;
	}
	public String getRegistState() {
		return registState;
	}
	public void setRegistState(String registState) {
		this.registState = registState;
	}
	public Integer getRegistStateFlag() {
		return registStateFlag;
	}
	public void setRegistStateFlag(Integer registStateFlag) {
		this.registStateFlag = registStateFlag;
	}
	public String getProcedureState() {
		return procedureState;
	}
	public void setProcedureState(String procedureState) {
		this.procedureState = procedureState;
	}
	public String getStockState() {
		return stockState;
	}
	public void setStockState(String stockState) {
		this.stockState = stockState;
	}
	public String getSubCompanyAudit() {
		return subCompanyAudit;
	}
	public void setSubCompanyAudit(String subCompanyAudit) {
		this.subCompanyAudit = subCompanyAudit;
	}
	public Integer getSubCompanyAuditFlag() {
		return subCompanyAuditFlag;
	}
	public void setSubCompanyAuditFlag(Integer subCompanyAuditFlag) {
		this.subCompanyAuditFlag = subCompanyAuditFlag;
	}
	public String getSetState() {
		return setState;
	}
	public void setSetState(String setState) {
		this.setState = setState;
	}
	public Integer getSetStateFlag() {
		return setStateFlag;
	}
	public void setSetStateFlag(Integer setStateFlag) {
		this.setStateFlag = setStateFlag;
	}
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getAuctionTypeFlag() {
		return auctionTypeFlag;
	}
	public void setAuctionTypeFlag(Integer auctionTypeFlag) {
		this.auctionTypeFlag = auctionTypeFlag;
	}
	
	
}
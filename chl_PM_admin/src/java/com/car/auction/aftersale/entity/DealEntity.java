package com.car.auction.aftersale.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：DealEntity
 * 类描述：成交列表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月11日 上午11:17:28
 */
public class DealEntity {
	private String id;						//拍品id
	private String auctionSetId;			//参拍id
	private String auctionNo;			//拍品编号
    private String fullName;			//车辆标题
    private String lotAreas;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;			//车牌号
    private String vehicleState;			//拍品状态
    private Date dealTime;					//成交时间
    private String payState;				//付款状态
    private Integer payStateFlag;			//付款状态
    private String takeCarState;			//提货状态
    private Integer takeCarStateFlag;
    private String imgPath;					//列表头像
    private Integer abortiveStateFlag;		//流拍状态
    private Integer auctionTypeFlag;		//拍品类型
    private Integer deferStateFlag;			//延期审核
    private Integer adjustPriceStateFlag;	//调价审核
    private Integer backCarStateFlag;		//退货审核
	public Integer getAuctionTypeFlag() {
		return auctionTypeFlag;
	}
	public void setAuctionTypeFlag(Integer auctionTypeFlag) {
		this.auctionTypeFlag = auctionTypeFlag;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLotAreas() {
		return lotAreas;
	}
	public void setLotAreas(String lotAreas) {
		this.lotAreas = lotAreas;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionSetId() {
		return auctionSetId;
	}
	public void setAuctionSetId(String auctionSetId) {
		this.auctionSetId = auctionSetId;
	}
	public String getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(String auctionNo) {
		this.auctionNo = auctionNo;
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
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public String getTakeCarState() {
		return takeCarState;
	}
	public void setTakeCarState(String takeCarState) {
		this.takeCarState = takeCarState;
	}
	public Integer getAbortiveStateFlag() {
		return abortiveStateFlag;
	}
	public void setAbortiveStateFlag(Integer abortiveStateFlag) {
		this.abortiveStateFlag = abortiveStateFlag;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getPayStateFlag() {
		return payStateFlag;
	}
	public void setPayStateFlag(Integer payStateFlag) {
		this.payStateFlag = payStateFlag;
	}
	public Integer getTakeCarStateFlag() {
		return takeCarStateFlag;
	}
	public void setTakeCarStateFlag(Integer takeCarStateFlag) {
		this.takeCarStateFlag = takeCarStateFlag;
	}
	public Integer getDeferStateFlag() {
		return deferStateFlag;
	}
	public void setDeferStateFlag(Integer deferStateFlag) {
		this.deferStateFlag = deferStateFlag;
	}
	public Integer getAdjustPriceStateFlag() {
		return adjustPriceStateFlag;
	}
	public void setAdjustPriceStateFlag(Integer adjustPriceStateFlag) {
		this.adjustPriceStateFlag = adjustPriceStateFlag;
	}
	public Integer getBackCarStateFlag() {
		return backCarStateFlag;
	}
	public void setBackCarStateFlag(Integer backCarStateFlag) {
		this.backCarStateFlag = backCarStateFlag;
	}
}

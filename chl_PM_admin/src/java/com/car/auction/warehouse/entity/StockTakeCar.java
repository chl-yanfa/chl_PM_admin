package com.car.auction.warehouse.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：StockTakeCar
 * 类描述：提货单列表页
 * 创建人：张婉欣
 * @version
 */
public class StockTakeCar {
    private String auctionId;			//拍品id
    private String stockId;				//库存id
    private String auctionNo;			//拍品编号
    private String fullName;			//车辆标题
    private String lotAreas;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private Date paimaiDate;			//拍卖日期
    private String isPrint;				//是否打印
    private Integer printFlag;				//是否打印
    private String vehicleState="未上拍";	//拍品状态
    private String imgPath;				//列表头像
    private Integer auctionTypeFlag;	//拍品类型
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
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getStockId() {
		return stockId;
	}
	public void setStockId(String stockId) {
		this.stockId = stockId;
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
	public Date getPaimaiDate() {
		return paimaiDate;
	}
	public void setPaimaiDate(Date paimaiDate) {
		this.paimaiDate = paimaiDate;
	}
	public String getIsPrint() {
		return isPrint;
	}
	public void setIsPrint(String isPrint) {
		this.isPrint = isPrint;
	}
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	public Integer getPrintFlag() {
		return printFlag;
	}
	public void setPrintFlag(Integer printFlag) {
		this.printFlag = printFlag;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
package com.car.auction.warehouse.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：WarehouseWhole
 * 类描述：仓库管理列表页
 * 创建人：张婉欣
 * 创建时间： 2018年8月11日 上午10:57:45
 * @version
 */
public class WarehouseWhole {
    private String auctionId;			//拍品id
    private String stockId;				//库存id
    private String placingId;			//出库id
    private String auctionNo;			//拍品编号
    private String fullName;			//车辆标题
    private String lotAreas;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private Date paimaiDate;			//拍卖日期
    private String isWarehouse="未入库";	//入库状态
    private Integer warehouseFlag=0;
    private Date inStockDate;			//入库时间
    private Date outStockDate;			//出库时间
    private String key;					//正常钥匙：无|一把|两把
    private String keyState="未入库";		//钥匙状态
    private String carCard;				//车牌：两块|一块|无|损坏
    private String carCardState="未入库";	//车牌状态
    private String subCompanyAudit;		//分公司审核
    private String stockAddress;		//存放地
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
	public String getPlacingId() {
		return placingId;
	}
	public void setPlacingId(String placingId) {
		this.placingId = placingId;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPaimaiDate() {
		return paimaiDate;
	}
	public void setPaimaiDate(Date paimaiDate) {
		this.paimaiDate = paimaiDate;
	}
	public String getIsWarehouse() {
		return isWarehouse;
	}
	public void setIsWarehouse(String isWarehouse) {
		this.isWarehouse = isWarehouse;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getInStockDate() {
		return inStockDate;
	}
	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getOutStockDate() {
		return outStockDate;
	}
	public void setOutStockDate(Date outStockDate) {
		this.outStockDate = outStockDate;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
		if(StringUtils.isNotBlank(key)) {
			if(key.equals("一把")||key.equals("两把")) {
				keyState="已入库";
			}
		}
	}
	public String getKeyState() {
		return keyState;
	}
	public void setKeyState(String keyState) {
		this.keyState = keyState;
	}
	public String getCarCard() {
		return carCard;
	}
	public void setCarCard(String carCard) {
		this.carCard = carCard;
		if(StringUtils.isNotBlank(carCard)) {
			if(carCard.equals("两块")||carCard.equals("一块")||carCard.equals("损坏")) {
				carCardState="已入库";
			}
		}
	}
	public String getCarCardState() {
		return carCardState;
	}
	public void setCarCardState(String carCardState) {
		this.carCardState = carCardState;
	}
	public String getSubCompanyAudit() {
		return subCompanyAudit;
	}
	public void setSubCompanyAudit(String subCompanyAudit) {
		this.subCompanyAudit = subCompanyAudit;
	}
	public String getStockAddress() {
		return stockAddress;
	}
	public void setStockAddress(String stockAddress) {
		this.stockAddress = stockAddress;
	}
	public Integer getWarehouseFlag() {
		return warehouseFlag;
	}
	public void setWarehouseFlag(Integer warehouseFlag) {
		this.warehouseFlag = warehouseFlag;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}
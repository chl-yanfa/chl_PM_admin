package com.car.auction.warehouse.entity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：TakeCarBill
 * 类描述：提货单
 * 创建人：张婉欣
 * @version
 */
public class TakeCarBill {
    private String auctionId;			//拍品id
    private String fullName;			//车辆标题
    private String lotAreas;
    private String licenseNumber;		//车牌号
    private String paimaiDateOrder;		//拍卖期次及序号
    private String vin;					//车架号
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String key;					//正常钥匙：无|一把|两把
    private String bailor;				//委托人
    private String phone;				//电话
    private String idCard;				//身份证
    private String qrCode;				//二维码
    private String statement;			//声明
    private Integer auctionTypeFlag;	//拍品类型
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
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
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getPaimaiDateOrder() {
		return paimaiDateOrder;
	}
	public void setPaimaiDateOrder(String paimaiDateOrder) {
		this.paimaiDateOrder = paimaiDateOrder;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getBailor() {
		return bailor;
	}
	public void setBailor(String bailor) {
		this.bailor = bailor;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public Integer getAuctionTypeFlag() {
		return auctionTypeFlag;
	}
	public void setAuctionTypeFlag(Integer auctionTypeFlag) {
		this.auctionTypeFlag = auctionTypeFlag;
	}
	
}
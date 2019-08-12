package com.car.auction.paimai.entity;

import java.math.BigDecimal;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionCar
 * 类描述：参拍车辆
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月29日 下午5:52:09
 */
public class AuctionCar {
	private String auctionId;			//拍品id
	private String auctionSetId;		//参拍id
	private String auctionNo;			//拍品编号
    private String fullName;			//车辆标题
    private String lotAreas;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private String pmhId;				//拍卖会ID
    private Integer pmOrder;			//拍卖序号
    private String orderStateS;			//拍品状态
    private String orderState;			//拍品状态
    private String dealMid;				//买受人memberCode
    private String dealMember;			//买受人
    private BigDecimal startingPrice=BigDecimal.ZERO;		//起拍价
    private BigDecimal curPrice=BigDecimal.ZERO;			//当前价
    private BigDecimal reservePrice=BigDecimal.ZERO;		//保留价
    private BigDecimal auctionCashDeposit=BigDecimal.ZERO;	//参拍保证金
    private String imgPath;				//列表头像
    private Integer auctionTypeFlag;	//拍品类型
    private String vin;					//车架号
	private String parkAddress;			//停放地址
	private String phone;				//手机号
	private String idCard;			//身份证号码
	private BigDecimal jointPrice=BigDecimal.ZERO;				//合手价
	private BigDecimal highestPrice=BigDecimal.ZERO;		//最终成交价
	private BigDecimal otherPrice=BigDecimal.ZERO;			//最终其他费用
	private BigDecimal commission=BigDecimal.ZERO;	//最终佣金
    private BigDecimal totalPrice=BigDecimal.ZERO;			//总计
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
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
	public String getPmhId() {
		return pmhId;
	}
	public void setPmhId(String pmhId) {
		this.pmhId = pmhId;
	}
	public Integer getPmOrder() {
		return pmOrder;
	}
	public void setPmOrder(Integer pmOrder) {
		this.pmOrder = pmOrder;
	}
	public String getOrderStateS() {
		return orderStateS;
	}
	public void setOrderStateS(String orderStateS) {
		this.orderStateS = orderStateS;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getDealMid() {
		return dealMid;
	}
	public void setDealMid(String dealMid) {
		this.dealMid = dealMid;
	}
	public String getDealMember() {
		return dealMember;
	}
	public void setDealMember(String dealMember) {
		this.dealMember = dealMember;
	}
	public BigDecimal getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(BigDecimal startingPrice) {
		this.startingPrice = startingPrice;
	}
	public BigDecimal getCurPrice() {
		return curPrice;
	}
	public void setCurPrice(BigDecimal curPrice) {
		this.curPrice = curPrice;
	}
	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
	}
	public BigDecimal getAuctionCashDeposit() {
		return auctionCashDeposit;
	}
	public void setAuctionCashDeposit(BigDecimal auctionCashDeposit) {
		this.auctionCashDeposit = auctionCashDeposit;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getAuctionTypeFlag() {
		return auctionTypeFlag;
	}
	public void setAuctionTypeFlag(Integer auctionTypeFlag) {
		this.auctionTypeFlag = auctionTypeFlag;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getParkAddress() {
		return parkAddress;
	}
	public void setParkAddress(String parkAddress) {
		this.parkAddress = parkAddress;
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
	public BigDecimal getJointPrice() {
		return jointPrice;
	}
	public void setJointPrice(BigDecimal jointPrice) {
		this.jointPrice = jointPrice;
	}
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}
	public BigDecimal getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(BigDecimal otherPrice) {
		this.otherPrice = otherPrice;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
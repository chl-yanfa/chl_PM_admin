package com.car.auction.aftersale.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：DealInfo
 * 类描述：成交列表-详情-成交信息
 * 创建人：zhangwanxin
 * 创建时间： 2018年10月17日 下午3:03:35
 */
public class DealInfo {
	private String fullName;//品牌型号
	private String vin;
    private String licenseNumber;		//车牌号
	private String paimaiName;			//拍卖会名称
	private Date auctionStartTime;		//拍卖时间
	private String dealMember;			//买受人
	private String dealPhone;			//买受人联系电话
	private String dealIdCard;			//买受人身份证号
	private BigDecimal startingPrice = BigDecimal.ZERO;	//起拍价
    private BigDecimal reservePrice = BigDecimal.ZERO;	//保留价
	private BigDecimal jointPrice = BigDecimal.ZERO;	//合手价
	private BigDecimal highestPrice = BigDecimal.ZERO;	//车款
	private BigDecimal commission = BigDecimal.ZERO;	//佣金
	private BigDecimal otherPrice = BigDecimal.ZERO;	//其他费用
	private Date dealTime;				//成交时间
	private String dealMid;				//买受人id
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getAuctionStartTime() {
		return auctionStartTime;
	}
	public void setAuctionStartTime(Date auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getPaimaiName() {
		return paimaiName;
	}
	public void setPaimaiName(String paimaiName) {
		this.paimaiName = paimaiName;
	}
	public String getDealMember() {
		return dealMember;
	}
	public void setDealMember(String dealMember) {
		this.dealMember = dealMember;
	}
	public String getDealPhone() {
		return dealPhone;
	}
	public void setDealPhone(String dealPhone) {
		this.dealPhone = dealPhone;
	}
	public String getDealIdCard() {
		return dealIdCard;
	}
	public void setDealIdCard(String dealIdCard) {
		this.dealIdCard = dealIdCard;
	}
	public String getDealMid() {
		return dealMid;
	}
	public void setDealMid(String dealMid) {
		this.dealMid = dealMid;
	}
	public BigDecimal getStartingPrice() {
		return startingPrice;
	}
	public void setStartingPrice(BigDecimal startingPrice) {
		this.startingPrice = startingPrice;
	}
	public BigDecimal getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(BigDecimal reservePrice) {
		this.reservePrice = reservePrice;
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
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(BigDecimal otherPrice) {
		this.otherPrice = otherPrice;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	
}

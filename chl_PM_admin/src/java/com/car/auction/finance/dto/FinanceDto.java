package com.car.auction.finance.dto;

import java.util.Date;


public class FinanceDto {
	private Integer auctionType;		//拍品类型：0车辆|1物资|2其他
	private Date paimaiDateStart;		//拍卖日期起
	private Date paimaiDateEnd;			//拍卖日期截止
	private Date paimaiDate;			//拍卖日期
	private Date dealDateStart;
	private Date dealDateEnd;
	private String drivingBrand;		//品牌车型-品牌
	private String licenseNumber;		//车牌号
	private Integer aftersaleState;		//财务类型
	private String keywords;			//条件搜索关键词
	private Integer page;
	private Integer rows;
	private Integer total;
	public Integer getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(Integer auctionType) {
		this.auctionType = auctionType;
	}
	public Date getPaimaiDateStart() {
		return paimaiDateStart;
	}
	public void setPaimaiDateStart(Date paimaiDateStart) {
		this.paimaiDateStart = paimaiDateStart;
	}
	public Date getPaimaiDateEnd() {
		return paimaiDateEnd;
	}
	public void setPaimaiDateEnd(Date paimaiDateEnd) {
		this.paimaiDateEnd = paimaiDateEnd;
	}
	public Date getPaimaiDate() {
		return paimaiDate;
	}
	public void setPaimaiDate(Date paimaiDate) {
		this.paimaiDate = paimaiDate;
	}
	public Date getDealDateStart() {
		return dealDateStart;
	}
	public void setDealDateStart(Date dealDateStart) {
		this.dealDateStart = dealDateStart;
	}
	public Date getDealDateEnd() {
		return dealDateEnd;
	}
	public void setDealDateEnd(Date dealDateEnd) {
		this.dealDateEnd = dealDateEnd;
	}
	public String getDrivingBrand() {
		return drivingBrand;
	}
	public void setDrivingBrand(String drivingBrand) {
		this.drivingBrand = drivingBrand;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public Integer getAftersaleState() {
		return aftersaleState;
	}
	public void setAftersaleState(Integer aftersaleState) {
		this.aftersaleState = aftersaleState;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
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

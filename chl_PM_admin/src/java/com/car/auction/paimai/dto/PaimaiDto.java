package com.car.auction.paimai.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiDto
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月16日 下午1:48:12
 * @version
 */
public class PaimaiDto {
    private Integer auctionType;		//拍品类型：0车辆|1物资|2其他
    private String salesman;			//业务员
    private Integer auctionPlatformFlag;//拍卖平台 0-国投互联|1-中拍协
    private String auctionPlatform;		//拍卖平台 国投互联|中拍协
    private String channel;				//渠道
    private Date paimaiDateStart;		//拍卖日期开始
    private Date paimaiDateEnd;			//拍卖日期截止
    private Date paimaiDate;			//预计拍卖日期
    private String paimaiName;			//拍卖会名称
    private String licenseNumber;		//车牌号
    private String drivingBrand;		//品牌车型-品牌
    private String imgPath;				//列表头像
    private Integer paimaiState;		//拍卖会状态
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
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public Integer getAuctionPlatformFlag() {
		return auctionPlatformFlag;
	}
	public void setAuctionPlatformFlag(Integer auctionPlatformFlag) {
		this.auctionPlatformFlag = auctionPlatformFlag;
	}
	public String getAuctionPlatform() {
		return auctionPlatform;
	}
	public void setAuctionPlatform(String auctionPlatform) {
		this.auctionPlatform = auctionPlatform;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPaimaiDate() {
		return paimaiDate;
	}
	public void setPaimaiDate(Date paimaiDate) {
		this.paimaiDate = paimaiDate;
	}
	public String getPaimaiName() {
		return paimaiName;
	}
	public void setPaimaiName(String paimaiName) {
		this.paimaiName = paimaiName;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getDrivingBrand() {
		return drivingBrand;
	}
	public void setDrivingBrand(String drivingBrand) {
		this.drivingBrand = drivingBrand;
	}
	public Integer getPaimaiState() {
		return paimaiState;
	}
	public void setPaimaiState(Integer paimaiState) {
		this.paimaiState = paimaiState;
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
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}

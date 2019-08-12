package com.car.auction.auction.entity;

import java.util.Date;
import java.util.List;

import com.car.auction.procedure.entity.ProcedureFile;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionScrap
 * 类描述：报废列表页
 * 创建人：张婉欣
 * @version
 */
public class AuctionScrap {
    private String id;					//拍品id
    private String auctionNo;			//拍品编码
    private String fullName;			//车辆标题
    private String lotAreas;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private List<ProcedureFile> procedureFileList;
    private Date submitTime;			//提交报废时间
    private String scrapState;			//报废状态
    private Integer scrapStateFlag;		//报废状态
    private String submitUser;			//报废人
    private Date scrapTime;				//确认报废时间
    private String imgPath;				//列表头像
    private Integer dataSource;			//数据来源 0-新增；1-文件导入
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
	public List<ProcedureFile> getProcedureFileList() {
		return procedureFileList;
	}
	public void setProcedureFileList(List<ProcedureFile> procedureFileList) {
		this.procedureFileList = procedureFileList;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public String getScrapState() {
		return scrapState;
	}
	public void setScrapState(String scrapState) {
		this.scrapState = scrapState;
	}
	public Integer getScrapStateFlag() {
		return scrapStateFlag;
	}
	public void setScrapStateFlag(Integer scrapStateFlag) {
		this.scrapStateFlag = scrapStateFlag;
	}
	public String getSubmitUser() {
		return submitUser;
	}
	public void setSubmitUser(String submitUser) {
		this.submitUser = submitUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getScrapTime() {
		return scrapTime;
	}
	public void setScrapTime(Date scrapTime) {
		this.scrapTime = scrapTime;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getDataSource() {
		return dataSource;
	}
	public void setDataSource(Integer dataSource) {
		this.dataSource = dataSource;
	}
}
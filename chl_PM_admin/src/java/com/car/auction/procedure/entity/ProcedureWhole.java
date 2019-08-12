package com.car.auction.procedure.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Procedure
 * 类描述：手续管理-列表页显示
 * 创建人：张婉欣
 * 创建时间： 2018年8月10日 上午9:45:53
 * @version
 */
public class ProcedureWhole {
	private String auctionId;			//拍品id
	private String fullName;			//车辆标题
	private String procedureId;			//手续id
	private String auctionNo;			//拍品编码
	private String lotAreas;
	private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private Date newestInTime;			//最新入库时间
    private Date latestOutTime;			//最后出库时间
    private String subCompanyAudit;		//分公司审核
    private String isRecord;			//是否归档
    private String vehicleState="未上拍";	//拍品状态
    private String imgPath;				//列表头像
	private List<ProcedureFile> procedureFileList;
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
	public String getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
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
	public Date getNewestInTime() {
		return newestInTime;
	}
	public void setNewestInTime(Date newestInTime) {
		this.newestInTime = newestInTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getLatestOutTime() {
		return latestOutTime;
	}
	public void setLatestOutTime(Date latestOutTime) {
		this.latestOutTime = latestOutTime;
	}
	public String getSubCompanyAudit() {
		return subCompanyAudit;
	}
	public void setSubCompanyAudit(String subCompanyAudit) {
		this.subCompanyAudit = subCompanyAudit;
	}
	public String getIsRecord() {
		return isRecord;
	}
	public void setIsRecord(String isRecord) {
		this.isRecord = isRecord;
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
	public List<ProcedureFile> getProcedureFileList() {
		return procedureFileList;
	}
	public void setProcedureFileList(List<ProcedureFile> procedureFileList) {
		this.procedureFileList = procedureFileList;
	}
	public Integer getAuctionTypeFlag() {
		return auctionTypeFlag;
	}
	public void setAuctionTypeFlag(Integer auctionTypeFlag) {
		this.auctionTypeFlag = auctionTypeFlag;
	}
	
}

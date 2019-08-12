package com.car.auction.auction.entity;

import java.util.Date;
import java.util.List;

import com.car.auction.auction.dto.AuctionFileDto;
import com.car.auction.scraporder.dto.CarScrapOrderAutopartsDto;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * 项目名称：SDIC-Inner
 * 类名称：Auction
 * 类描述：拍品数据表实现类
 * 创建人：张婉欣
 * 创建时间： 2018年7月9日 
 * 修改人：张婉欣
 * 修改时间： 2018年7月9日 
 * 修改备注： 
 * @version 
 *
 */
public class Auction {
    private String id;
    private Integer auctionType;		//拍品类型(0:全车配件,1:高价值配件,2:大宗物资)
    private String auctionNo;			//拍品编号
    private String vin;					//车架号
    private String fullName;			//拍品名称
    private String fullNameText;
    private String vehicleBrand;		//品牌
    private String vehicleType;			//车型
    private String vehicleSystem;		//车系
    private String licenseNumber;		//车牌号
    private String lotAreas;			//所在地区
    private Integer lotAreasId;			//所在地区
    private Integer partsCount;			//数量
    private String partsWeight;			//重量
    private Date productionDate;		//出厂日期
    private String isHasKey;			//是否有钥匙 0-没有,1-有
    private String description;			//描述
    private String buyRequirement;		//购买要求
    private String lookContacts;		//看货联系人
    private String lookContactNumber;	//看货联系方式
    private String lookAddress;			//看货地址
    private String lookCoordinate;		//看货地址经纬度
    private String bidNotice;			//竞价须知
    private String specialNotice;		//特别提示
    
    private Integer auctionState;		//拍品状态(0:保存,1:未上拍,2:进入拍卖会,3:待发布,4:拍卖中,5:成交,6:售后,7:完结,-10:已退货)
    private Integer auctionAuditState;	//拍品审核状态(0:未审核,1:审核通过,-1:审核驳回)
    private Integer registState;		//登记状态(0:未登记,1:已登记,2:已驳回)
    private Integer procedureState;		//手续状态(0:待提交,1:已提交,2:已出库,3:已驳回)
    private Integer stockState;			//仓库状态(0:待入库,1:已入库,2:已出库,3:已驳回)
    private Integer auditState;			//参拍审核状态(0:未审核,1:审核通过,-1:审核驳回)
    private Integer setState;			//参拍设置(0:待设置,1:已设置,2:待审核,3:已驳回)
    private Integer aftersaleState;		//售后状态(0:待付款,1:已付款,2:待提货,3:已提货,4:待过户,5:已过户,-1:延期未付款,-3:延期未提货,-5:延期未过户,-10:退货)
    private Integer abortiveState;		//流拍状态(0:待处理,1:待审核,-1:驳回)
    private Integer isDelete;			//是否删除(0:有效,1:删除)
    private Date createTime;			//创建时间
    private String createUser;
    private Date updateTime;			//更新时间
    private String updateUser;
    private List<AuctionFileDto> auctionFileList;	//图片列表
    private Integer dataState;			//0保存 1提交
    private String fileIds;
    private String scrapOrderId;
    private List<String> autopartsIdList;
    private List<CarScrapOrderAutopartsDto> autopartsList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAuctionType() {
		return auctionType;
	}
	public void setAuctionType(Integer auctionType) {
		this.auctionType = auctionType;
	}
	public String getAuctionNo() {
		return auctionNo;
	}
	public void setAuctionNo(String auctionNo) {
		this.auctionNo = auctionNo;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullNameText() {
		return fullNameText;
	}
	public void setFullNameText(String fullNameText) {
		this.fullNameText = fullNameText;
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
	public String getLotAreas() {
		return lotAreas;
	}
	public void setLotAreas(String lotAreas) {
		this.lotAreas = lotAreas;
	}
	public Integer getLotAreasId() {
		return lotAreasId;
	}
	public void setLotAreasId(Integer lotAreasId) {
		this.lotAreasId = lotAreasId;
	}
	public Integer getPartsCount() {
		return partsCount;
	}
	public void setPartsCount(Integer partsCount) {
		this.partsCount = partsCount;
	}
	public String getPartsWeight() {
		return partsWeight;
	}
	public void setPartsWeight(String partsWeight) {
		this.partsWeight = partsWeight;
	}
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public String getIsHasKey() {
		return isHasKey;
	}
	public void setIsHasKey(String isHasKey) {
		this.isHasKey = isHasKey;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBuyRequirement() {
		return buyRequirement;
	}
	public void setBuyRequirement(String buyRequirement) {
		this.buyRequirement = buyRequirement;
	}
	public String getLookContacts() {
		return lookContacts;
	}
	public void setLookContacts(String lookContacts) {
		this.lookContacts = lookContacts;
	}
	public String getLookContactNumber() {
		return lookContactNumber;
	}
	public void setLookContactNumber(String lookContactNumber) {
		this.lookContactNumber = lookContactNumber;
	}
	public String getLookAddress() {
		return lookAddress;
	}
	public void setLookAddress(String lookAddress) {
		this.lookAddress = lookAddress;
	}
	public String getLookCoordinate() {
		return lookCoordinate;
	}
	public void setLookCoordinate(String lookCoordinate) {
		this.lookCoordinate = lookCoordinate;
	}
	public String getBidNotice() {
		return bidNotice;
	}
	public void setBidNotice(String bidNotice) {
		this.bidNotice = bidNotice;
	}
	public String getSpecialNotice() {
		return specialNotice;
	}
	public void setSpecialNotice(String specialNotice) {
		this.specialNotice = specialNotice;
	}
	public Integer getAuctionState() {
		return auctionState;
	}
	public void setAuctionState(Integer auctionState) {
		this.auctionState = auctionState;
	}
	public Integer getAuctionAuditState() {
		return auctionAuditState;
	}
	public void setAuctionAuditState(Integer auctionAuditState) {
		this.auctionAuditState = auctionAuditState;
	}
	public Integer getRegistState() {
		return registState;
	}
	public void setRegistState(Integer registState) {
		this.registState = registState;
	}
	public Integer getProcedureState() {
		return procedureState;
	}
	public void setProcedureState(Integer procedureState) {
		this.procedureState = procedureState;
	}
	public Integer getStockState() {
		return stockState;
	}
	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public Integer getSetState() {
		return setState;
	}
	public void setSetState(Integer setState) {
		this.setState = setState;
	}
	public Integer getAftersaleState() {
		return aftersaleState;
	}
	public void setAftersaleState(Integer aftersaleState) {
		this.aftersaleState = aftersaleState;
	}
	public Integer getAbortiveState() {
		return abortiveState;
	}
	public void setAbortiveState(Integer abortiveState) {
		this.abortiveState = abortiveState;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public List<AuctionFileDto> getAuctionFileList() {
		return auctionFileList;
	}
	public void setAuctionFileList(List<AuctionFileDto> auctionFileList) {
		this.auctionFileList = auctionFileList;
	}
	public Integer getDataState() {
		return dataState;
	}
	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getScrapOrderId() {
		return scrapOrderId;
	}
	public void setScrapOrderId(String scrapOrderId) {
		this.scrapOrderId = scrapOrderId;
	}
	public List<String> getAutopartsIdList() {
		return autopartsIdList;
	}
	public void setAutopartsIdList(List<String> autopartsIdList) {
		this.autopartsIdList = autopartsIdList;
	}
	public List<CarScrapOrderAutopartsDto> getAutopartsList() {
		return autopartsList;
	}
	public void setAutopartsList(List<CarScrapOrderAutopartsDto> autopartsList) {
		this.autopartsList = autopartsList;
	}
}
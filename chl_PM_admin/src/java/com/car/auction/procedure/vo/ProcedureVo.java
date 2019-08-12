/**
 * 
 */
package com.car.auction.procedure.vo;

import java.util.Date;

import com.car.auction.procedure.entity.Procedure;

/**
 * 类名称：ProcedureVo
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-04-01 15:03
 */

public class ProcedureVo extends Procedure{
	private String id;
    private String auctionId;				//拍品ID
    private String entrustContractNo;		//委托合同编号
    private String handoverBillNo;			//交接清单编号
    private String province;				//手续地址省
    private String provinceId;				//手续地址省id
    private String city;					//手续地址市
    private String cityId;					//手续地址市id
    private String area;					//手续地址区
    private String areaId;					//手续地址区id
    private String address;					//手续详细地址
	private String fileTypes;
    private String fileIds;	
    private Integer auditState;				//审核状态：0-未审核；1-审核通过；-1：审核驳回
    private Date createTime;
    private Date updateTime;
    private Integer version;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public String getEntrustContractNo() {
		return entrustContractNo;
	}
	public void setEntrustContractNo(String entrustContractNo) {
		this.entrustContractNo = entrustContractNo;
	}
	public String getHandoverBillNo() {
		return handoverBillNo;
	}
	public void setHandoverBillNo(String handoverBillNo) {
		this.handoverBillNo = handoverBillNo;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
    
}

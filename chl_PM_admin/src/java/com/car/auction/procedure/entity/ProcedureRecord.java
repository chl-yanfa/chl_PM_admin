package com.car.auction.procedure.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureRecord
 * 类描述：手续完结归档表
 * 创建人：张婉欣
 * 创建时间： 2018年8月7日 下午3:14:20
 * @version
 */
public class ProcedureRecord {
    private String id;
    private String procedureId;			//手续id
    private String applicantId;			//申请人id
    private Integer transferInvoiceId;	//过户发票文件地址
    private Integer registrationId;		//登记证文件地址
    private String province;			//存档位置省
    private String provinceId;			//存档位置省id
    private String city;				//存档位置市
    private String cityId;				//存档位置id
    private String area;				//存档位置区
    private String areaId;				//存档位置区id
    private String recordAddress;		//存档位置
    private String remark;				//备注
    private Integer isDelete;			//是否删除 0-未删除 1-已删除
    private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}
	public String getApplicantId() {
		return applicantId;
	}
	public void setApplicantId(String applicantId) {
		this.applicantId = applicantId;
	}
	public Integer getTransferInvoiceId() {
		return transferInvoiceId;
	}
	public void setTransferInvoiceId(Integer transferInvoiceId) {
		this.transferInvoiceId = transferInvoiceId;
	}
	public Integer getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
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
	public String getRecordAddress() {
		return recordAddress;
	}
	public void setRecordAddress(String recordAddress) {
		this.recordAddress = recordAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
    
    
}
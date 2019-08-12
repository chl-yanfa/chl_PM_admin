package com.car.auction.auction.dto;

import java.util.Date;
import java.util.List;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionDto
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月14日 下午2:28:58
 * @version
 */
public class AuctionDto {
	private Integer auctionType;		//拍品类型
	private Integer subCompanyAuditId;	//分公司审核Id
	private Integer setStateId;			//参拍设置 0-待设置；1-已设置；2-待审核；3-已驳回
	private Date paimaiDateStart;		//拍卖日期起
	private Date paimaiDateEnd;			//拍卖日期截止
	private Date paimaiDate;			//拍卖日期
	private Integer registStateId;		//业务登记 0-未登记；1-已登记；2-已驳回
	private Integer procedureStateId;	//手续状态 0-待提交；1-已提交；2-已出库；3-已驳回
	private Integer stockStateId;		//库存状态 0-待入库；1-已入库；2-已出库；3-已驳回
	private String drivingBrand;		//品牌车型-品牌
	private String licenseNumber;		//车牌号
	private Integer auctionState;		//拍品类型
	private List<Integer> auctionStateList;	//拍品类型
	private Integer scrapState;
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
	public Integer getSubCompanyAuditId() {
		return subCompanyAuditId;
	}
	public void setSubCompanyAuditId(Integer subCompanyAuditId) {
		this.subCompanyAuditId = subCompanyAuditId;
	}
	public Integer getSetStateId() {
		return setStateId;
	}
	public void setSetStateId(Integer setStateId) {
		this.setStateId = setStateId;
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
	public Integer getRegistStateId() {
		return registStateId;
	}
	public void setRegistStateId(Integer registStateId) {
		this.registStateId = registStateId;
	}
	public Integer getProcedureStateId() {
		return procedureStateId;
	}
	public void setProcedureStateId(Integer procedureStateId) {
		this.procedureStateId = procedureStateId;
	}
	public Integer getStockStateId() {
		return stockStateId;
	}
	public void setStockStateId(Integer stockStateId) {
		this.stockStateId = stockStateId;
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
	public Integer getAuctionState() {
		return auctionState;
	}
	public void setAuctionState(Integer auctionState) {
		this.auctionState = auctionState;
	}
	public List<Integer> getAuctionStateList() {
		return auctionStateList;
	}
	public void setAuctionStateList(List<Integer> auctionStateList) {
		this.auctionStateList = auctionStateList;
	}
	public Integer getScrapState() {
		return scrapState;
	}
	public void setScrapState(Integer scrapState) {
		this.scrapState = scrapState;
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

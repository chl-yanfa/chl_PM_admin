package com.car.auction.warehouse.dto;

import java.util.Date;

public class WarehouseDto {
    private Integer auctionType;		//拍品类型：0车辆|1物资|2其他
    private Integer subCompanyAuditId;	//分公司审核id
    private Date paimaiDateStart;		//拍卖日期开始
    private Date paimaiDateEnd;			//拍卖日期截止
    private Date paimaiDate;			//拍卖日期
    private Date inStockDate;			//入库时间
    private Date outStockDate;			//出库时间
    private Integer stockState;	
    private Integer lotAreasId;
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
	public Date getInStockDate() {
		return inStockDate;
	}
	public void setInStockDate(Date inStockDate) {
		this.inStockDate = inStockDate;
	}
	public Date getOutStockDate() {
		return outStockDate;
	}
	public void setOutStockDate(Date outStockDate) {
		this.outStockDate = outStockDate;
	}
	public Integer getStockState() {
		return stockState;
	}
	public void setStockState(Integer stockState) {
		this.stockState = stockState;
	}
	public Integer getLotAreasId() {
		return lotAreasId;
	}
	public void setLotAreasId(Integer lotAreasId) {
		this.lotAreasId = lotAreasId;
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
package com.car.auction.procedure.dto;

import java.util.Date;

public class ProcedureDto {
	private Integer auctionType;		//拍品类型：0车辆|1物资|2其他
	private Integer subCompanyAuditId;	//分公司审核Id
	private Integer isRecordId;			//是否归档 0-未归档 1-已归档
	private Date inTimeStart;			//入库时间start
    private Date inTimeEnd;				//入库时间end
    private Date procedureMonthStart;	//入库统计时间起
    private Date procedureMonthEnd;		//入库统计时间止
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
	public Integer getIsRecordId() {
		return isRecordId;
	}
	public void setIsRecordId(Integer isRecordId) {
		this.isRecordId = isRecordId;
	}
	public Date getInTimeStart() {
		return inTimeStart;
	}
	public void setInTimeStart(Date inTimeStart) {
		this.inTimeStart = inTimeStart;
	}
	public Date getInTimeEnd() {
		return inTimeEnd;
	}
	public void setInTimeEnd(Date inTimeEnd) {
		this.inTimeEnd = inTimeEnd;
	}
	public Date getProcedureMonthStart() {
		return procedureMonthStart;
	}
	public void setProcedureMonthStart(Date procedureMonthStart) {
		this.procedureMonthStart = procedureMonthStart;
	}
	public Date getProcedureMonthEnd() {
		return procedureMonthEnd;
	}
	public void setProcedureMonthEnd(Date procedureMonthEnd) {
		this.procedureMonthEnd = procedureMonthEnd;
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
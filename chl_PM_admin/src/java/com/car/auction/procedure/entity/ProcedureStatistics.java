package com.car.auction.procedure.entity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureStatistics
 * 类描述：手续统计
 * 创建人：张婉欣
 * 创建时间： 2018年8月16日 上午9:31:57
 * @version
 */
public class ProcedureStatistics {
	private String procedureMonth;		//日期
	private Integer auctionCount=0;		//车辆总数
	private Integer dealCount=0;		//成交总数
	private Integer unTransOwnerCount=0;//未过户总数
	private Integer transOwnerCount=0;	//已过户总数
	private Integer recordCount=0;		//已归档
	private Integer unRecordCount=0;	//未归档
	public String getProcedureMonth() {
		return procedureMonth;
	}
	public void setProcedureMonth(String procedureMonth) {
		this.procedureMonth = procedureMonth;
	}
	public Integer getAuctionCount() {
		return auctionCount;
	}
	public void setAuctionCount(Integer auctionCount) {
		this.auctionCount = auctionCount;
	}
	public Integer getDealCount() {
		return dealCount;
	}
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	public Integer getUnTransOwnerCount() {
		return unTransOwnerCount;
	}
	public void setUnTransOwnerCount(Integer unTransOwnerCount) {
		this.unTransOwnerCount = unTransOwnerCount;
	}
	public Integer getTransOwnerCount() {
		return transOwnerCount;
	}
	public void setTransOwnerCount(Integer transOwnerCount) {
		this.transOwnerCount = transOwnerCount;
	}
	public Integer getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
	public Integer getUnRecordCount() {
		return unRecordCount;
	}
	public void setUnRecordCount(Integer unRecordCount) {
		this.unRecordCount = unRecordCount;
	}
}

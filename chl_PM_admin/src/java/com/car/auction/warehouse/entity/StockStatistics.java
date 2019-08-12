package com.car.auction.warehouse.entity;

/**
 * 项目名称：SDIC-Inner
 * 类名称：StockStatistics
 * 类描述：库存统计
 * 创建人：张婉欣
 * 创建时间： 2018年8月15日 下午2:49:58
 * @version
 */
public class StockStatistics {
	private String lotAreas; 			//分公司名称
    private Integer toPaimaiCount=0;		//待拍车辆数
	private Integer paimaiCount=0;		//拍卖中车辆数
	private Integer passInCount=0;		//流拍待处理车辆数
	private Integer noTakeCarCount=0;		//成交未提货辆数
	private Integer noWarehouseCount=0;	//未入库车辆数
	private Integer statistics=0;			//统计
	public String getLotAreas() {
		return lotAreas;
	}
	public void setLotAreas(String lotAreas) {
		this.lotAreas = lotAreas;
	}
	public Integer getToPaimaiCount() {
		return toPaimaiCount;
	}
	public void setToPaimaiCount(Integer toPaimaiCount) {
		this.toPaimaiCount = toPaimaiCount;
	}
	public Integer getPaimaiCount() {
		return paimaiCount;
	}
	public void setPaimaiCount(Integer paimaiCount) {
		this.paimaiCount = paimaiCount;
	}
	public Integer getPassInCount() {
		return passInCount;
	}
	public void setPassInCount(Integer passInCount) {
		this.passInCount = passInCount;
	}
	public Integer getNoTakeCarCount() {
		return noTakeCarCount;
	}
	public void setNoTakeCarCount(Integer noTakeCarCount) {
		this.noTakeCarCount = noTakeCarCount;
	}
	public Integer getNoWarehouseCount() {
		return noWarehouseCount;
	}
	public void setNoWarehouseCount(Integer noWarehouseCount) {
		this.noWarehouseCount = noWarehouseCount;
	}
	public Integer getStatistics() {
		return statistics;
	}
	public void setStatistics(Integer statistics) {
		this.statistics = statistics;
	}
}  
   
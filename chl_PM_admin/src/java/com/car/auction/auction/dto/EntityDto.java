package com.car.auction.auction.dto;

import com.car.auction.auction.entity.Auction;
import com.car.auction.procedure.entity.Procedure;
import com.car.auction.warehouse.entity.Warehouse;

/**
 * 项目名称：SDIC-Inner
 * 类名称：EntityDto
 * 类描述：
 * 创建人：张婉欣
 * 创建时间： 2018年8月10日 下午5:49:14
 * @version
 */
public class EntityDto {
	private Auction auction;
	private Procedure procedure;
	private Warehouse warehouse;
	public Auction getAuction() {
		return auction;
	}
	public void setAuction(Auction auction) {
		this.auction = auction;
	}
	public Procedure getProcedure() {
		return procedure==null?new Procedure():procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public Warehouse getWarehouse() {
		return warehouse==null?new Warehouse():warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
}

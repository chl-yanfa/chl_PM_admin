package com.car.auction.finance.entity;

import java.util.Date;

/**
 * 项目名称：	SDIC-Inner
 * 类名称：	PayWater
 * 类描述：	付款流水表
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月14日 下午1:42:13
 */
public class PayWater {
    private String id;
    private String mid;			//用户编码
    private String orderId;			//订单号
    private String orderNo;
    private String operatorCash;	//操作金额
    private Integer operatorType;	//操作类型 1-冻结过户保证金;2-解冻过户保证金;3-付款;5-退款;
    private String memo;			//描述
    private Integer isDelete;		//是否删除 0-未删除 1-已删除
    private String createUser;	//操作人用户名
    private Date createrTime;		//操作时间
    
	public PayWater() {}
	
	public PayWater(String id, String mid, String operatorCash, Integer operatorType, String memo,
			String createUser, String orderId) {
		this.id = id;
		this.mid = mid;
		this.operatorCash = operatorCash;
		this.operatorType = operatorType;
		this.memo = memo;
		this.createUser = createUser;
		this.orderId = orderId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getOperatorCash() {
		return operatorCash;
	}
	public void setOperatorCash(String operatorCash) {
		this.operatorCash = operatorCash;
	}
	public Integer getOperatorType() {
		return operatorType;
	}
	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreaterTime() {
		return createrTime;
	}

	public void setCreaterTime(Date createrTime) {
		this.createrTime = createrTime;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
    
}
package com.car.auction.paimai.entity;

/**
 * 项目名称：	sdicPM_interface
 * 类名称：	HighestCustomerInfo
 * 类描述：
 * 创建人：	zhangwanxin
 * 创建时间：	2018年12月10日18:04:13
 */
public class HighestCustomerInfo {
	private String orderId = "";
	private String mid = "";
	private String nickname = "";
	private String realname;		//真实姓名
	private String idCard="";	//身份证号
	private String phone = "";
	private Integer maximumPrice = 0;
	private Integer margin = 0;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getMaximumPrice() {
		return maximumPrice;
	}
	public void setMaximumPrice(Integer maximumPrice) {
		this.maximumPrice = maximumPrice;
	}
	public Integer getMargin() {
		return margin;
	}
	public void setMargin(Integer margin) {
		this.margin = margin;
	}
	
}

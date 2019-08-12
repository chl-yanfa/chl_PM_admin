/**
 * 
 */
package com.car.auction.scraporder.dto;

/**
 * 类名称：CarScrapOrderDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-04-11 10:33
 */

public class CarScrapOrderAutopartsDto {
	private String id;
	private String orderId;
	private String orderNo;
	private Integer partsType;
	private String partsName;
	private String partsNum;
	private String carModelNumber;
	private String carFrameNumber;
	private String carNumber;
	private String headImgPath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getPartsType() {
		return partsType;
	}
	public void setPartsType(Integer partsType) {
		this.partsType = partsType;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public String getPartsNum() {
		return partsNum;
	}
	public void setPartsNum(String partsNum) {
		this.partsNum = partsNum;
	}
	public String getCarModelNumber() {
		return carModelNumber;
	}
	public void setCarModelNumber(String carModelNumber) {
		this.carModelNumber = carModelNumber;
	}
	public String getCarFrameNumber() {
		return carFrameNumber;
	}
	public void setCarFrameNumber(String carFrameNumber) {
		this.carFrameNumber = carFrameNumber;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public String getHeadImgPath() {
		return headImgPath;
	}
	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
}

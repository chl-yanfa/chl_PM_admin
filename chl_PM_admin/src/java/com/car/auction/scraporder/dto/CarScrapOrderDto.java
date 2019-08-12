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

public class CarScrapOrderDto {
	private String id;
	private String orderNo;
	private String carModelNumber;
	private String carFrameNumber;
	private String carNumber;
	private Integer orderAreasId;
	private String orderAreas;
	private String headImgPath;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public Integer getOrderAreasId() {
		return orderAreasId;
	}
	public void setOrderAreasId(Integer orderAreasId) {
		this.orderAreasId = orderAreasId;
	}
	public String getOrderAreas() {
		return orderAreas;
	}
	public void setOrderAreas(String orderAreas) {
		this.orderAreas = orderAreas;
	}
	public String getHeadImgPath() {
		return headImgPath;
	}
	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}
}

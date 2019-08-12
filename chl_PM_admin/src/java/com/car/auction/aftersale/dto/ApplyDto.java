package com.car.auction.aftersale.dto;

import com.car.auction.aftersale.entity.AdjustPrice;
import com.car.auction.aftersale.entity.BackCar;
import com.car.auction.aftersale.entity.Defer;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ApplyDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月17日 下午5:40:37
 */
public class ApplyDto {
	private Defer defer;
	private AdjustPrice adjustPrice;
	private BackCar backCar;
	public Defer getDefer() {
		return defer;
	}
	public void setDefer(Defer defer) {
		this.defer = defer;
	}
	public AdjustPrice getAdjustPrice() {
		return adjustPrice;
	}
	public void setAdjustPrice(AdjustPrice adjustPrice) {
		this.adjustPrice = adjustPrice;
	}
	public BackCar getBackCar() {
		return backCar;
	}
	public void setBackCar(BackCar backCar) {
		this.backCar = backCar;
	}
	
}

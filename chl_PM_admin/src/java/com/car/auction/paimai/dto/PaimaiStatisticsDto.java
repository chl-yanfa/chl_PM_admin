package com.car.auction.paimai.dto;

import java.util.ArrayList;
import java.util.List;

import com.car.auction.paimai.entity.PaimaiHot;
import com.car.auction.paimai.entity.PaimaiStatistics;

/**
 * 项目名称：SDIC-Inner
 * 类名称：PaimaiStatisticsDto
 * 类描述：拍卖会统计
 * 创建人：zhangwanxin
 * 创建时间： 2018年10月9日 上午11:57:10
 */
public class PaimaiStatisticsDto {
	private List<PaimaiStatistics> vehicleList;
	private List<PaimaiStatistics> goodsList;
	private PaimaiStatistics allStatistics;
	private PaimaiHot paimaiHot;
	public List<PaimaiStatistics> getVehicleList() {
		return vehicleList==null?new ArrayList<PaimaiStatistics>():vehicleList;
	}
	public void setVehicleList(List<PaimaiStatistics> vehicleList) {
		this.vehicleList = vehicleList;
	}
	public List<PaimaiStatistics> getGoodsList() {
		return goodsList==null?new ArrayList<PaimaiStatistics>():goodsList;
	}
	public void setGoodsList(List<PaimaiStatistics> goodsList) {
		this.goodsList = goodsList;
	}
	public PaimaiStatistics getAllStatistics() {
		return allStatistics;
	}
	public void setAllStatistics(PaimaiStatistics allStatistics) {
		this.allStatistics = allStatistics;
	}
	public PaimaiHot getPaimaiHot() {
		return paimaiHot==null?new PaimaiHot():paimaiHot;
	}
	public void setPaimaiHot(PaimaiHot paimaiHot) {
		this.paimaiHot = paimaiHot;
	}
	
}

package com.car.auction.aftersale.dto;

import java.util.ArrayList;
import java.util.List;

import com.car.auction.aftersale.entity.PayTrack;
import com.car.auction.aftersale.entity.TakeCarTrack;

/**
 * 项目名称：SDIC-Inner
 * 类名称：TrackDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月14日 下午4:37:23
 */
public class TrackDto {
	private List<PayTrack> payTracks;
	private List<TakeCarTrack> takeCarTracks;
	public List<PayTrack> getPayTracks() {
		return payTracks==null?new ArrayList<PayTrack>():payTracks;
	}
	public void setPayTracks(List<PayTrack> payTracks) {
		this.payTracks = payTracks;
	}
	public List<TakeCarTrack> getTakeCarTracks() {
		return takeCarTracks==null?new ArrayList<TakeCarTrack>():takeCarTracks;
	}
	public void setTakeCarTracks(List<TakeCarTrack> takeCarTracks) {
		this.takeCarTracks = takeCarTracks;
	}
	
}

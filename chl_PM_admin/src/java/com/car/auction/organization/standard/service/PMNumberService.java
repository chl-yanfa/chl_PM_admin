package com.car.auction.organization.standard.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.organization.dao.PMNumberDao;
import com.car.auction.organization.entity.PMNumber;

@Service
public class PMNumberService {
	
	@Autowired
	private PMNumberDao pMNumberDao;

	/**
	 * getAuctionNo 生成拍品编号
	 * C-20180714-01 类型+年月日+序号（递增）
	 */
	public synchronized String getAuctionNo() {
		String format = "";
		StringBuffer sb=new StringBuffer();
		Date date = new Date();
		try {
			PMNumber pMNumber = pMNumberDao.getPMNumberByType(0);
			Integer number = pMNumber.getNumber();
			sb.append("C-");
			String time=new SimpleDateFormat("yyyyMMdd").format(date);//20180620
			sb.append(time+"-");
			DecimalFormat decimalFormat = new DecimalFormat("00");
			sb.append(decimalFormat.format(number));
			pMNumberDao.updatePMNumberById(pMNumber.getId());
			format=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	/**
	 * getOrderNo 生成订单编号
	 * D-20180714-01 类型+年月日+序号（递增）
	 */
	public synchronized String getOrderNo() {
		String format = "";
		StringBuffer sb=new StringBuffer();
		Date date = new Date();
		try {
			PMNumber pMNumber = pMNumberDao.getPMNumberByType(1);
			Integer number = pMNumber.getNumber();
			sb.append("D-");
			String time=new SimpleDateFormat("yyyyMMdd").format(date);//20180620
			sb.append(time+"-");
			DecimalFormat decimalFormat = new DecimalFormat("00");
			sb.append(decimalFormat.format(number));
			pMNumberDao.updatePMNumberById(pMNumber.getId());
			format=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	/**
	 * getPmhID 生成拍卖会ID
	 * PM-20180714-01  类型+年月日+序号（递增）
	 */
	public synchronized String getPmhID() {
		String format = "";
		StringBuffer sb=new StringBuffer();
		Date date = new Date();
		try {
			PMNumber pMNumber = pMNumberDao.getPMNumberByType(2);
			Integer number = pMNumber.getNumber();
			sb.append("PM-");
			String time=new SimpleDateFormat("yyyyMMdd").format(date);//20180620
			sb.append(time+"-");
			DecimalFormat decimalFormat = new DecimalFormat("00");
			sb.append(decimalFormat.format(number));
			pMNumberDao.updatePMNumberById(pMNumber.getId());
			format=sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	

}

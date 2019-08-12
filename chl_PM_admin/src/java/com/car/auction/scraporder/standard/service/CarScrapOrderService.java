package com.car.auction.scraporder.standard.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.common.Constants;
import com.car.auction.common.ResultDTO;
import com.car.auction.scraporder.dao.CarScrapOrderAuditDao;
import com.car.auction.scraporder.dao.CarScrapOrderAutopartsDao;
import com.car.auction.scraporder.dao.CarScrapOrderDao;
import com.car.auction.scraporder.dto.CarScrapOrderAutopartsDto;
import com.car.auction.scraporder.dto.CarScrapOrderDto;
import com.car.auction.scraporder.entity.CarScrapOrder;
import com.car.auction.scraporder.entity.CarScrapOrderAudit;
import com.car.auction.scraporder.entity.CarScrapOrderAutoparts;
import com.car.auction.scraporder.vo.CarScrapOrderVo; 

@Service
public class CarScrapOrderService {
	
	@Autowired
	private CarScrapOrderDao carScrapOrderDao;
	@Autowired
	private CarScrapOrderAuditDao carScrapOrderAuditDao;
	@Autowired
	private CarScrapOrderAutopartsDao carScrapOrderAutopartsDao;
	
	/**
	 * getCarScrapOrderList
	 * 根据条件获取‘整车列表’信息
	 */
	public ResultDTO<CarScrapOrderDto> getCarScrapOrderList(Integer page,Integer rows,CarScrapOrderVo vo) {
		ResultDTO<CarScrapOrderDto> result = new ResultDTO<CarScrapOrderDto>();
		List<CarScrapOrderDto> orderList = carScrapOrderDao.getCarScrapOrderList(page,rows,vo); 
		int total = carScrapOrderDao.getCarScrapOrderListTotal(vo); 
		result.setRows(orderList);
		result.setTotal(total);
		return result;
	}
	
	/**
	 * getCarScrapOrderAutopartsList
	 * 根据条件获取‘整车列表’信息
	 */
	public ResultDTO<CarScrapOrderAutopartsDto> getCarScrapOrderAutopartsList(Integer page,Integer rows,CarScrapOrderVo vo) {
		ResultDTO<CarScrapOrderAutopartsDto> result = new ResultDTO<CarScrapOrderAutopartsDto>();
		List<CarScrapOrderAutopartsDto> orderList = carScrapOrderDao.getCarScrapOrderAutopartsList(page,rows,vo); 
		int total = carScrapOrderDao.getCarScrapOrderAutopartsListTotal(vo); 
		result.setRows(orderList);
		result.setTotal(total);
		return result;
	}
	@Transactional
	public void addScrapOrder(Integer orderType,String orderId,String auctionId,String userId){
		Date date = new Date();
		if(orderType == Constants.ScrapOrderStatus.SCRAP_CAR) {
			//整车
			CarScrapOrder order = new CarScrapOrder();
			order.setId(orderId);
			order.setAuctionId(auctionId);
			order.setOrderStatus(Constants.ScrapOrderStatus.ORDER_AUCTION);
			order.setOperatortime(date);
			order.setOperator(userId);
			carScrapOrderDao.updateCarScrapOrder(order);
		}else {
			//旧件
			CarScrapOrderAutoparts autoparts = new CarScrapOrderAutoparts();
			autoparts.setId(orderId);
			autoparts.setAuctionId(auctionId);
			autoparts.setOrderAutopartsStatus(Constants.ScrapOrderStatus.ORDER_AUCTION);
			autoparts.setOperatortime(date);
			autoparts.setOperator(userId);
			carScrapOrderAutopartsDao.updateCarScrapOrderAutoparts(autoparts);
		}
		CarScrapOrderAudit audit = new CarScrapOrderAudit(orderId, null, 
				orderType+"", Constants.ScrapOrderStatus.ORDER_AUCTION+"", "拍卖", userId, date, userId, userId, date, date);
		carScrapOrderAuditDao.addCarScrapOrderAudit(audit);
	}
	@Transactional
	public void deleteScrapOrder(Integer orderType,String orderId,String auctionId,String userId){
		Date date = new Date();
		if(orderType == Constants.ScrapOrderStatus.SCRAP_CAR) {
			//整车
			CarScrapOrder order = new CarScrapOrder();
			order.setId(orderId);
			order.setAuctionId(null);
			order.setOrderStatus(Constants.ScrapOrderStatus.CAR_FINISH);
			order.setOperatortime(date);
			order.setOperator(userId);
			carScrapOrderDao.updateCarScrapOrder(order);
		}else {
			//旧件
			CarScrapOrderAutoparts autoparts = new CarScrapOrderAutoparts();
			autoparts.setId(orderId);
			autoparts.setAuctionId(null);
			autoparts.setOrderAutopartsStatus(Constants.ScrapOrderStatus.AUTOPARTS_FINISH);
			autoparts.setOperatortime(date);
			autoparts.setOperator(userId);
			carScrapOrderAutopartsDao.updateCarScrapOrderAutoparts(autoparts);
		}
		CarScrapOrderAudit audit = new CarScrapOrderAudit(orderId, null, 
				orderType+"", Constants.ScrapOrderStatus.ORDER_DEL_AUCTION+"", "删除配件", userId, date, userId, userId, date, date);
		carScrapOrderAuditDao.addCarScrapOrderAudit(audit);
	}
}

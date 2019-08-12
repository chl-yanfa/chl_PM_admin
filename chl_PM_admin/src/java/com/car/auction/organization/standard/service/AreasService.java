package com.car.auction.organization.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.organization.dao.AreasDao;
import com.car.auction.organization.entity.Areas;

@Service
public class AreasService {
	
	@Autowired
	private AreasDao areasDao;

	public List<Areas> getAreas(){
		List<Areas> areasList = areasDao.getAreas();
		return areasList;
	}

}

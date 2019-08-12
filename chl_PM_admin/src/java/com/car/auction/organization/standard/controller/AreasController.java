package com.car.auction.organization.standard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.organization.entity.Areas;
import com.car.auction.organization.standard.service.AreasService;


@Controller
@RequestMapping(value = "/areas")
public class AreasController{
	@Autowired
	private AreasService areasService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Areas> getAreas(){
		return areasService.getAreas();			
	}
	
}

package com.car.auction.warehouse.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.warehouse.entity.TowingCompany;
import com.car.auction.warehouse.standard.service.TowingCompanyService;


/**
 * TowingCompanyController
 */
@Controller
@RequestMapping(value = "/towingCompany")
public class TowingCompanyController extends BaseController{
	
	@Autowired
	private TowingCompanyService towingCompanyService;
	
	/**
	 * getTowingCompanyList(列表查询拖车公司)
	 * @param towingCompany
	 * @return
	 */
	@RequestMapping(value = "/getTowingCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<TowingCompany> getTowingCompanyList(TowingCompany towingCompany) {
		return towingCompanyService.getTowingCompanyList(towingCompany);
	}
	/**
	 * getTowingCompanyById(根据id查询拖车公司)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTowingCompanyById", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<TowingCompany> getTowingCompanyById(String id) {
		return towingCompanyService.getTowingCompanyById(id);
	}
	/**
	 * addTowingCompany(新增拖车公司)
	 * @param towingCompany
	 * @return
	 */
	@RequestMapping(value = "/addTowingCompany", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> addTowingCompany(TowingCompany towingCompany) {
		return towingCompanyService.addTowingCompany(towingCompany);
	}
	/**
	 * editTowingCompany(编辑拖车公司信息)
	 * @param towingCompany
	 * @return
	 */
	@RequestMapping(value = "/updateTowingCompany", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> updateTowingCompany(TowingCompany towingCompany) {
		return towingCompanyService.updateTowingCompany(towingCompany);
	}
	/**
	 * deleteTowingCompany(删除拖车公司)
	 * @param towingCompany
	 * @return
	 */
	@RequestMapping(value = "/deleteTowingCompany", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<String> deleteTowingCompany(String id) {
		return towingCompanyService.deleteTowingCompany(id);
	}

}

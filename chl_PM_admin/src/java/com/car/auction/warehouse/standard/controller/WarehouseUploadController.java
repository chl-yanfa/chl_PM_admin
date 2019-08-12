package com.car.auction.warehouse.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;
import com.car.auction.warehouse.dto.WarehouseFileDto;
import com.car.auction.warehouse.standard.service.WarehouseUploadService;


@Controller
@RequestMapping(value = "/warehouseupload")
public class WarehouseUploadController extends BaseController{
	
	@Autowired
	private WarehouseUploadService warehouseUploadService;
	
	/**
	 * 拍品信息编辑页新增图片
	 * @param stockId
	 * @param fileType
	 * @param sort
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload/{stockId}", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<WarehouseFileDto> upload(@PathVariable(value="stockId")String stockId,
			@RequestParam(value="fileType",required=true)Integer fileType,
			@RequestParam(value="sort",required=false)Integer sort,
			@RequestParam(value="file",required=true)MultipartFile file) {
		return warehouseUploadService.upload(stockId,fileType,sort,file);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> delete(@PathVariable(value="id")Integer id) {
		return warehouseUploadService.delete(id);
	}
}

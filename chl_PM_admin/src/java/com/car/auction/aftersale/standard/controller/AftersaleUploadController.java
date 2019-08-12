package com.car.auction.aftersale.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.aftersale.dto.AftersaleFileDto;
import com.car.auction.aftersale.standard.service.AftersaleUploadService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;


@Controller
@RequestMapping(value = "/aftersaleupload")
public class AftersaleUploadController extends BaseController{
	
	@Autowired
	private AftersaleUploadService aftersaleUploadService;
	
	/**
	 * 编辑页新增图片
	 * @param stockId
	 * @param fileType
	 * @param sort
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload/{scrapId}", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AftersaleFileDto> upload(@PathVariable(value="scrapId")String scrapId,
			@RequestParam(value="fileType",required=true)Integer fileType,
			@RequestParam(value="sort",required=false)Integer sort,
			@RequestParam(value="file",required=true)MultipartFile file) {
		return aftersaleUploadService.upload(scrapId,fileType,sort,file);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> delete(@PathVariable(value="id")Integer id) {
		return aftersaleUploadService.delete(id);
	}
}

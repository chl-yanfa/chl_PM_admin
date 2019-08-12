package com.car.auction.auction.standard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.auction.dto.AuctionFileDto;
import com.car.auction.auction.standard.service.AuctionUploadService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;


@Controller
@RequestMapping(value = "/auctionupload")
public class AuctionUploadController extends BaseController{
	
	@Autowired
	private AuctionUploadService auctionUploadService;
	
	/**
	 * 拍品信息编辑页新增图片
	 * @param auctionId
	 * @param fileType
	 * @param sort
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload/{auctionId}", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<AuctionFileDto> upload(@PathVariable(value="auctionId")String auctionId,
			@RequestParam(value="fileType",required=true)Integer fileType,
			@RequestParam(value="sort",required=false)Integer sort,
			@RequestParam(value="file",required=true)MultipartFile file) {
		return auctionUploadService.upload(auctionId,fileType,sort,file);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> delete(@PathVariable(value="id")Integer id) {
		return auctionUploadService.delete(id);
	}
}

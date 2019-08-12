package com.car.auction.procedure.standard.controller;

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
import com.car.auction.procedure.dto.ProcedureFileDto;
import com.car.auction.procedure.standard.service.ProcedureUploadService;


@Controller
@RequestMapping(value = "/procedureupload")
public class ProcedureUploadController extends BaseController{
	
	@Autowired
	private ProcedureUploadService procedureUploadService;
	
	/**
	 * 拍品信息编辑页新增图片
	 * @param auctionId
	 * @param fileType
	 * @param sort
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload/{procedureId}", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<ProcedureFileDto> upload(@PathVariable(value="procedureId")String procedureId,
			@RequestParam(value="fileType",required=true)Integer fileType,
			@RequestParam(value="version",required=true)Integer version,
			@RequestParam(value="file",required=true)MultipartFile file) {
		return procedureUploadService.upload(procedureId,fileType,version,file);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> delete(@PathVariable(value="id")Integer id) {
		return procedureUploadService.delete(id);
	}
}

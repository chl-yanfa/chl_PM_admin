package com.car.auction.attachment.standard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.attachment.entity.Attachment;
import com.car.auction.attachment.standard.service.AttachmentService;
import com.car.auction.common.BaseController;
import com.car.auction.common.ResultDTO;

@Controller
@RequestMapping(value = "/attachment")
public class AttachmentController extends BaseController{
	
	@Autowired
	private AttachmentService attachmentService;
	
	/**
	 * uploadSingle 上传图片（用于新增时）
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadSingle", method = RequestMethod.POST)
	@ResponseBody
	public ResultDTO<Attachment> uploadSingle(@RequestParam(value="file",required=true)MultipartFile file) {
		return attachmentService.uploadSingle(file);
	}
	
	/**
	 * delete 删除图片 用于新增时
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultDTO<String> delete(@PathVariable("id") Integer id) {
		return attachmentService.delete(id);
	}
	
	/**
	 * uploadUeditor 上传图片（用于新增时）
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/uploadUeditor")
	@ResponseBody
	public ResultDTO<Attachment> uploadUeditor(HttpServletRequest request) {
		return attachmentService.uploadUeditor(request);
	}
	
}





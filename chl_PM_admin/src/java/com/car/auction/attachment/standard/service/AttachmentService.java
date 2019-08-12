package com.car.auction.attachment.standard.service;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.attachment.dao.AttachmentDao;
import com.car.auction.attachment.entity.Attachment;
import com.car.auction.common.Applications;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants; 

@Service
public class AttachmentService {
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	/**
	 * uploadSingle 
	 */
	@Transactional
	public ResultDTO<Attachment> uploadSingle(MultipartFile file) {
		ResultDTO<Attachment> res = new ResultDTO<Attachment>();
		String filePath = null;
		if(file!=null && !file.isEmpty()) {
			if(file.getSize()>10485760) {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_UPLOAD_OVER);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_UPLOAD_OVER);
				return res;
			}
			filePath = getFilePath(file.getOriginalFilename());
			File newFile = new File(filePath); 
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				filePath = null; 
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_UPLOAD_FAIL);
				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_UPLOAD_FAIL);
			}
		}
		if(StringUtils.isNotBlank(filePath)) {
			filePath = getUrl(filePath);
			Attachment attachment = new Attachment(file.getOriginalFilename(), filePath, new Date(), true);
			int count = attachmentDao.addAttachment(attachment);
			if(count > 0) {
				res.setEntity(attachment);
			}
		}
		return res;
		
	}
	
	public Attachment upload(MultipartFile file) {
		String filePath = null;
		if(file!=null && !file.isEmpty()) {
			if(file.getSize()>10485760) {
				return null;
			}
			filePath = getFilePath(file.getOriginalFilename());
			File newFile = new File(filePath); 
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		if(StringUtils.isNotBlank(filePath)) {
			filePath = getUrl(filePath);
			Attachment attachment = new Attachment(file.getOriginalFilename(), filePath, new Date(), true);
			int count = attachmentDao.addAttachment(attachment);
			if(count > 0) {
				return attachment;
			}
		}
		return null;
		
	}
	/**
	 * deleteAttachment 
	 */
	@Transactional
	public ResultDTO<String> delete(Integer id) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(id == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Attachment attachment = new Attachment(id, new Date(), false);
		attachmentDao.updateAttachment(attachment);
		return res;
	}
	public int deleteFile(Integer id) {
		if(id == null) {
			return 0;
		}
		Attachment attachment = new Attachment(id, new Date(), false);
		return attachmentDao.updateAttachment(attachment);
	}
	
	/**
	 * @param sourceFileName
	 * @return
	 * EX:D://images/2018/11/09/2018110908372953901351.png
	 * EX:http://60.205.223.142/images/2018/11/09/2018110908372953901351.png
	 */
	private String getFilePath(String sourceFileName) {
		if(StringUtils.indexOf(sourceFileName, ".")<0){
			sourceFileName =sourceFileName+".jpg";
		}
        String baseFolder = Applications.getString("file.upload.path") + File.separator + "images";
        Date nowDate = new Date();
        // yyyy/MM/dd
        String fileFolder = baseFolder + File.separator + new DateTime(nowDate).toString("yyyy")
                + File.separator + new DateTime(nowDate).toString("MM") + File.separator
                + new DateTime(nowDate).toString("dd");
        File file = new File(fileFolder);
        if (!file.isDirectory()) {
            // 如果目录不存在，则创建目录
            file.mkdirs();
        }
        // 生成新的文件名
        String fileName = new DateTime(nowDate).toString("yyyyMMddHHmmssSSSS")
                + RandomUtils.nextInt(1000, 9999) + "." + StringUtils.substringAfterLast(sourceFileName, ".");
        return fileFolder + File.separator + fileName;
    }

	private String getUrl(String relativePath){
		 String picUrl = StringUtils.replace(
	                StringUtils.substringAfter(relativePath, Applications.getString("file.upload.path")), "\\", "/");
		 
		 
		 picUrl =  Applications.getString("file.read.path")+picUrl;
		 return picUrl;
	}
	
	/**
	 * uploadUeditor 
	 */
	@Transactional
	public ResultDTO<Attachment> uploadUeditor(HttpServletRequest request) {
		ResultDTO<Attachment> res = new ResultDTO<Attachment>();
//		String filePath = null;
//		if(file!=null && !file.isEmpty()) {
//			if(file.getSize()>10485760) {
//				res.setReturnCode(RtnMsgConstants.RETURN_CODE_UPLOAD_OVER);
//				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_UPLOAD_OVER);
//				return res;
//			}
//			filePath = getFilePath(file.getOriginalFilename());
//			File newFile = new File(filePath); 
//			try {
//				file.transferTo(newFile);
//			} catch (Exception e) {
//				e.printStackTrace();
//				filePath = null; 
//				res.setReturnCode(RtnMsgConstants.RETURN_CODE_UPLOAD_FAIL);
//				res.setReturnMsg(RtnMsgConstants.RETURN_MSG_UPLOAD_FAIL);
//			}
//		}
//		if(StringUtils.isNotBlank(filePath)) {
//			filePath = getUrl(filePath);
//			Attachment attachment = new Attachment(file.getOriginalFilename(), filePath, new Date(), true);
//			int count = attachmentDao.addAttachment(attachment);
//			if(count > 0) {
//				res.setEntity(attachment);
//			}
//		}
		return res;
		
	}

}

package com.car.auction.aftersale.standard.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.aftersale.dao.AftersaleFileDao;
import com.car.auction.aftersale.dto.AftersaleFileDto;
import com.car.auction.aftersale.entity.AftersaleFile;
import com.car.auction.attachment.entity.Attachment;
import com.car.auction.attachment.standard.service.AttachmentService;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants; 

@Service
public class AftersaleUploadService {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private AftersaleFileDao aftersaleFileDao;
	@Transactional
	public ResultDTO<AftersaleFileDto> upload(String scrapId,Integer fileType,Integer sort,MultipartFile file) {
		ResultDTO<AftersaleFileDto> res = new ResultDTO<AftersaleFileDto>();
		if(StringUtils.isBlank(scrapId)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Attachment attachment = attachmentService.upload(file);
		if(attachment!=null) {
			AftersaleFile aftersaleFile = new AftersaleFile( fileType, scrapId, attachment.getId(), sort);
			int count = aftersaleFileDao.addFile(aftersaleFile);
			if(count>0) {
				AftersaleFileDto aftersaleFileDto = new AftersaleFileDto();
				BeanUtils.copyProperties(aftersaleFile, aftersaleFileDto);
				aftersaleFileDto.setImgPath(attachment.getStoragePath());
				res.setEntity(aftersaleFileDto);
			}
		}
		return res;
	}
	@Transactional
	public ResultDTO<String> delete(Integer id) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(id == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		AftersaleFile aftersaleFile = aftersaleFileDao.getFileById(id);
		if(aftersaleFile == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_ERROR);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_ERROR);
			return res;
		}
		aftersaleFile.setIsDelete(1);
		aftersaleFileDao.updateFile(aftersaleFile);
		attachmentService.deleteFile(aftersaleFile.getAttachmentId());
		return res;
	}
}

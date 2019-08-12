package com.car.auction.procedure.standard.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.attachment.entity.Attachment;
import com.car.auction.attachment.standard.service.AttachmentService;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.procedure.dao.ProcedureFileDao;
import com.car.auction.procedure.dto.ProcedureFileDto;
import com.car.auction.procedure.entity.ProcedureFile; 

@Service
public class ProcedureUploadService {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private ProcedureFileDao procedureFileDao;
	@Transactional
	public ResultDTO<ProcedureFileDto> upload(String procedureId,Integer fileType,Integer version,MultipartFile file) {
		ResultDTO<ProcedureFileDto> res = new ResultDTO<ProcedureFileDto>();
		if(StringUtils.isBlank(procedureId) || fileType == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Attachment attachment = attachmentService.upload(file);
		if(attachment!=null) {
			ProcedureFile procedureFile = new ProcedureFile(procedureId, fileType, attachment.getId(), version);
			int count = procedureFileDao.addProcedureFile(procedureFile);
			if(count>0) {
				ProcedureFileDto procedureFileDto = new ProcedureFileDto();
				BeanUtils.copyProperties(procedureFile, procedureFileDto);
				procedureFileDto.setImgPath(attachment.getStoragePath());
				res.setEntity(procedureFileDto);
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
		ProcedureFile procedureFile = procedureFileDao.getProcedureFileById(id);
		if(procedureFile == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_ERROR);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_ERROR);
			return res;
		}
		procedureFile.setIsDelete(1);
		procedureFileDao.updateProcedureFile(procedureFile);
		attachmentService.deleteFile(procedureFile.getAttachmentId());
		return res;
	}
}

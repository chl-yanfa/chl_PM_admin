package com.car.auction.warehouse.standard.service;

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
import com.car.auction.warehouse.dao.WarehouseFileDao;
import com.car.auction.warehouse.dto.WarehouseFileDto;
import com.car.auction.warehouse.entity.WarehouseFile; 

@Service
public class WarehouseUploadService {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private WarehouseFileDao warehouseFileDao;
	@Transactional
	public ResultDTO<WarehouseFileDto> upload(String stockId,Integer fileType,Integer sort,MultipartFile file) {
		ResultDTO<WarehouseFileDto> res = new ResultDTO<WarehouseFileDto>();
		if(StringUtils.isBlank(stockId)) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Attachment attachment = attachmentService.upload(file);
		if(attachment!=null) {
			
			WarehouseFile warehouseFile = new WarehouseFile(stockId, fileType, attachment.getId(), sort);
			int count = warehouseFileDao.addWarehouseFile(warehouseFile);
			if(count>0) {
				WarehouseFileDto warehouseFileDto = new WarehouseFileDto();
				BeanUtils.copyProperties(warehouseFile, warehouseFileDto);
				warehouseFileDto.setImgPath(attachment.getStoragePath());
				res.setEntity(warehouseFileDto);
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
		WarehouseFile warehouseFile = warehouseFileDao.getWarehouseFileById(id);
		if(warehouseFile == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_ERROR);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_ERROR);
			return res;
		}
		warehouseFile.setIsDelete(1);
		warehouseFileDao.updateWarehouseFile(warehouseFile);
		attachmentService.deleteFile(warehouseFile.getAttachmentId());
		return res;
	}
}

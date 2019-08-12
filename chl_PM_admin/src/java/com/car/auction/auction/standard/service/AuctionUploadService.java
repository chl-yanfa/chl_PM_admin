package com.car.auction.auction.standard.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.car.auction.attachment.entity.Attachment;
import com.car.auction.attachment.standard.service.AttachmentService;
import com.car.auction.auction.dao.AuctionFileDao;
import com.car.auction.auction.dto.AuctionFileDto;
import com.car.auction.auction.entity.AuctionFile;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants; 

@Service
public class AuctionUploadService {
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private AuctionFileDao auctionFileDao;
	@Transactional
	public ResultDTO<AuctionFileDto> upload(String auctionId,Integer fileType,Integer sort,MultipartFile file) {
		ResultDTO<AuctionFileDto> res = new ResultDTO<AuctionFileDto>();
		if(StringUtils.isBlank(auctionId) || fileType == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_NULL);
			return res;
		}
		Attachment attachment = attachmentService.upload(file);
		if(attachment!=null) {
			AuctionFile auctionFile = new AuctionFile(auctionId, fileType, attachment.getId(), sort, 0, null);
			int count = auctionFileDao.addAuctionFile(auctionFile);
			if(count>0) {
				AuctionFileDto auctionFileDto = new AuctionFileDto();
				BeanUtils.copyProperties(auctionFile, auctionFileDto);
				auctionFileDto.setImgPath(attachment.getStoragePath());
				res.setEntity(auctionFileDto);
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
		AuctionFile auctionFile = auctionFileDao.getAuctionFileById(id);
		if(auctionFile == null) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_PARAMETER_ERROR);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_PARAMETER_ERROR);
			return res;
		}
		auctionFile.setIsDelete(1);
		auctionFileDao.updateAuctionFile(auctionFile);
		attachmentService.deleteFile(auctionFile.getAttachmentId());
		return res;
	}
}

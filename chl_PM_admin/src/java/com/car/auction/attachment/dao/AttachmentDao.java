package com.car.auction.attachment.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.attachment.entity.Attachment;
import com.car.auction.common.BaseDao;

@Component
public class AttachmentDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	public Attachment getAttachmentById(Integer id) {
		return baseDao.findOne("mapper.standard.AttachmentMapper.getAttachmentById", id);
	}
	
	public int addAttachment(Attachment attachment) {
		return baseDao.create("mapper.standard.AttachmentMapper.addAttachment", attachment);
	}
	
	public int updateAttachment(Attachment attachment) {
		return baseDao.update("mapper.standard.AttachmentMapper.updateAttachment", attachment);
	}
}

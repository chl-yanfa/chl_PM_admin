package com.car.auction.organization.standard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.organization.dao.LyMessageDao;
import com.car.auction.organization.entity.LyMessage;

/**
 * 类名称：LyMessageService
 */
@Service
public class LyMessageService {
	@Autowired 
	private LyMessageDao lyMessageDao;
	public ResultDTO<LyMessage> getLyMessageByLyids(List<String> lyidList,Integer page,Integer rows) {
		ResultDTO<LyMessage> result = new ResultDTO<LyMessage>();
		if(lyidList!=null&&lyidList.size()>0){
			List<LyMessage> lyMessageByLyids = lyMessageDao.getLyMessageByLyids(lyidList,page,rows);
			result.setRows(lyMessageByLyids);
			int total = lyMessageDao.getLyMessageCountByLyids(lyidList);
			result.setTotal(total);
			return result;			
		}else {
			result.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			result.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return result;
		}
	}
	
}

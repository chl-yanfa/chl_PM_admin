package com.car.auction.procedure.standard.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.car.auction.auction.dao.AuctionDao;
import com.car.auction.auction.dto.EntityDto;
import com.car.auction.auction.entity.Auction;
import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.common.UUIDUtil;
import com.car.auction.procedure.dao.ProcedureDao;
import com.car.auction.procedure.dao.ProcedureFileDao;
import com.car.auction.procedure.dao.ProcedureHistoryDao;
import com.car.auction.procedure.dao.ProcedurePlacingAuditDao;
import com.car.auction.procedure.dao.ProcedureRecordDao;
import com.car.auction.procedure.dao.ProcedureWholeDao;
import com.car.auction.procedure.dto.ProcedureDto;
import com.car.auction.procedure.dto.ProcedurePlacingDto;
import com.car.auction.procedure.entity.Procedure;
import com.car.auction.procedure.entity.ProcedureFile;
import com.car.auction.procedure.entity.ProcedureHistory;
import com.car.auction.procedure.entity.ProcedurePlacingAudit;
import com.car.auction.procedure.entity.ProcedureRecord;
import com.car.auction.procedure.entity.ProcedureStatistics;
import com.car.auction.procedure.entity.ProcedureWhole;
import com.car.auction.procedure.vo.ProcedureVo;
import com.car.auction.sys.dto.UserInfoDto;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureService
 * 类描述：手续入库单Service
 * 创建人：张婉欣
 * 创建时间： 2018年7月14日 下午4:05:33
 * @version
 */
@Service
public class ProcedureService {
	@Autowired
	private AuctionDao auctionDao;
	@Autowired
	private ProcedureWholeDao procedureWholeDao;
	@Autowired
	private ProcedureDao procedureDao;
	@Autowired
	private ProcedureFileDao procedureFileDao;
	@Autowired
	private ProcedureHistoryDao procedureHistoryDao;
	@Autowired
	private ProcedurePlacingAuditDao procedureAuditDao;
	@Autowired
	private ProcedureRecordDao procedureRecordDao;
	/**
	 * getProcedureList
	 * 手续查询
	 */
	public ResultDTO<ProcedureWhole> getProcedureList(HttpSession session,ProcedureDto procedure) {
		ResultDTO<ProcedureWhole> res = new ResultDTO<ProcedureWhole>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		List<ProcedureWhole> list = procedureWholeDao.getProcedureListByPage(procedure);
		res.setRows(list);
		int total = procedureWholeDao.getProcedureListTotal(procedure);
		res.setTotal(total);
		return res;
	}
	/**
	 * getAuctionProcedureById
	 * 根据拍品id查询(拍品信息，入库手续)
	 */
	public ResultDTO<EntityDto> getAuctionProcedureById(String auctionId) {
		ResultDTO<EntityDto> res = new ResultDTO<EntityDto>();
		EntityDto dto=new EntityDto();
		Auction auction = auctionDao.getAuctionById(auctionId);
		Procedure procedure = procedureDao.getProcedureByAuctionId(auctionId);
		dto.setAuction(auction);
		dto.setProcedure(procedure);
		res.setEntity(dto);
		return res;
	}
	/**
	 * getProcedureByAuctionId
	 * 手续查询
	 */
	public ResultDTO<Procedure> getProcedureByAuctionId(String auctionId) {
		ResultDTO<Procedure> res = new ResultDTO<Procedure>();
		Procedure procedure = procedureDao.getProcedureByAuctionId(auctionId);
		res.setEntity(procedure);
		return res;
	}
	/**
	 * getOutofProcedure
	 * 出库手续查询--获取已入库手续用来出库
	 */
	public ResultDTO<ProcedurePlacingDto> getOutofProcedure(String procedureId) {
		ResultDTO<ProcedurePlacingDto> res = new ResultDTO<ProcedurePlacingDto>();
		if(StringUtils.isNotBlank(procedureId)) {
			Procedure procedure = procedureDao.getProcedureById(procedureId);
			if(procedure!=null) {
				ProcedurePlacingDto dto=new ProcedurePlacingDto();
				ProcedureFile file=new ProcedureFile();
				file.setProcedureId(procedure.getId());
				file.setProcedureState(0);
				List<Integer> fileTypeList = procedureFileDao.getFileTypeByProcedureState(file);
				dto.setFileTypeList(fileTypeList);
				dto.setProcedureId(procedure.getId());
				res.setEntity(dto);			
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("没有要出库的手续");
				return res;
			}			
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * getOutofProcedureAudit
	 * 查询待审核的出库手续--用于审核
	 */
	public ResultDTO<ProcedureHistory> getOutofProcedureAudit(String procedureId) {
		ResultDTO<ProcedureHistory> res = new ResultDTO<ProcedureHistory>();
		if(procedureId!=null) {
			ProcedureHistory history=new ProcedureHistory();
			history.setProcedureId(procedureId);
			history.setAuditState(1);
			List<ProcedureHistory> historyList = procedureHistoryDao.getProcedureHistoryByCondition(history);
			if(historyList!=null&&historyList.size()>0) {
				ProcedureHistory procedureHistory = historyList.get(0);
				res.setEntity(procedureHistory);			
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("没有要审核的出库手续申请");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * addProcedure()
	 * 新增手续入库（全字段新增，为null的也会插入）
	 * is_delete 0;createtime now()
	 */
	@Transactional
	public ResultDTO<String> addProcedure(ProcedureVo vo) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(vo!=null) {
			Procedure procedure=new Procedure();
			BeanUtils.copyProperties(vo, procedure);
			procedure.setInTime(new Date());
			String addFileTypes="";
			List<String> fileTypeStrList = Arrays.asList(vo.getFileTypes().split(","));
			List<Integer> fileTypeList = fileTypeStrList.stream().map(Integer::parseInt).collect(Collectors.toList());
			List<String> fileIdStrList = Arrays.asList(vo.getFileIds().split(","));
			List<Integer> fileIdList = fileIdStrList.stream().map(Integer::parseInt).collect(Collectors.toList());
			if(StringUtils.isBlank(vo.getId())) {
				/**
				 * procedure == null首次入库
				 * fileId 为 文件 id 需  新增 文件入库
				 **/
				procedure.setId(UUIDUtil.getUuid());
				procedure.setAuditState(1);//2018年11月30日14:20:02 更改：0-1  手续免审核
				procedureDao.addProcedure(procedure);
				addFileTypes = vo.getFileTypes();
				/**filelist获取文件列表*/
				List<ProcedureFile> fileList = ProcedureUtil.getFileList(fileTypeList, fileIdList, procedure.getId(),vo.getVersion());
				/**filelist新增*/
				procedureFileDao.addProcedureFileBatch(fileList);
			}else {
				/**
				 * procedure != null再次入库
				 * fileId 为 入库文件 id
				 **/
				ProcedureFile queryParams=new ProcedureFile();
				queryParams.setProcedureId(vo.getId());
				queryParams.setProcedureState(0);
				queryParams.setVersion(vo.getVersion()-1);
				List<Integer> oldFileTypeList = procedureFileDao.getFileTypeByProcedureState(queryParams);
				
				List<Integer> addList = ProcedureUtil.getDifferent(oldFileTypeList, fileTypeList);
				addFileTypes = addList.stream().map(String::valueOf).collect(Collectors.joining(","));
				
				procedureDao.updateProcedureAll(procedure);
				
				if(fileIdList!=null) {
					List<ProcedureFile> fileList = ProcedureUtil.getFileList(fileTypeList, fileIdList, procedure.getId(),vo.getVersion());
					for (ProcedureFile file : fileList) {
						ProcedureFile fileDB = procedureFileDao.getProcedureFileByCondition(file);
						if(fileDB!=null) {
							if(fileDB.getProcedureState()==1) {//再次入库
								fileDB.setProcedureState(0);
								procedureFileDao.updateProcedureFile(fileDB);
							}
						}else {
							ProcedureFile procedureFile=new ProcedureFile(procedure.getId(), file.getFileType(), file.getAttachmentId(),vo.getVersion());
							procedureFileDao.addProcedureFile(procedureFile);
						}
					}
				}
				
			}
			//流水入库
			ProcedureHistory history=new ProcedureHistory();
			history.setId(UUIDUtil.getUuid());
			history.setProcedureId(procedure.getId());
			history.setType(0);
			history.setFileTypes(addFileTypes);
			history.setAuditState(0);
			procedureHistoryDao.addProcedureHistory(history);
			//拍品数据修改手续状态
			Auction auction =new Auction();
			auction.setId(vo.getAuctionId());
			auction.setProcedureState(1);//已提交
			auctionDao.updateAuctionState(auction);
			res.setEntity(procedure.getId());
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_DATA_NULL);
			return res;
		}
		return res;
	}
	/**
	 * outofProcedure 手续出库（全字段修改，为null的也会覆盖旧值）
	 * updateTime now()
	 */
	public ResultDTO<String> outofProcedure(ProcedurePlacingDto dto) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(dto!=null&&StringUtils.isNotBlank(dto.getProcedureId())) {
			ProcedureHistory history=new ProcedureHistory();
			BeanUtils.copyProperties(dto, history);
			//流水出库
			history.setId(UUIDUtil.getUuid());
			history.setType(1);
			history.setFileTypes(dto.getFileTypes());
			history.setAuditState(1);//待审核
			procedureHistoryDao.addProcedureHistory(history);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * outofProcedureAudit
	 * 手续出库申请审核
	 */
	public ResultDTO<String> outofProcedureAudit(ProcedurePlacingAudit audit) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(audit!=null&&StringUtils.isNotBlank(audit.getApplicationId())) {
			String historyId = audit.getApplicationId();
			ProcedureHistory history = procedureHistoryDao.getProcedureHistoryById(historyId);
			if(history!=null) {
				if(audit.getIsPass()==0) {
					List<Integer> fileIdList = history.getFileTypeList();
					//文件修改状态
					if(fileIdList!=null){
						ProcedureFile procedureFile;
						for (Integer fileId : fileIdList) {
							procedureFile = new ProcedureFile(history.getProcedureId(), fileId);
							ProcedureFile file = procedureFileDao.getProcedureFileByCondition(procedureFile);
							if(file!=null&&file.getProcedureState()==0) {
								//修改状态
								file.setProcedureState(1);
								procedureFileDao.updateProcedureFileByCondition(file);
							}
						}					
					}
					history.setAuditState(2);
					procedureHistoryDao.updateProcedureHistorySelective(history);
					//出库时间
					Procedure procedure=new Procedure();
					procedure.setId(history.getProcedureId());
					procedure.setOutTime(new Date());
					procedureDao.updateProcedureSelective(procedure);
				}else {//拒绝
					history.setAuditState(-2);
					procedureHistoryDao.updateProcedureHistorySelective(history);
				}
				//增加审核记录
				audit.setId(UUIDUtil.getUuid());
				procedureAuditDao.addProcedureAudit(audit);
			}else {
				res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
				res.setReturnMsg("没有要审核的出库数据，或者传入的applicationId有误，请检查之后再进行");
				return res;
			}
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入待审核数据的id");
			return res;
		}
		return res;
	}
	/**
	 * addProcedureRecord
	 * 手续完结归档
	 */
	public ResultDTO<String> addProcedureRecord(HttpServletRequest request,ProcedureRecord record) {
		ResultDTO<String> res = new ResultDTO<String>();
		if(record!=null&&StringUtils.isNotBlank(record.getProcedureId())) {
			ProcedureRecord dto = procedureRecordDao.getProcedureRecordByProcedureId(record.getProcedureId());
			if(dto!=null) {//更新
				record.setId(dto.getId());
				UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
				record.setApplicantId(userInfoDto==null?null:userInfoDto.getId());//操作人
				record.setIsDelete(0);
				procedureRecordDao.updateProcedureRecord(record);
			}else {//新增
				record.setId(UUIDUtil.getUuid());
				UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
				record.setApplicantId(userInfoDto==null?null:userInfoDto.getId());//操作人
				record.setIsDelete(0);
				procedureRecordDao.addProcedureRecord(record);
				//修改手续归档状态
				Procedure procedure=new Procedure();
				procedure.setId(record.getProcedureId());
				procedure.setIsRecord(1);
				procedureDao.updateProcedureSelective(procedure);
			}
			res.setEntity(record.getId());
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * updateProcedureRecord
	 * 更新手续完结归档(仅限于图片)
	 */
	public int updateRecord(ProcedureRecord record) {
		if(record!=null&&StringUtils.isNotBlank(record.getId())) {
			return procedureRecordDao.updateProcedureRecordSelective(record);
		}else {
			return -1;
		}
	}
	/**
	 * getProcedureRecord
	 * 查询手续完结归档
	 */
	public ResultDTO<ProcedureRecord> getProcedureRecord(String procedureId) {
		ResultDTO<ProcedureRecord> res = new ResultDTO<ProcedureRecord>();
		if(StringUtils.isNotBlank(procedureId)) {
			ProcedureRecord dto = procedureRecordDao.getProcedureRecordByProcedureId(procedureId);
			res.setEntity(dto);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * getProcedureHistory
	 * 出入库记录查询
	 */
	public ResultDTO<ProcedureHistory> getProcedureHistory(String procedureId,Integer page,Integer rows) {
		ResultDTO<ProcedureHistory> res = new ResultDTO<ProcedureHistory>();
		if(StringUtils.isNotBlank(procedureId)) {
			List<ProcedureHistory> list = procedureHistoryDao.getProcedureHistoryByPage(procedureId, page, rows);
			res.setRows(list);
			int count = procedureHistoryDao.getProcedureHistoryCount(procedureId);
			res.setTotal(count);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_DATA_NULL);
			res.setReturnMsg("请传入手续id（procedureId）");
			return res;
		}
		return res;
	}
	/**
	 * getProcedureStatistics
	 * 获取手续统计
	 */
	public ResultDTO<ProcedureStatistics> getProcedureStatistics(HttpSession session,ProcedureDto procedure) {
		ResultDTO<ProcedureStatistics> res = new ResultDTO<ProcedureStatistics>();
		UserInfoDto userInfo = (UserInfoDto)session.getAttribute("userInfo");
		if(null == userInfo) {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_LOGIN_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_LOGIN_NULL);
			return res;
		}
		List<ProcedureStatistics> list = procedureWholeDao.getProcedureStatisticsByPage(procedure);
		res.setRows(list);
		int total = procedureWholeDao.getProcedureStatisticsTotal(procedure);
		res.setTotal(total);
		return res;
	}
	/**
	 * addProcedureFile
	 * 保存文件到数据库
	 */
	public void addProcedureFile(List<ProcedureFile> procedureFileList) {
		for (ProcedureFile procedureFile : procedureFileList) {
			ProcedureFile file = procedureFileDao.getProcedureFileByCondition(procedureFile);
			if(file==null) {
				//新增
				procedureFile.setProcedureState(0);
				procedureFileDao.addProcedureFile(procedureFile);
			}
		}
	}
}

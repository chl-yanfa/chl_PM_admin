package com.car.auction.procedure.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.car.auction.procedure.standard.service.ProcedureUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureHistory
 * 类描述：手续出入库记录表
 * 创建人：张婉欣
 * 创建时间： 2018年8月6日 下午2:38:27
 * @version
 */
public class ProcedureHistory {
    private String id;
    private String procedureId;	//手续id
    private Integer type;		//入库0 出库1
    private String fileTypes;	//操作的文件
    private String fileNames;	//操作的文件名
    private List<Integer> fileTypeList;
    private String proposer;	//申请人
    private String reason;		//事由
    private String remark;		//备注信息
    private Integer auditState;	//审核状态 0：不需要审核；1：待审核 ；2：审核通过；-2：审核驳回
    private Integer isDelete;	//是否删除 0-未删除 1-已删除
    private Date createTime;
    private Date updateTime;
    private Date TS;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProcedureId() {
		return procedureId;
	}
	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
		if(StringUtils.isNotBlank(fileTypes)) {
			List<String> asList = Arrays.asList(fileTypes.split(","));
			this.fileTypeList = asList.stream().map(Integer::parseInt).collect(Collectors.toList());
			String fileNames2 = ProcedureUtil.getFileNames(fileTypeList);
			this.fileNames = fileNames2;
		}
	}
	public List<Integer> getFileTypeList() {
		return fileTypeList;
	}
	public void setFileTypeList(List<Integer> fileTypeList) {
		this.fileTypeList = fileTypeList;
	}
	
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public String getProposer() {
		return proposer;
	}
	public void setProposer(String proposer) {
		this.proposer = proposer;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getAuditState() {
		return auditState;
	}
	public void setAuditState(Integer auditState) {
		this.auditState = auditState;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getTS() {
		return TS;
	}
	public void setTS(Date tS) {
		TS = tS;
	}
    
    
}
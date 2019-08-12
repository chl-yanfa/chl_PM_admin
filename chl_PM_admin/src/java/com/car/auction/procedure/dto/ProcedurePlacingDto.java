package com.car.auction.procedure.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 项目名称：SDIC-Inner
 * 类名称：ProcedureHistory
 * 类描述：手续出入库记录表
 * 创建人：张婉欣
 * 创建时间： 2018年8月6日 下午2:38:27
 * @version
 */
public class ProcedurePlacingDto {
    private String procedureId;	//手续id
    private Integer type;		//入库0 出库1
    private String fileTypes;		//操作的文件id
    private List<Integer> fileTypeList;
    private String proposer;	//申请人
    private String reason;		//事由
    private String remark;		//备注信息
    
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
		}
	}
	public List<Integer> getFileTypeList() {
		return fileTypeList;
	}
	public void setFileTypeList(List<Integer> fileTypeList) {
		this.fileTypeList = fileTypeList;
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
	
    
    
}
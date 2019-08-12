package com.car.auction.procedure.entity;

import java.util.Date;

import com.car.auction.procedure.standard.service.ProcedureUtil;

public class ProcedureFile {
    private Integer id;
    private String procedureId;				//手续id
    private Integer fileType;				//文件id，0:车主身份证, 1:委托合同, 2:车辆登记证,......
    private Integer attachmentId;			//
    private Integer procedureState;			//入出状态,0已入库 1已出库
    private Integer isDelete;				//是否删除 0-未删除 1-已删除
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
    private Integer version;

    private String fileName;				//文件名
    
	public ProcedureFile() {}
	
	public ProcedureFile(String procedureId, Integer fileType) {
		this.procedureId = procedureId;
		this.fileType = fileType;
	}
	
	public ProcedureFile(String procedureId, Integer fileType, Integer attachmentId, Integer version) {
		this.procedureId = procedureId;
		this.fileType = fileType;
		this.attachmentId = attachmentId;
		this.version = version;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProcedureId() {
		return procedureId;
	}

	public void setProcedureId(String procedureId) {
		this.procedureId = procedureId;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
		String fileName2 = ProcedureUtil.getFileName(fileType);
		fileName=fileName2;
	}

	public Integer getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}

	public Integer getProcedureState() {
		return procedureState;
	}

	public void setProcedureState(Integer procedureState) {
		this.procedureState = procedureState;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
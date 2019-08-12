package com.car.auction.auction.entity;

import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AuctionAudit
 * 类描述：拍品信息审核表
 * 创建人：张婉欣
 * 创建时间： 2018年8月2日 上午9:50:56
 * @version
 */
public class AuctionAudit {
    private String id;
    private String auctionId;				//拍品id
    private Integer auditFlag;				//审核的信息flag（车辆0，手续1，入库2）
    private String auditFlagId;				//审核信息的id
    private Integer isPass;					//通过或驳回 0-通过 1-驳回
    private String noPassReason;			//驳回原因
    private Date createTime;
    private Integer isDelete;				//是否删除 0-未删除 1-已删除
    private Date TS;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	
	public Integer getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(Integer auditFlag) {
		this.auditFlag = auditFlag;
	}
	public String getAuditFlagId() {
		return auditFlagId;
	}
	public void setAuditFlagId(String auditFlagId) {
		this.auditFlagId = auditFlagId;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Date getTS() {
		return TS;
	}
	public void setTS(Date tS) {
		TS = tS;
	}
    
}
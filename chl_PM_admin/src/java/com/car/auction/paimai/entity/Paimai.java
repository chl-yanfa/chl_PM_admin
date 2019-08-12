package com.car.auction.paimai.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Paimai
 * 类描述：拍卖会表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月28日 上午10:14:53
 */
public class Paimai {
    private String id;
    private String pmhId;				//拍卖会ID
    private String paimaiName;			//拍卖会名称
    private Date paimaiStartTime;		//拍卖开始时间
    private Integer intervalTime;		//间隔时间
    private Date paimaiFinishTime;		//拍卖结束时间
    private Integer singleTime;			//延迟时间
    private String remark;				//备注
    private Integer paimaiState;		//拍卖状态：0-待发布；1-进行中；2-已结束
    private String paimaiStates;		//拍卖状态：待发布|进行中|已结束
    private Date publishTime;			//发布时间
    private Integer auctionCount=0;		//拍品数量
    private Integer dealCount=0;		//成交数量
    private Integer abortiveCount=0;	//流拍数量
    private Integer revokeCount=0;		//撤拍数量
    private BigDecimal jointPriceTotal;	//合手价
    private BigDecimal commissionTotal;	//佣金总额
    private BigDecimal otherPriceTotal;	//其他费用总额
    private BigDecimal transferDepositTotal;//过户保证金总额
    private String selAuctionCarStr;		//勾选的参拍车辆
    private String createUser;				//创建用户
    private Date createTime;
    private Integer isDelete;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPmhId() {
		return pmhId;
	}
	public void setPmhId(String pmhId) {
		this.pmhId = pmhId;
	}
	public String getPaimaiName() {
		return paimaiName;
	}
	public void setPaimaiName(String paimaiName) {
		this.paimaiName = paimaiName;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPaimaiStartTime() {
		return paimaiStartTime;
	}
	public void setPaimaiStartTime(Date paimaiStartTime) {
		this.paimaiStartTime = paimaiStartTime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPaimaiFinishTime() {
		return paimaiFinishTime;
	}
	public void setPaimaiFinishTime(Date paimaiFinishTime) {
		this.paimaiFinishTime = paimaiFinishTime;
	}
	public Integer getSingleTime() {
		return singleTime;
	}
	public void setSingleTime(Integer singleTime) {
		this.singleTime = singleTime;
	}
	public Integer getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(Integer intervalTime) {
		this.intervalTime = intervalTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getPaimaiState() {
		return paimaiState;
	}
	public void setPaimaiState(Integer paimaiState) {
		this.paimaiState = paimaiState;
	}
	public String getPaimaiStates() {
		return paimaiStates;
	}
	public void setPaimaiStates(String paimaiStates) {
		this.paimaiStates = paimaiStates;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	public Integer getAuctionCount() {
		return auctionCount;
	}
	public void setAuctionCount(Integer auctionCount) {
		this.auctionCount = auctionCount;
	}
	public Integer getDealCount() {
		return dealCount;
	}
	public void setDealCount(Integer dealCount) {
		this.dealCount = dealCount;
	}
	public Integer getAbortiveCount() {
		return abortiveCount;
	}
	public void setAbortiveCount(Integer abortiveCount) {
		this.abortiveCount = abortiveCount;
	}
	public Integer getRevokeCount() {
		return revokeCount;
	}
	public void setRevokeCount(Integer revokeCount) {
		this.revokeCount = revokeCount;
	}
	public BigDecimal getJointPriceTotal() {
		return jointPriceTotal;
	}
	public void setJointPriceTotal(BigDecimal jointPriceTotal) {
		this.jointPriceTotal = jointPriceTotal;
	}
	public BigDecimal getCommissionTotal() {
		return commissionTotal;
	}
	public void setCommissionTotal(BigDecimal commissionTotal) {
		this.commissionTotal = commissionTotal;
	}
	public BigDecimal getOtherPriceTotal() {
		return otherPriceTotal;
	}
	public void setOtherPriceTotal(BigDecimal otherPriceTotal) {
		this.otherPriceTotal = otherPriceTotal;
	}
	public BigDecimal getTransferDepositTotal() {
		return transferDepositTotal;
	}
	public void setTransferDepositTotal(BigDecimal transferDepositTotal) {
		this.transferDepositTotal = transferDepositTotal;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
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
	public String getSelAuctionCarStr() {
		return selAuctionCarStr;
	}
	public void setSelAuctionCarStr(String selAuctionCarStr) {
		this.selAuctionCarStr = selAuctionCarStr;
	}

}
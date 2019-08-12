package com.car.auction.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目名称：	SDIC-Inner
 * 类名称：	CustomerMargin
 * 类描述：	用户保证金表
 * 创建人：	zhangwanxin
 * 创建时间： 	2018年12月12日 上午11:56:22
 */
public class CustomerMargin {
    private String id;
    private BigDecimal walletPledge;
    private BigDecimal walletPledgeFreeze;
    private Integer isDelete;
    private Date createTime;
    private String createUser;
    private Date updateTime;
    private String updateUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getWalletPledge() {
		return walletPledge;
	}
	public void setWalletPledge(BigDecimal walletPledge) {
		this.walletPledge = walletPledge;
	}
	public BigDecimal getWalletPledgeFreeze() {
		return walletPledgeFreeze;
	}
	public void setWalletPledgeFreeze(BigDecimal walletPledgeFreeze) {
		this.walletPledgeFreeze = walletPledgeFreeze;
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
    
}
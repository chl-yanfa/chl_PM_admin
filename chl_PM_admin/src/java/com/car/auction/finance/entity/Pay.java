package com.car.auction.finance.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 项目名称：SDIC-Inner
 * 类名称：Pay
 * 类描述：付款表
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月18日 下午6:53:35
 */
public class Pay {
    private String id;
    private String auctionId;	
    private String auctionSetId;
    private String type;					//操作类型 : PAY-付款 REF-退款
    private String mid;
    private String payHandle;				//付款人
    private String payBank;
    private String payOpeningBank;
    private String payAccount;
    private BigDecimal payMoney;
    private String payContactPhone;
    private BigDecimal highestPrice;		//车款
    private BigDecimal commission;			//佣金
    private BigDecimal otherPrice;
    private Integer payVoucherId;			//付款凭证
    private String payVoucherPath;
    private String remark;					//备注
    private String createUser;
    private Date createTime;
    private Integer isDelete;
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
	public String getAuctionSetId() {
		return auctionSetId;
	}
	public void setAuctionSetId(String auctionSetId) {
		this.auctionSetId = auctionSetId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getPayHandle() {
		return payHandle;
	}
	public void setPayHandle(String payHandle) {
		this.payHandle = payHandle;
	}
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}
	public String getPayOpeningBank() {
		return payOpeningBank;
	}
	public void setPayOpeningBank(String payOpeningBank) {
		this.payOpeningBank = payOpeningBank;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public String getPayContactPhone() {
		return payContactPhone;
	}
	public void setPayContactPhone(String payContactPhone) {
		this.payContactPhone = payContactPhone;
	}
	public BigDecimal getHighestPrice() {
		return highestPrice;
	}
	public void setHighestPrice(BigDecimal highestPrice) {
		this.highestPrice = highestPrice;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getOtherPrice() {
		return otherPrice;
	}
	public void setOtherPrice(BigDecimal otherPrice) {
		this.otherPrice = otherPrice;
	}
	public Integer getPayVoucherId() {
		return payVoucherId;
	}
	public void setPayVoucherId(Integer payVoucherId) {
		this.payVoucherId = payVoucherId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getPayVoucherPath() {
		return payVoucherPath;
	}
	public void setPayVoucherPath(String payVoucherPath) {
		this.payVoucherPath = payVoucherPath;
	}
    
}
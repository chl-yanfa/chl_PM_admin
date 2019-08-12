package com.car.auction.customer.dto;

import java.math.BigDecimal;

import com.car.auction.customer.entity.Customer;

/**
 * 类名称：CustomerDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-04-18 10:49
 */
public class CustomerDto extends Customer{
	private BigDecimal walletPledge;
    private BigDecimal walletPledgeFreeze;
    private String statusStr;
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
	public String getStatusStr() {
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
    
}

package com.car.auction.customer.dto;

import com.car.auction.customer.entity.CustomerMarginWater;

public class CustomerMarginWaterDto extends CustomerMarginWater {
	private String userName;			
	private String loginName;
    private String payVoucherPath;		//存放收款凭证
    private String operatorTypeS;		//
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPayVoucherPath() {
		return payVoucherPath;
	}
	public void setPayVoucherPath(String payVoucherPath) {
		this.payVoucherPath = payVoucherPath;
	}
	public String getOperatorTypeS() {
		return operatorTypeS;
	}
	public void setOperatorTypeS(String operatorTypeS) {
		this.operatorTypeS = operatorTypeS;
	}
    
}
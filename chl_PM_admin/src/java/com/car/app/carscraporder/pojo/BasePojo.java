package com.car.app.carscraporder.pojo;

import java.util.Date;

public abstract class BasePojo implements java.io.Serializable {
    
	private static final long serialVersionUID = 1L;
	
    private Date createtime;
    private Date operatortime;
	private String creater;
    private String operator;
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getOperatortime() {
		return operatortime;
	}
	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	

}

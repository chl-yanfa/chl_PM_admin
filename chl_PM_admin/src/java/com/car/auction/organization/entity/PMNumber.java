package com.car.auction.organization.entity;

import java.util.Date;

/**
 * 类名称：PMNumber
 * 类描述：拍卖项目编号生成
 * 创建人：zhangwanxin
 * 创建时间：2019-03-25 17:25
 */
public class PMNumber {
    private Integer id;
    private Integer type;
    private Integer number;
    private String memo;
    private Date createTime;
    private Date updateTime;
    private Date ts;

    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
    
}
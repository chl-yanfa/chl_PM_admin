/**
 * 
 */
package com.car.auction.customer.vo;

/**
 * 类名称：CustomerVo
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-04-17 14:18
 */

public class CustomerVo {
	private String id;
	private String userName;				//用户名
	private String phone;					//手机号
	private String password;
	private String password2;
	private String keywords;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}

package com.car.auction.common;

import java.util.ResourceBundle;

public class AddressBase {
	
	public static ResourceBundle APPLICATION = ResourceBundle.getBundle("application");

	/*Domain地址*/
	public final static String getDomainAddress() {
		return APPLICATION.getString("domainAddress");
	}
}

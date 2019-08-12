package com.car.auction.sys.threadlocal;

import org.slf4j.MDC;

import com.car.auction.sys.dto.UserInfoDto;


public class UserUtil {

	private final static ThreadLocal<UserInfoDto> tlUser = new ThreadLocal<UserInfoDto>();

	public static final String KEY_USER = "user";

	public static void setUser(UserInfoDto user) {
		tlUser.set(user);
		// 把用户信息放到log4j
		MDC.put(KEY_USER, user.getId());
	}

	/**
	 * 如果没有登录会抛出异常
	 * 
	 * @return
	 * @throws DataException 
	 */
	public static UserInfoDto getUser() throws RuntimeException {
		UserInfoDto user = tlUser.get();

		if (user == null) {
			throw new RuntimeException("未登录系统");
		}

		return user;
	}
	

	public static void clearAllUserInfo() {
		tlUser.remove();
		MDC.remove(KEY_USER);
	}
}

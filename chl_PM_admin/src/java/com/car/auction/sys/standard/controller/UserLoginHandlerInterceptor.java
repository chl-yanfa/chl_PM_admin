package com.car.auction.sys.standard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.car.auction.common.Constants;
import com.car.auction.common.CookieUtils;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.sys.standard.service.UserManagementService;
import com.car.auction.sys.threadlocal.UserUtil;

public class UserLoginHandlerInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserManagementService userManagementService;

	Logger logger = LoggerFactory.getLogger(UserLoginHandlerInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		//clear 由于tomcat线程重用，记得清空
		clearAllUserInfo();

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 如何检查用户是否登录
		String ticket = CookieUtils.getCookieValue(request, Constants.USER_COOKIEKEY);
		if (StringUtils.isBlank(ticket)) {
			response.sendRedirect(request.getContextPath() + "/logout.html");
			return false;
		}
		UserInfoDto user = this.userManagementService.queryUserByCookieVal(ticket);
		if (user == null) {
			// 登录超时
			response.sendRedirect(request.getContextPath() + "/logout.html");
			return false;
		}
		// 将user保存到ThreadLocal中
		UserUtil.setUser(user);
		return true;
	}

	private void clearAllUserInfo() {
		UserUtil.clearAllUserInfo();
	}

}

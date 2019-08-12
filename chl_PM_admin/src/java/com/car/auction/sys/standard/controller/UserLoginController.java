package com.car.auction.sys.standard.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.car.auction.common.Base64;
import com.car.auction.common.Constants;
import com.car.auction.common.CookieUtils;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.sys.standard.service.UserLoginService;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：UserLoginController
 * 类描述：用户登录控制器
 * 创建人：刘民
 * 创建时间： 2016年2月18日 下午1:20:59
 * 修改人：刘民
 * 修改时间： 2016年2月18日 下午1:20:59
 * 修改备注： 
 * @version 
 *
 */
@Controller
@RequestMapping(value = "/userLogin")
public class UserLoginController {

	@Autowired
	private UserLoginService userLoginService;
	
	/**
	 * 
	 * logout(系统退出,清空缓存)
	 * @param  request
	 * @param  response    设定文件
	 * @return void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
	@ResponseStatus(HttpStatus.OK)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		Cookie[] cookies = request.getCookies();
		String cookieName = null;
		
		if (null != cookies && cookies.length > 0) {
			for (Cookie c : cookies) {
				cookieName = c.getName();
				if (StringUtils.isNotBlank(cookieName) && cookieName.indexOf("remember") == -1) {
					Cookie newCookie = new Cookie(cookieName, null);
					newCookie.setPath("/");
					newCookie.setMaxAge(0);
				    response.addCookie(newCookie);
				}
			}
		}
	}
	
	/**
	 * login(用户登录验证)
	 * @param  @param loginId
	 * @param  @param password
	 * @param  @param request
	 * @param  @param response
	 * @param  @return
	 * @param  @throws UnsupportedEncodingException    设定文件
	 * @return String
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam String loginId, @RequestParam String password, HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		
		UserInfoDto userInfoDto = userLoginService.validateUser(loginId, password);
		if (userInfoDto == null) {
			return RtnMsgConstants.RETURN_CODE_LOGIN_NULL;
		} else if (Constants.STATUS_DELETE.equals(userInfoDto.getStatus())) {
			return RtnMsgConstants.RETURN_CODE_LOGIN_NULL;
		}else if (Constants.STATUS_FORBIDDEN.equals(userInfoDto.getStatus())) {
			// 用户还未启用
			return RtnMsgConstants.RETURN_CODE_LOGIN_ENABLE;
		} else {
			//查询该用户是否为超级管理员
			boolean isSuperRole = userLoginService.getSuperRoleByUserId(userInfoDto.getId());
			if (isSuperRole) {
				userInfoDto.setAdmin(true);
			}
			request.getSession().setAttribute("userInfo", userInfoDto);
			//保存信息到cookie中
			Map<String, String> cookies = new HashMap<String, String>();
			cookies.put("userCode", userInfoDto.getId().toString());
			cookies.put("loginID", loginId);
			cookies.put("userName", Base64.encodeToString(userInfoDto.getRealName()));
			CookieUtils.addCookie(cookies, "/", response);
			return RtnMsgConstants.RETURN_CODE_SUCCESS;
		}
	}
}

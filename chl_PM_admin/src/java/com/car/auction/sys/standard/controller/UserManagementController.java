package com.car.auction.sys.standard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.sys.standard.service.UserManagementService;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：UserManagementController
 * 类描述：用户管理控制器
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午2:18:55
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午2:18:55
 * 修改备注： 
 * @version 
 *
 */
@Controller
@RequestMapping(value = "/userManagement")
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;
	
	/**
	 * TODO
	 * updatePwd(修改登录密码)
	 * @param  @param session
	 * @param  @param sysUserInfo    设定文件
	 * @return void
	 * @Exception 异常对象
	 */
	@RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void updatePwd(HttpServletRequest request,UserInfoDto userInfo) {
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		String newPassword = request.getParameter("userPassword");
		String userId = userInfoDto.getId();
		userManagementService.updateSysUserPwdInfo(userId,newPassword);
	}

}
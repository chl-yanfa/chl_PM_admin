package com.car.auction.organization.standard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.ResultDTO;
import com.car.auction.common.RtnMsgConstants;
import com.car.auction.organization.entity.User;
import com.car.auction.organization.standard.service.UserService;
import com.car.auction.sys.dto.UserInfoDto;


@Controller
@RequestMapping(value = "/user")
public class UserController{
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/getUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResultDTO<User> getUser(HttpServletRequest request){
		ResultDTO<User> res = new ResultDTO<User>();
		UserInfoDto userInfoDto = (UserInfoDto) request.getSession().getAttribute("userInfo");
		if(userInfoDto!=null) {
			User user = userService.getUser(userInfoDto.getId());
			res.setEntity(user);
		}else {
			res.setReturnCode(RtnMsgConstants.RETURN_CODE_SESSION_NULL);
			res.setReturnMsg(RtnMsgConstants.RETURN_MSG_SESSION_NULL);
		}
		return res; 			
	}
	/**
	 * getAllUser(获取业务员)
	 * @return
	 */
	@RequestMapping(value = "/getAllUser" , method = RequestMethod.GET)
	@ResponseBody
	public List<UserInfoDto> getAllUser(){
		return userService.getAllUser();
	}
}

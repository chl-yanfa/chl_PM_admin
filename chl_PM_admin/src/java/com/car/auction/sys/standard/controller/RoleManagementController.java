package com.car.auction.sys.standard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.car.auction.common.Constants;
import com.car.auction.sys.dto.SysMenuInfoDto;
import com.car.auction.sys.dto.UserInfoDto;
import com.car.auction.sys.standard.service.RoleManageService;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：RoleManagementController
 * 类描述：岗位管理控制器
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午1:43:42
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午1:43:42
 * 修改备注： 
 * @version 
 *
 */
@Controller
@RequestMapping(value = "/roleManagement")
public class RoleManagementController {

	@Autowired
	private RoleManageService roleManageService;
	
	/**
	 * 
	 * getUserMenu(返回用户菜单)
	 * @param  @param session
	 * @param  @return    设定文件
	 * @return Map<String,SysMenuInfoDto>
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	@RequestMapping(value="/getUserMenu",method=RequestMethod.GET)  
	@ResponseBody
	public Map<String, SysMenuInfoDto> getUserMenu(HttpSession session){
		UserInfoDto userInfoDto = (UserInfoDto) session.getAttribute("userInfo");
		if (null == userInfoDto) {
			return null;
		}
		String id = userInfoDto.getId();
		if (StringUtils.isEmpty(id) || StringUtils.isEmpty(id.trim())) {
			return null;
		}
		SysMenuInfoDto sysMenuInfoDto = new SysMenuInfoDto();
		if (!userInfoDto.isAdmin()) {
			sysMenuInfoDto.setUserId(id);
		}
		sysMenuInfoDto.setRoleId(userInfoDto.getRoleIds());
		sysMenuInfoDto.setMenuType(Constants.MenuType.MENU);
		sysMenuInfoDto.setIsNotMenuLevel(Constants.SYS_MENU_LEVEL_ONE);
		sysMenuInfoDto.setIsEnable(Constants.ENABLE_Y);
		Map<String, SysMenuInfoDto> result = roleManageService.selectUserMenu(sysMenuInfoDto);
		return result;
	}
}

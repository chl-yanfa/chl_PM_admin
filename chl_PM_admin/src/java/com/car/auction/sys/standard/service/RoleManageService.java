package com.car.auction.sys.standard.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.common.Constants;
import com.car.auction.sys.dao.SysMenuInfoDao;
import com.car.auction.sys.dto.SysMenuInfoDto;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：RoleManageService
 * 类描述：岗位管理服务层
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午1:47:11
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午1:47:11
 * 修改备注： 
 * @version 
 *
 */
@Service
public class RoleManageService {

	@Autowired
	private SysMenuInfoDao sysMenuInfoDao;
	
	
	/**
	 * 
	 * selectUserMenu(查询用户权限下的菜单)
	 * @param  @param sysMenuInfoDto
	 * @param  @return    设定文件
	 * @return Map<String,SysMenuInfoDto>
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Map<String, SysMenuInfoDto> selectUserMenu(SysMenuInfoDto sysMenuInfoDto) {
		Map<String, SysMenuInfoDto> result=new LinkedHashMap<String, SysMenuInfoDto>();
		Map<String, SysMenuInfoDto> tempList = new LinkedHashMap<String, SysMenuInfoDto>();
		
		//一级菜单(全部)
		SysMenuInfoDto param = new SysMenuInfoDto();
		param.setRoleId(sysMenuInfoDto.getRoleId());
		param.setIsEnable(Constants.ENABLE_Y);
		param.setMenuLevel(Constants.SYS_MENU_LEVEL_ONE);
 		List<SysMenuInfoDto> allParentsMenuList = sysMenuInfoDao.selectSysMenuInfo(param);
		//用户菜单
		List<SysMenuInfoDto> userMenuList = sysMenuInfoDao.selectSysMenuInfo(sysMenuInfoDto);
		
		for (SysMenuInfoDto menu : userMenuList) {
			if (StringUtils.isNotEmpty(menu.getMenuUrl()) && menu.getMenuUrl().startsWith("http:")) {
				menu.setMenuUrl(menu.getMenuUrl() + "?userCode=" + menu.getUserId() + "&random=" + Math.random());
			}
		}
		
		userMenuList.addAll(allParentsMenuList);
		
		for (SysMenuInfoDto menu : userMenuList) {
			if (StringUtils.isEmpty(sysMenuInfoDto.getUserId())) {
				menu.setMenuChecked(Constants.ENABLE_Y);
			}
			tempList.put(menu.getId(), menu);
		}
		
		for (SysMenuInfoDto menu : userMenuList) {
			if (tempList.containsKey(menu.getParentId())) {
				tempList.get(menu.getParentId()).getChildren().add(menu);
			}
		}
		
		for (SysMenuInfoDto menu : userMenuList) {
			if (Constants.SYS_MENU_LEVEL_ONE.equals(menu.getMenuLevel())) {
				result.put(menu.getId(),tempList.get(menu.getId()));
			}
		}
		
		return result;
	}
	
}

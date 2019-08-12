package com.car.auction.sys.standard.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.auction.common.CommonUtils;
import com.car.auction.common.Constants;
import com.car.auction.sys.dao.SysUserInfoDao;
import com.car.auction.sys.dto.UserInfoDto;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：UserLoginService
 * 类描述：用户管理服务层
 * 创建人：刘民
 * 创建时间： 2016年2月18日 下午2:14:17
 * 修改人：刘民
 * 修改时间： 2016年2月18日 下午2:14:17
 * 修改备注： 
 * @version 
 *
 */
@Service
public class UserLoginService {

	@Autowired
	private SysUserInfoDao sysUserInfoDao;
	
	/**
	 * 
	 * validateUser(验证用户信息)
	 * @param  @param loginId
	 * @param  @param loginPassword
	 * @param  @return    设定文件
	 * @return UserInfoDto
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserInfoDto validateUser(String loginId, String password) {
		
		UserInfoDto param = new UserInfoDto(loginId);
		UserInfoDto userInfoDto = sysUserInfoDao.selectOneUserInfoDto(param);
		if (userInfoDto == null) {
			return null;
		}
		String validpassword = CommonUtils.createPassword(password, userInfoDto.getPasswordSalt(), Constants.HASHITERATIONS);
		String oldPassword = userInfoDto.getPassword();
		if(StringUtils.equals(validpassword, oldPassword)) {
			return userInfoDto;			
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * getSuperRoleByUserId(判断用户是否为超级管理员)
	 * @param  @param userId
	 * @param  @return    设定文件
	 * @return boolean
	 * @Exception 异常对象
	 */
	public boolean getSuperRoleByUserId(String userId) {
		int i = sysUserInfoDao.getSuperRoleByUserId(userId);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
}

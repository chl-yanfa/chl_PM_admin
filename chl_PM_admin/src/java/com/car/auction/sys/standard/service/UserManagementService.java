package com.car.auction.sys.standard.service;

import java.util.Date;
import java.util.UUID;

import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.app.carscraporder.bo.UserBO;
import com.car.auction.common.CommonUtils;
import com.car.auction.common.Constants;
import com.car.auction.redis.standard.service.RedisService;
import com.car.auction.sys.dao.SysUserInfoDao;
import com.car.auction.sys.dto.UserInfoDto;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：UserManagementService
 * 类描述：用户管理服务层
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午2:21:05
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午2:21:05
 * 修改备注： 
 * @version 
 *
 */
@Service
public class UserManagementService {

	@Autowired
	private SysUserInfoDao sysUserInfoDao;
	@Autowired
	private RedisService redisService;
	
	public UserInfoDto queryUserByCookieVal(String cookieVal) {
		byte[] byteKey = CommonUtils.getByteKey(cookieVal);
    	SimpleSession session = null;
		try {
			session = (SimpleSession)redisService.getObject(byteKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(session == null) {
			return null;
		}
		SimplePrincipalCollection principalCollection = (SimplePrincipalCollection)session.getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY");
		if(principalCollection == null) {
			return null;
		}
		UserBO userBO = (UserBO)principalCollection.getPrimaryPrincipal();
		if(userBO == null) {
			return null;
		}
		return this.getUserById(userBO.getId());
	}
	
	public UserInfoDto getUserById(String id) {
		UserInfoDto param = new UserInfoDto();
		param.setId(id);
		return  sysUserInfoDao.selectOneUserInfoDto(param);
	}
	
	/**
	 * updateSysUserPwdInfo(修改密码)
	 * @param  @param userId
	 * @param  @param newPassword    设定文件
	 * @return void
	 * @Exception 异常对象
	 */
	public void updateSysUserPwdInfo(String userId, String newPassword) {
		String salt = UUID.randomUUID().toString().replaceAll("-", "");
		String createPassword = CommonUtils.createPassword(newPassword, salt, Constants.HASHITERATIONS);
		UserInfoDto userInfoDto = new UserInfoDto();
		userInfoDto.setId(userId);
		userInfoDto.setPassword(createPassword);
		userInfoDto.setPasswordSalt(salt);
		userInfoDto.setOperator(userId);
		userInfoDto.setOperatortime(new Date());
		sysUserInfoDao.updateSysUserInfo(userInfoDto);
		
	}
}
package com.car.auction.sys.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.sys.dto.UserInfoDto;

/**
 * 
 * 项目名称：SDIC-Inner
 * 类名称：SysUserInfoDao
 * 类描述：用户管理数据库实现类
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午2:35:06
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午2:35:06
 * 修改备注： 
 * @version 
 *
 */
@Component
public class SysUserInfoDao {

	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 
	 * selectOneUserInfoDto(返回一个用户信息)
	 * @param  @param userInfoDto
	 * @param  @return    设定文件
	 * @return UserInfoDto
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserInfoDto selectOneUserInfoDto(UserInfoDto userInfoDto) {
		return baseDao.findOne("mapper.standard.SysUserInfoMapper.selectUserInfoDto", userInfoDto);
	}
	
	/**
	 * 
	 * updateSysUserInfo(更新用户信息)
	 * @param  @param userInfoDto
	 * @param  @return    设定文件
	 * @return int
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public int updateSysUserInfo(UserInfoDto userInfoDto) {
		return baseDao.update("mapper.standard.SysUserInfoMapper.updateSysUserInfo", userInfoDto);
	}
	
	/**
	 * 
	 * getSuperRoleByUserId(查询用户是否为超级管理员岗)------v
	 * @param  @param userId
	 * @param  @return    设定文件
	 * @return int
	 * @Exception 异常对象
	 */
	public int getSuperRoleByUserId(String userId) {
		return baseDao.findOne("mapper.standard.SysUserInfoMapper.getSuperRoleByUserId" ,userId);
	}

}
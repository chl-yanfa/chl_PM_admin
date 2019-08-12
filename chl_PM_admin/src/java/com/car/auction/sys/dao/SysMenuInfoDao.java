package com.car.auction.sys.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.sys.dto.SysMenuInfoDto;

/**
 * 
 * 
 * 项目名称：SDIC-Inner
 * 类名称：SysMenuInfoDao
 * 类描述：菜单表相关操作
 * 创建人：刘民
 * 创建时间： 2016年2月15日 下午1:38:12
 * 修改人：刘民
 * 修改时间： 2016年2月15日 下午1:38:12
 * 修改备注： 
 * @version 
 *
 */
@Component
public class SysMenuInfoDao {

	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 
	 * selectSysMenuInfo(查询菜单列表)
	 * @param  @param sysMenuInfoDto
	 * @param  @return    设定文件
	 * @return List<SysMenuInfoDto>
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<SysMenuInfoDto> selectSysMenuInfo(SysMenuInfoDto sysMenuInfoDto) {
		return baseDao.findList("mapper.standard.SysMenuInfoMapper.selectSysMenuInfo", sysMenuInfoDto);
	}
	
}

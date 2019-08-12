package com.car.auction.warehouse.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.car.auction.common.BaseDao;
import com.car.auction.warehouse.dto.WarehouseFileDto;
import com.car.auction.warehouse.entity.WarehouseFile;


@Component
public class WarehouseFileDao {
	
	@Autowired 
	private BaseDao baseDao;
	
	/**
	 * 根据id查询车辆入库图片信息
	 */
	public WarehouseFileDto getWarehouseFileById(Integer id) {
		return baseDao.findOne("mapper.standard.WarehouseFileMapper.getWarehouseFileById", id);
	}
	/**
	 * 车辆入库图片信息
	 */
	public int addWarehouseFile(WarehouseFile warehouseFile) {
		return baseDao.create("mapper.standard.WarehouseFileMapper.addWarehouseFile", warehouseFile);
	}
	/**
	 * 车辆入库图片信息
	 */
	public int addWarehouseFileBatch(List<WarehouseFile> list) {
		if(list!=null&&list.size()>0) {
			return baseDao.create("mapper.standard.WarehouseFileMapper.addWarehouseFileBatch", list);
		}
		return 0;
	}
	public int updateWarehouseFile(WarehouseFile warehouseFile) {
		return baseDao.update("mapper.standard.WarehouseFileMapper.updateWarehouseFile", warehouseFile);
	}

}

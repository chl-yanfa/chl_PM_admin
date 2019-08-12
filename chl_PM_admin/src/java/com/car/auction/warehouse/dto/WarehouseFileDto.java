/**
 * 
 */
package com.car.auction.warehouse.dto;

import com.car.auction.warehouse.entity.WarehouseFile;

/**
 * 类名称：WarehouseFileDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-03-28 17:48
 */

public class WarehouseFileDto extends WarehouseFile{
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	
}

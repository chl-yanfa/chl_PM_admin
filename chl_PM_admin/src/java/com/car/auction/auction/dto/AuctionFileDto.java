/**
 * 
 */
package com.car.auction.auction.dto;

import com.car.auction.auction.entity.AuctionFile;

/**
 * 类名称：AuctionFileDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-03-25 18:01
 */

public class AuctionFileDto extends AuctionFile{
	private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
}

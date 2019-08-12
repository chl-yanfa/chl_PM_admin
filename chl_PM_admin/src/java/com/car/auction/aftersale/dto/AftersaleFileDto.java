package com.car.auction.aftersale.dto;

import com.car.auction.aftersale.entity.AftersaleFile;

/**
 * 项目名称：SDIC-Inner
 * 类名称：AftersaleFile
 * 类描述：售后管理图片库
 * 创建人：zhangwanxin
 * 创建时间： 2018年9月14日 下午2:36:08
 */
public class AftersaleFileDto extends AftersaleFile {
    private String imgPath;

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
    
}
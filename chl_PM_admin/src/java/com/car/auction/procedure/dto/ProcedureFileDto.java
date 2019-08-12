/**
 * 
 */
package com.car.auction.procedure.dto;

import com.car.auction.procedure.entity.ProcedureFile;

/**
 * 类名称：ProcedureFileDto
 * 类描述：
 * 创建人：zhangwanxin
 * 创建时间：2019-03-26 15:04
 */

public class ProcedureFileDto extends ProcedureFile{
	private String imgName;				//文件名
    private String imgPath;				//文件路径
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
    
}

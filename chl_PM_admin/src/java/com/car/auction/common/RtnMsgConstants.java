package com.car.auction.common;

public class RtnMsgConstants {
	
	/** 成功*/
	public final static String RETURN_CODE_SUCCESS = "000000";
	public final static String RETURN_MSG_SUCCESS = "成功";
	/** 参数为空(输入参数数据)*/
	public final static String RETURN_CODE_PARAMETER_NULL = "100000";
	public final static String RETURN_MSG_PARAMETER_NULL = "参数为空";
	/** 参数有错(输入参数数据)*/
	public final static String RETURN_CODE_PARAMETER_ERROR = "100001";
	public final static String RETURN_MSG_PARAMETER_ERROR = "参数有错";
	/** 车辆状态状态不符合*/
    public final static String RETURN_CODE_PARAMETER_INDENT2 = "100007";
	public final static String RETURN_MSG_PARAMETER_INDENT2 = "车辆状态状态不符合";
	/** 无可成交商户*/
	public final static String RETURN_CODE_PARAMETER_NULL1 = "100008";
    public final static String RETURN_MSG_PARAMETER_NULL1 = "无可成交商户！";
    /** 密码不一致*/
	public final static String RETURN_CODE_PWD_NOT_SAME = "100009";
    public final static String RETURN_MSG_PWD_NOT_SAME = "密码不一致";
    /** 数据为空*/
	public final static String RETURN_CODE_DATA_NULL = "200001";
	public final static String RETURN_MSG_DATA_NULL = "数据为空";
	/** 数据已经存在*/
	public final static String RETURN_CODE_DATA_EXIST = "300000";
	public final static String RETURN_MSG_DATA_EXIST = "数据已经存在.";
	public final static String RETURN_MSG_DATA_EXIST_PLATFORM = "工具名称重复.";
	/** 数据推送异常*/
	public final static String RETURN_CODE_DATA_ERROR4 = "300004"; 
	public final static String RETURN_MSG_DATA_ERROR4 = "数据推送异常：";
	public final static String RETURN_MSG_DATA_ERROR41 = "PC数据推送失败";
	/** 系统报错*/
	public final static String RETURN_CODE_SYSTEM_FAIL = "400000";
	public final static String RETURN_MSG_SYSTEM_FAIL = "系统报错";
	/** 连接超时*/
	public final static String RETURN_CODE_SESSION_NULL = "400001";
	public final static String RETURN_MSG_SESSION_NULL = "连接超时";
	/** Excel数据错误*/
	public final static String RETURN_CODE_EXCEL_FAIL = "500000";
	public final static String RETURN_MSG_EXCEL_FAIL = "EXCEL数据错误";
	/** 上传文件相关*/
	public final static String RETURN_CODE_UPLOAD_FAIL = "600000";
	public final static String RETURN_MSG_UPLOAD_FAIL = "上传失败";
	/** 上传文件相关-文件过大*/
	public final static String RETURN_CODE_UPLOAD_OVER = "600001";
	public final static String RETURN_MSG_UPLOAD_OVER = "文件过大";
	
	/**
	 * 登录模块
	 */
	/** 验证码不正确 */
	public final static String RETURN_CODE_LOGIN_KAPTCHA = "L00000";
	/** 未登录*/
	public final static String RETURN_CODE_LOGIN_NULL = "L00001";
    public final static String RETURN_MSG_LOGIN_NULL = "未检测到登录信息";
	/** 用户还未启用*/
	public final static String RETURN_CODE_LOGIN_ENABLE = "L00002";
	/** 没有后台平台权限 */
	public final static String RETURN_CODE_LOGIN_PLATFORM = "L00003";

}

package com.car.auction.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * Company:SDIC_Inner 
 * Description：加密算法
 * @author yh
 * @date 2014年5月4日
 * @use 主要用于加密
 **/
public class MessageEncrypt {
	/**
	*功能描述：字符串加密，默认为MD5
	*输入参数： strSrc：要加密的字符串，encName：加密算法名（MD5，SHA-1，SHA-256）
	*返 回 值：String
	*创 建 人： yh
	*日    期：2014年5月4日
	*修 改 人： 
	*日    期：
	*修 改描述：
	**/
	public static String Encrypt(String strSrc, String encName) {
		// 得到MessageDigest对象
		MessageDigest md = null;
		MessageEncrypt mept = new MessageEncrypt();
		// 加密后的字符串
		String strDes = null;
		// 要加密的字符串字节型数组
		byte[] bt = strSrc.getBytes();
		try {
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			// 通过执行诸如填充之类的最终操作完成哈希计算
			strDes = mept.bytes2Hex(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			strDes = null;
			throw new RuntimeException(e);
		}finally{
			
		}
		return strDes;
	}

	/**
	*功能描述：将字节数组转换成16进制的字符串
	*输入参数： 
	*返 回 值：String
	*创 建 人： yh
	*日    期：2014年5月4日
	*修 改 人： 
	*日    期：
	*修 改描述：
	**/
	private String bytes2Hex(byte[] bts) {
		String des = "";
		String tmp = null;

		for (int i = 0; i < bts.length; i++) {
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) {
				des += "0";
			}
			des += tmp;
		}
		return des;
	}

	/**
	*功能描述：MD5加密
	*输入参数： 要加密的字符串
	*返 回 值：String
	*创 建 人： yh
	*日    期：2014年5月4日
	*修 改 人： 
	*日    期：
	*修 改描述：
	**/
	public static String toMd5(String strSrc) {
		return Encrypt(strSrc, "MD5");
	}
	/**
	  * BASE64加密
	  * 
	  * @param key = 需要加密的字符数组
	  * @return
	  * @throws Exception
	  */
	 public static String encryptBASE64(String key){
		 return (new BASE64Encoder()).encodeBuffer(key.getBytes()).trim();
	 }
	 /***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}
}

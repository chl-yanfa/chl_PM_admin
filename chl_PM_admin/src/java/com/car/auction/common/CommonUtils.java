package com.car.auction.common;

import org.apache.shiro.crypto.hash.Md5Hash;

public class CommonUtils {
	/**
	 * createPassword()	生成密码
     * @param password 	密码
     * @param salt     	密码盐
     * @return
     */
    public static String createPassword(String password, String salt, int hashIterations) {
        Md5Hash md5Hash = new Md5Hash(password.trim(), salt, hashIterations);
        return md5Hash.toString();
    }
    private static String keyPrefix = "shiro_redis_session:";
    /**
	 * 获得byte[]型的key
	 * @param key
	 * @return
	 */
    public static byte[] getByteKey(String sessionId){
		String preKey = keyPrefix + sessionId;
		return preKey.getBytes();
	}


}

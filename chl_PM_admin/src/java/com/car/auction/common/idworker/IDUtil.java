package com.car.auction.common.idworker;

/**
 * id 生成器，读取最大的值，有个缓冲
 * 
 **/
public class IDUtil {
	private final static IdWorker idWorker = new IdWorker();

	private IDUtil() {};

	public static Long getId() {
		return idWorker.nextId();
	}
	public static void main(String[] args) {
		System.out.println(IDUtil.getId());
	}

}

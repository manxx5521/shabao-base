package com.xiaoshabao.base.util;
/**
 * 生成唯一id
 */
public class SnowflakeUtil {
	
	private static SnowflakeIdWorker ID_WORKER = new SnowflakeIdWorker(0, 0);
	
	/**
	 * 获得唯一id
	 * @return 385079485793304584 生成18位数字
	 */
	public static long nextId() {
		return ID_WORKER.nextId();
	}

}

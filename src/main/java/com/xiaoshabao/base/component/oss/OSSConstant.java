package com.xiaoshabao.base.component.oss;

public interface OSSConstant {
	/** 文件存储类型*/
	interface Type{
		/**本地相对目录*/
		Integer LOCAL_RELATIVE=1;
		/**本地绝地目录*/
		Integer LOCAL_ABSOLUTE=2;
		/**阿里云*/
		Integer ALIYUN=3;
	}
	
	/**上传接口前缀标识*/
	String ablePrefix="StorageAble_";
	
}

package com.xiaoshabao.base.component.oss;

public interface OSSConstant {
	/** 文件存储类型*/
	interface Type{
		/**本地相对目录*/
		Integer LOCAL=1;
		/**阿里云*/
		Integer ALIYUN=2;
	}
	
	/**上传接口前缀标识*/
	String ablePrefix="StorageAble_";
	
}

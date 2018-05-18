package com.xiaoshabao.base.component.oss.core;

import org.springframework.web.multipart.MultipartFile;

public interface StorageAble {
	/**
	 * 初始化配置信息
	 */
	void initConfig();
	
	/**
	 * 验证文件存在以及类型(验证不通过抛出MsgErrorException)
	 * @param file
	 */
	void validateFile(MultipartFile file);
	/**
	 * 上传文件（默认验证文件）
	 * @param file
	 * @return 返回基本目录后的 目录
	 */
	String upload(MultipartFile file);
	
	
}

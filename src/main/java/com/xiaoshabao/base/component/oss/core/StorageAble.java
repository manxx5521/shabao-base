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
	
	/**
	 * 获得url，本站采用 /f/201805/100.jpg 形式
	 * @return 找不到时返回null
	 */
	String getUrl(Long fileId);
	/**
	 * 获得全路径url http://localshot/f/201805/100.jpg 形式
	 * @return 找不到时返回null
	 */
	String getUrlFull(Long fileId);
	
}

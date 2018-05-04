package com.xiaoshabao.base.component.oss.core;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageAble {
	
	/**
	 * 验证文件存在以及类型
	 * @param file
	 */
	void validateFile(MultipartFile file);
	
	 /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    String upload(byte[] data, String path);

    /**
     * 文件上传
     * @param data     文件字节数组
     * @param suffix   后缀
     * @return         返回http地址
     */
    String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     * @param inputStream  字节流
     * @param suffix       后缀
     * @return             返回http地址
     */
    String uploadSuffix(InputStream inputStream, String suffix);

}

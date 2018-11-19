package com.xiaoshabao.base.component.oss.core;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.oss.dto.UploadInfo;
import com.xiaoshabao.base.entity.SysFileEntity;

public interface StorageAble {
	
	/**
	 * 验证文件存在以及类型(验证不通过抛出MsgErrorException)
	 * @param file
	 */
	void validateFile(MultipartFile file);
	
	/**
	 * 上传文件
	 * @param url
	 * @return 返回本站相对目录访问url
	 */
	String upload(String url);
	/**
	 * 上传文件（默认验证文件）
	 * @param file
	 * @return 返回本站相对目录访问url
	 */
	String upload(MultipartFile file);
	/**
	 * 上传文件（默认验证文件）
	 * @param file
	 * @return 返回上传后的详细信息
	 */
	public UploadInfo uploadForInfo(MultipartFile file);
	
	/**
	 * 上传文件（默认验证文件）
	 * @param file
	 * @return 返回基本目录后的 目录
	 */
	String upload(MultipartFile file,int x, int y, int width, int height);
	/**
   	 * 上传截取图
   	 * @param filePath 上传文件的路径
   	 * @param x 截取的 x 轴位置
   	 * @param y 截取的 y 轴位置
   	 * @param width 截取图片宽度
   	 * @param height 截取图片高度
   	 * @param maxLength 等比例缩小，最大长度
   	 * @return 相对url，本站采用 /f/201805/100.jpg 形式
   	 */
	String upload(String filePath, int x, int y, int width,int height, int maxLength);
	/**
	 * 将图片剪裁，并缩小
	 * @param file
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param maxLength 缩略后的最大长度
	 * @return
	 */
	String upload(MultipartFile file, int x, int y, int width, int height,int maxLength);
	
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
	/**
   	 * 获得文件真实存放路径
   	 * @return 找不到时返回null
   	 */
    String getRealFilePath(Long fileId);
    
    /**
   	 * 获得文件真实存放路径
   	 * @param fileEntity
   	 * @return
   	 */
   	String getRealFilePath(SysFileEntity entity);
   	
   	/**
	 * 获得文件实体对象
	 * @param fileId
	 * @return
	 */
	SysFileEntity getFileEntity(Long fileId);
	
	/**
	 * 获得文件输入流
	 * @param entity
	 * @return
	 */
	InputStream getFileInputStream(SysFileEntity entity) throws IOException;
	
}

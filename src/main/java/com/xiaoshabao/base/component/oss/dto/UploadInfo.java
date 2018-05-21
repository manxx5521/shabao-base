package com.xiaoshabao.base.component.oss.dto;
/**
 * 文件上传返回详细信息
 */
public class UploadInfo {
	
	private Long fileId;
	private String url;
	/**
	 * 文件存放真实目录
	 */
	private String filePath;
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}

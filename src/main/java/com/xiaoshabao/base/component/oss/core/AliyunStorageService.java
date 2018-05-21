package com.xiaoshabao.base.component.oss.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xiaoshabao.base.exception.ServiceException;

/**
 * 阿里云存储
 */
@Component
public class AliyunStorageService extends BaseStorageService {
    private OSS client;

    interface AliyunConstant {
    	/**阿里云绑定的域名*/
        String DOMAIN="custom.oss.aliyun.domain";
        /**阿里云EndPoint*/
        String END_POINT="custom.oss.aliyun.endPoint";
        /**阿里云AccessKeyId*/
        String ACCESS_KEY_ID="custom.oss.aliyun.accessKeyId";
        /**阿里云AccessKeySecret*/
        String ACCESS_KEY_SECRET="custom.oss.aliyun.accessKeySecret";
        /**阿里云BucketName*/
        String BUCKET_NAME="custom.oss.aliyun.bucketName"; 
    }

    @Override
	public void init(){
        client = new OSSClientBuilder().build(config.getString(AliyunConstant.END_POINT), config.getString(AliyunConstant.ACCESS_KEY_ID),
                config.getString(AliyunConstant.ACCESS_KEY_SECRET));
    }

    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getString(AliyunConstant.BUCKET_NAME), path, inputStream);
        } catch (Exception e){
            throw new ServiceException("上传文件失败，请检查配置信息");
        }

        return config.getString(AliyunConstant.DOMAIN) + "/" + path;
    }


	@Override
	public String upload(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBasePath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(byte[] data, String basePath, String relativePath) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrlFull(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBaseUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(MultipartFile file, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(MultipartFile file, int x, int y, int width,
			int height, int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRealFilePath(Long fileId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String upload(String filePath, int x, int y, int width, int height,
			int maxLength) {
		// TODO Auto-generated method stub
		return null;
	}

}

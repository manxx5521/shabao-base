package com.xiaoshabao.base.component.oss.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;

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
        /**阿里云路径前缀*/
        String PREFIX="custom.oss.aliyun.prefix";
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

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            client.putObject(config.getString(AliyunConstant.BUCKET_NAME), path, inputStream);
        } catch (Exception e){
            throw new ServiceException("上传文件失败，请检查配置信息");
        }

        return config.getString(AliyunConstant.DOMAIN) + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getString(AliyunConstant.PREFIX), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getString(AliyunConstant.PREFIX), suffix));
    }
}

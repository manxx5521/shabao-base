package com.xiaoshabao.base.component.oss.core;

import com.aliyun.oss.OSSClient;
import com.xiaoshabao.base.component.SysConfig;
import com.xiaoshabao.base.exception.ServiceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 阿里云存储
 */
public class LocalStorageService extends BaseStorageService{

    interface LocalConstant {
    	
    }

    public void init(){
    	
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

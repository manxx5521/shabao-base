package com.xiaoshabao.base.component.oss.core;

import com.aliyun.oss.OSSClient;
import com.xiaoshabao.base.component.SysConfig;
import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.exception.ServiceException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 阿里云存储
 */
@Service(OSSConstant.ablePrefix+1)
public class LocalStorageService extends BaseStorageService{

    interface LocalConstant {
    	
    }

    public void init(){
    	
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

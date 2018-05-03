package com.xiaoshabao.base.component.oss.core;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xiaoshabao.base.component.SysConfig;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 */
public abstract class BaseStorageService  implements StorageAble{
	@Autowired
    protected SysConfig config;
    
    public BaseStorageService() {
    	//初始化
        init();
    }
    
    /**
     * 初始化方法
     */
    public void init() {
    	
    }
    
    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //文件路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//定义格式，不显示毫秒
        String path=df.format(System.currentTimeMillis()) + "/" + uuid;
//        String path = DateTime.now().toString("yyyyMMdd") + "/" + uuid;
        if(StringUtils.isNotBlank(prefix)){
            path = prefix + "/" + path;
        }

        return path + suffix;
    }

    /**
     * 文件上传
     * @param data    文件字节数组
     * @param path    文件路径，包含文件名
     * @return        返回http地址
     */
    public abstract String upload(byte[] data, String path);

    /**
     * 文件上传
     * @param data     文件字节数组
     * @param suffix   后缀
     * @return         返回http地址
     */
    public abstract String uploadSuffix(byte[] data, String suffix);

    /**
     * 文件上传
     * @param inputStream   字节流
     * @param path          文件路径，包含文件名
     * @return              返回http地址
     */
    public abstract String upload(InputStream inputStream, String path);

    /**
     * 文件上传
     * @param inputStream  字节流
     * @param suffix       后缀
     * @return             返回http地址
     */
    public abstract String uploadSuffix(InputStream inputStream, String suffix);

}

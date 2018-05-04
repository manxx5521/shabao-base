package com.xiaoshabao.base.component.oss.core;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.SysConfig;
import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.exception.MsgErrorException;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class BaseStorageService  implements StorageAble{
	@Autowired
    protected SysConfig config;
	/**上传根目录*/
	protected String basePath;
	
	protected String[] fileTypes;
    
    public BaseStorageService() {
    	initConfig();
    	//初始化
        init();
    }
    
    private void initConfig() {
    	basePath=config.getString(OSSConstant.basePathId);
//    	if(config.exists(key, null);
    }
    
    /**
     * 初始化方法
     */
    public void init() {
    	
    }
    
    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }
    
    /**
     * 文件路径
     * @param prefix 前缀
     * @param suffix 后缀
     * @return 返回上传路径
     */
    public String getPath(String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //文件路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//定义格式，不显示毫秒
        String path=df.format(System.currentTimeMillis()) + "/" + uuid;
//        String path = DateTime.now().toString("yyyyMMdd") + "/" + uuid;
        path = basePath + "/" + path;

        return path + suffix;
    }
    
    @Override
    public void validateFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new MsgErrorException("文件不能为空");
		}
		
		if (!checkFileType(file.getOriginalFilename())) {
			throw new MsgErrorException("文件格式不支持");
    	}
	}
	
	/**
	 * 文件类型判断
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		if(fileTypes.length>0) {
			for(String type:fileTypes) {
				if(fileName.toLowerCase().endsWith(type)){
					return true;
				}
			}
		}
		return false;
	}

}

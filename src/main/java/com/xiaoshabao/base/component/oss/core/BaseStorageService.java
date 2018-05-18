package com.xiaoshabao.base.component.oss.core;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.oss.StorageConstant;
import com.xiaoshabao.base.component.sysConfig.ConfigType;
import com.xiaoshabao.base.component.sysConfig.SysConfig;
import com.xiaoshabao.base.dao.BaseDao;
import com.xiaoshabao.base.entity.SysFileEntity;
import com.xiaoshabao.base.exception.MsgErrorException;
import com.xiaoshabao.base.util.SnowflakeUtil;

/**
 * 云存储(支持七牛、阿里云、腾讯云、又拍云)
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public abstract class BaseStorageService  implements StorageAble{
	@Autowired
    protected SysConfig config;
	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;
	/**上传根目录*/
	protected String basePath;
	
	protected String[] fileTypes;
	
	protected Integer maxSize;
    
    public void initConfig() {
    	if(config.exists(StorageConstant.basePathId)) {
    		basePath=config.getString(StorageConstant.basePathId);
    	}else {
    		basePath="";
    	}
    	
    	if(config.exists(StorageConstant.fileTypesId, ConfigType.ARRAY)){
    		fileTypes=config.getArray(StorageConstant.fileTypesId);
    	}else{
    		fileTypes=new String[]{};
    	}
    	if(config.exists(StorageConstant.maxSizeId, ConfigType.INTEGER)){
    		maxSize=config.getInteger(StorageConstant.maxSizeId);
    	}
    	//初始化
        init();
    }
    
    /**
     * 初始化方法
     */
    public void init() {
    	
    }
    @Override
    public void validateFile(MultipartFile file) {
		if (file == null || file.isEmpty()) {
			throw new MsgErrorException("文件不能为空");
		}
		
		if (!checkFileType(file.getOriginalFilename())) {
			throw new MsgErrorException("文件格式不支持");
    	}
		if(maxSize!=null&&maxSize<file.getSize()) {
			throw new MsgErrorException("文件大小超出上限");
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
		}else{
			return true;
		}
		return false;
	}
	
	/**
	 * 获得保存文件的基本目录 /aaa/bb/
	 */
	public abstract String getBasePath();
    
    @Override
	public String upload(MultipartFile file) {
    	validateFile(file);
    	long size=file.getSize();
    	String fileName = file.getOriginalFilename();
    	try {
    		return save(file.getBytes(),fileName,size);
		} catch (IOException e) {
			throw new MsgErrorException("获得文件内容数组失败");
		}
	}
    
    
    public final String save(byte[] data,String fileName,long size) {
    	String md5 = DigestUtils.md5Hex(data);
    	String ext=FilenameUtils.getExtension(fileName);
    	String baseName=FilenameUtils.getBaseName(fileName);
    	
    	long id=SnowflakeUtil.nextId();
    	
    	//文件路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//定义格式，不显示毫秒
        String path=df.format(System.currentTimeMillis())+"/";
        
        SysFileEntity entity=new SysFileEntity();
        entity.setFileId(id);
        entity.setUploadName(baseName);
        entity.setSavePath(path);
        entity.setExt(ext);
        entity.setSize(size);
        entity.setMd5(md5);
        baseDao.insert(SysFileEntity.class, entity);
        
    	try {
    		return save(data,getBasePath(),path+id+"."+ext);
		} catch (Exception e) {
			throw new MsgErrorException("保存文件时，生成文件异常",e);
		}
    }
    
    /**
     * 将文件保存到path目录（真实保存目录basePath+relativePath）
     * @param data
     * @param basePath 保存文件的基本目录 /aaa/bb/
     * @param relativePath 相对目录 aa/bb/cc.jpg
     * @return 返回可访问的url，用作回显
     */
    public abstract String save(byte[] data,String basePath,String relativePath) throws Exception ;
    

}

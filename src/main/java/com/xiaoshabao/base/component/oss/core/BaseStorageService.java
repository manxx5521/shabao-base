package com.xiaoshabao.base.component.oss.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.oss.StorageConstant;
import com.xiaoshabao.base.component.oss.dto.UploadInfo;
import com.xiaoshabao.base.component.sysConfig.ConfigType;
import com.xiaoshabao.base.component.sysConfig.SysConfig;
import com.xiaoshabao.base.dao.BaseDao;
import com.xiaoshabao.base.entity.SysFileEntity;
import com.xiaoshabao.base.exception.MsgErrorException;
import com.xiaoshabao.base.util.SnowflakeUtil;

import net.coobird.thumbnailator.Thumbnails;

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
	
    
    @Override
	public String upload(MultipartFile file) {
    	return uploadForInfo(file).getUrl();
	}
    
    @Override
	public UploadInfo uploadForInfo(MultipartFile file) {
		UploadInfo result=new UploadInfo();
    	validateFile(file);
    	long size=file.getSize();
    	String fileName = file.getOriginalFilename();
    	try {
    		byte[] data=file.getBytes();
    		String md5 = DigestUtils.md5Hex(data);
        	SysFileEntity entity=new SysFileEntity();
        	entity.setMd5(md5);
        	
        	//检查是否存在文件
        	List<SysFileEntity> dbEntitys=baseDao.getData(SysFileEntity.class, entity);
        	if(dbEntitys!=null&&!dbEntitys.isEmpty()){
        		//如果已经存在
        		entity=dbEntitys.get(0);
        		result.setUrl(getUrl(entity));
        	}else {
        		//不存在，新上传
        		entity=this.insertEntity(md5, fileName, size);
        		String url=save(data,getBasePath(),getRelativePath(entity));
        		result.setUrl(url);
        	}
        	
        	result.setFileId(entity.getFileId());
        	result.setFilePath(getRealFilePath(entity));
    		return result;
		} catch (IOException e) {
			throw new MsgErrorException("获得文件内容数组失败");
		}catch (Exception e) {
			throw new MsgErrorException("保存文件时，生成文件异常");
		}
	}
    
    public String upload(MultipartFile file,int x, int y, int width, int height) {
    	UploadInfo info=uploadForInfo(file);
    	Long id=SnowflakeUtil.nextId();
    	return saveThumbnails(id,file, x, y, width, height);
    }
    
    protected abstract String saveThumbnails(Long fileId,MultipartFile file,int x, int y, int width, int height);
    
    protected final SysFileEntity insertEntity(String md5,String fileName,long size) {
    	return insertEntity(SnowflakeUtil.nextId(), md5, fileName, size);
    }
    
    protected final SysFileEntity insertEntity(Long id,String md5,String fileName,long size) {
    	SysFileEntity entity=new SysFileEntity();
    	String ext=FilenameUtils.getExtension(fileName);
    	String baseName=FilenameUtils.getBaseName(fileName);
    	//文件路径
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//定义格式，不显示毫秒
        String path=df.format(System.currentTimeMillis())+"/";
        
        entity.setFileId(id);
        entity.setUploadName(baseName);
        entity.setSavePath(path);
        entity.setExt(ext);
        entity.setSize(size);
        
        baseDao.insert(SysFileEntity.class, entity);
    	return entity;
    }
    
    /**
     * 将文件保存到path目录（真实保存目录basePath+relativePath）
     * @param data
     * @param basePath 保存文件的基本目录 /aaa/bb/
     * @param relativePath 相对目录 aa/bb/cc.jpg
     * @return 返回可访问的url，用作回显
     */
    public abstract String save(byte[] data,String basePath,String relativePath) throws Exception ;
    
    /**
	 * 获得保存文件的基本目录 /aaa/bb/
	 */
	public abstract String getBasePath();
    
    @Override
	public final String getUrl(Long fileId) {
    	SysFileEntity dbEntitys=baseDao.getDataById(SysFileEntity.class, fileId);
    	if(dbEntitys==null) {
    		return null;
    	}
		return getUrl(dbEntitys);
	}


    /**
	 * 获得url，本站采用 /f/201805/100.jpg 形式
	 * @return 找不到时返回null
	 */
    protected final String getUrl(SysFileEntity entity) {
    	return getBaseUrl()+getRelativePath(entity);
    }
    
    /**
   	 * http访问基本url
   	 * @return
   	 */
   	public abstract String getBaseUrl();
   	
   	/**
   	 * 获得文件真实存放路径
   	 * @param fileEntity
   	 * @return
   	 */
   	protected final String getRealFilePath(SysFileEntity entity) {
   		return getBasePath()+getRelativePath(entity);
   	}
   	
   	/**
   	 * 获得文件相对路径（不包括basePath）
   	 * @param fileEntity
   	 * @return
   	 */
   	protected final String getRelativePath(SysFileEntity fileEntity) {
   		return fileEntity.getSavePath()+fileEntity.getFileId()+"."+fileEntity.getExt();
   	}

}

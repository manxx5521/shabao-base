package com.xiaoshabao.base.component.oss.core;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.component.oss.StorageConstant;

/**
 * 本地相对目录
 */
@Service(OSSConstant.ablePrefix+1)
public class LocalRelativeStorageService extends BaseStorageService implements ServletContextAware{
	
	private ServletContext context;

	/*
	 *	basePathId 是相对于web根目录的位置 aa/bb/
	 *
	 */
    interface LocalConstant extends StorageConstant{
    	
    }

    public void init(){
    	
    }
    
    
    @Override
   	public String getBasePath() {
       	String separator=File.separator;
   		/** 真实保存目录 */ 
   		String savePath = context.getRealPath("/");
   		if(!savePath.endsWith(separator)){
   			savePath+=separator;
   		} 
   		return savePath+basePath;
   	}

   	@Override
   	public final void setServletContext(ServletContext servletContext) {
   		this.context = servletContext;
   	}

   	@Override
   	public String save(byte[] data, String basePath,String relativePath) throws Exception {
   		File dir=new File(FilenameUtils.getFullPath(basePath+relativePath));
   		if(!dir.exists()) {
   			dir.mkdirs();
   		}
		FileUtils.writeByteArrayToFile(new File(basePath+relativePath), data);
		return context.getContextPath()+this.basePath+relativePath;
   	}
   	
   	

}

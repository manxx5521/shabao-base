package com.xiaoshabao.base.component.oss.core;

import java.io.File;

import org.springframework.stereotype.Service;

import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.component.oss.StorageConstant;

/**
 * 本地相对目录
 */
@Service(OSSConstant.ablePrefix+1)
public class LocalRelativeStorageService extends BaseLocalStorageService{
	
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
	public String getBaseUrl() {
		return context.getContextPath()+this.basePath;
	}

}

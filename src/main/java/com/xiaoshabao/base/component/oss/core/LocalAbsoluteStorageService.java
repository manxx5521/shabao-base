package com.xiaoshabao.base.component.oss.core;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.component.oss.StorageConstant;

/**
 * 本地相对目录
 */
@Service(OSSConstant.ablePrefix+2)
public class LocalAbsoluteStorageService extends BaseLocalStorageService implements ServletContextAware{
	
	/*
	 *	basePathId 存储绝对目录 E:/test/cun/形式，映射到url  /f/**
	 *
	 */
    interface LocalConstant extends StorageConstant{
    	
    }

    public void init(){
    	
    }
    
    /**
     * 存储绝对目录 E:/test/cun/形式
     */
    @Override
   	public String getBasePath() {
   		return basePath;
   	}

	@Override
	public String getBaseUrl() {
		return context.getContextPath()+"/f/"+typePath;
	}


}

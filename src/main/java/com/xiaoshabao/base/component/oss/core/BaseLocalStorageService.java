package com.xiaoshabao.base.component.oss.core;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.context.ServletContextAware;

import com.xiaoshabao.base.lang.SiteConsts;

/**
 * 本地相对目录
 */
public abstract class BaseLocalStorageService extends BaseStorageService implements ServletContextAware{
	
	protected ServletContext context;
    

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
		return getBaseUrl()+relativePath;
   	}
   	
   	@Override
	public String getUrlFull(Long fileId) {
   		String reUrl=this.getUrl(fileId);
   		if(reUrl==null) {
   			return null;
   		}
   		String domain=config.getString(SiteConsts.ConfigId.DOMAIN_ID);
   		StringBuilder sb=new StringBuilder();
   		sb.append("http://");
   		sb.append(domain);
   		sb.append(reUrl);
		return sb.toString();
	}
   	
}

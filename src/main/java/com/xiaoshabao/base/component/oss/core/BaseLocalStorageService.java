package com.xiaoshabao.base.component.oss.core;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

/**
 * 本地相对目录
 */
public abstract class BaseLocalStorageService extends BaseStorageService implements ServletContextAware{
	
	protected ServletContext context;
    

   	@Override
   	public final void setServletContext(ServletContext servletContext) {
   		this.context = servletContext;
   	}


}

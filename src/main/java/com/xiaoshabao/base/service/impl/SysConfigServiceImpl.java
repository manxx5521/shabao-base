package com.xiaoshabao.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.service.SysConfigService;
@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService,ApplicationListener<ContextRefreshedEvent> {
	
	// 缓存管理
	@Autowired
	private CacheManager cacheManager;

	@Override
	@Cacheable("sysConfig")
	public <T> T getDataById(Class<T> clazz, Object id) {
		return super.getDataById(clazz, id);
	}

	/**
	 * 系统启动后清空系统参数缓存
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		cacheManager.getCache("sysConfig").clear();
	}

	
}

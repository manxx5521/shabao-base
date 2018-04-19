package com.xiaoshabao.base.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
/**
 * 系统启动完成后，清理固定的缓存
 */
@Component
public class CacheClear implements ApplicationListener<ContextRefreshedEvent> {

	// 缓存管理
	@Autowired
	private CacheManager cacheManager;

	/**
	 * 系统启动后清空系统参数缓存
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		cacheManager.getCache("sysConfig").clear();

		cacheManager.getCacheNames().forEach(name -> {
			if (StringUtils.isNotEmpty(name) && name.startsWith("temp_")) {
				cacheManager.getCache(name).clear();
			}
		});
	}

}

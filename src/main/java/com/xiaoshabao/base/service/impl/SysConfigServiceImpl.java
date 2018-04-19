package com.xiaoshabao.base.service.impl;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.service.SysConfigService;
@Service("sysConfigServiceImpl")
@CacheConfig(cacheNames = "sysConfig")
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService{
	

	@Override
	@Cacheable
	public <T> T getDataById(Class<T> clazz, Object id) {
		return super.getDataById(clazz, id);
	}

}

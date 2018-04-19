package com.xiaoshabao.base.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xiaoshabao.base.service.SysConfigService;
@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService{
	

	@Override
	@Cacheable("sysConfig")
	public <T> T getDataById(Class<T> clazz, Object id) {
		return super.getDataById(clazz, id);
	}

}

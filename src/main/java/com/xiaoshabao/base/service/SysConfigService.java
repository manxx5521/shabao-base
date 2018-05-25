package com.xiaoshabao.base.service;

import com.xiaoshabao.base.entity.SysConfigEntity;

/**
 * 系统配置
 */
public interface SysConfigService{
	
	public SysConfigEntity getDataById(String key);
	
}

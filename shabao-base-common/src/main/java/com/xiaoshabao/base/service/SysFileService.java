package com.xiaoshabao.base.service;

import java.util.List;

import com.xiaoshabao.base.entity.SysFileEntity;

/**
 * 系统文件
 */
public interface SysFileService {
	SysFileEntity getFileEntityById(Long id);

	List<SysFileEntity> getFileEntityByMD5(String md5);

	int insertFileEntity(SysFileEntity entity);

}

package com.xiaoshabao.base.component.oss;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.base.component.SpringContextHolder;
import com.xiaoshabao.base.component.oss.core.BaseStorageService;
import com.xiaoshabao.base.component.oss.core.StorageAble;
import com.xiaoshabao.base.component.sysConfig.SysConfig;

/**
 * 文件上传Factory
 */
@Component
public class OSSFactory {
	@Autowired
	private SysConfig sysConfig;

	/**
	 * 创建存储对象功能接口
	 */
	public StorageAble build() {
		Integer type = sysConfig.getInteger(StorageConstant.typeId);
		return build(type,null,null);
	}
	/**
	 * 创建存储对象功能接口
	 * @param typePath 可能分目录存放 ava/
	 */
	public StorageAble build(String typePath) {
		Integer type = sysConfig.getInteger(StorageConstant.typeId);
		return build(type,typePath,null);
	}
	/**
	 * 创建存储对象功能接口
	 * @param typePath 可能分目录存放 ava/
	 * @param dataDirPattern 分目录存放常用 默认yyyyMMdd、yyyy、yyyyMM
	 */
	public StorageAble build(String typePath,String dataDirPattern) {
		Integer type = sysConfig.getInteger(StorageConstant.typeId);
		return build(type,typePath,dataDirPattern);
	}

	/**
	 * 创建存储对象功能接口
	 * @param type 存储类型 {@link OSSConstant.Type}
	 * @param typePath 可能分目录存放 ava/ (默认存放在default)
	 * @param dataDirPattern 分目录存放常用 默认yyyyMMdd、yyyy、yyyyMM
	 */
	public StorageAble build(Integer type,String typePath,String dataDirPattern) {
		Map<String, StorageAble> ables = SpringContextHolder
				.getApplicationContext().getBeansOfType(StorageAble.class);

		for (Map.Entry<String, StorageAble> entry : ables.entrySet()) {
			String name = entry.getKey();
			if ((OSSConstant.ablePrefix + type).equals(name)) {
				StorageAble able = entry.getValue();
				if(able instanceof BaseStorageService){
					BaseStorageService baseService=(BaseStorageService) able;
					baseService.initConfig(typePath,dataDirPattern);
				}
				return able;
			}
		}
		return null;
	}

}

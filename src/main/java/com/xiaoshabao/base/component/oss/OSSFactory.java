package com.xiaoshabao.base.component.oss;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xiaoshabao.base.component.SpringContextHolder;
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
		return build(type);
	}

	/**
	 * 创建存储对象功能接口
	 * 
	 * @param type
	 *            存储类型 {@link OSSConstant.Type}
	 */
	public StorageAble build(Integer type) {
		Map<String, StorageAble> ables = SpringContextHolder
				.getApplicationContext().getBeansOfType(StorageAble.class);

		for (Map.Entry<String, StorageAble> entry : ables.entrySet()) {
			String name = entry.getKey();
			if ((OSSConstant.ablePrefix + type).equals(name)) {
				StorageAble able = entry.getValue();
				able.initConfig();
				return able;
			}
		}

		/*
		 * if (type.equals(OSSConstant.Type.LOCAL)) { return
		 * SpringContextHolder.getBean(LocalStorageService.class); } else if
		 * (type.equals(OSSConstant.Type.LOCAL)) { return
		 * SpringContextHolder.getBean(AliyunStorageService.class); }
		 */
		return null;
	}

}

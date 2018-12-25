package com.xiaoshabao.base.service;

import java.util.List;

import com.xiaoshabao.base.component.StaticComponent;
import com.xiaoshabao.base.entity.StaticEntity;

public interface StaticService extends StaticComponent{
	
	String PUBLIC="public";
	
	/**
	 * 获取pulbic枚举列表
	 * @param typeId 类型id
	 * @return
	 */
	default List<StaticEntity> getStatic(String typeId){
		return getStatic(typeId,PUBLIC);
	}
	
	/**
	 * 获取枚举列表
	 * @param typeId 类型id
	 * @param module 模块
	 * @return
	 */
	List<StaticEntity> getStatic(String typeId,String module);

}

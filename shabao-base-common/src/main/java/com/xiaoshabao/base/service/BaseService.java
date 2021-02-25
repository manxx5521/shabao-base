package com.xiaoshabao.base.service;

import java.util.List;

import com.xiaoshabao.base.component.PageDto;
import com.xiaoshabao.base.component.PageVo;

/**
 * 基本sevice
 */
public interface BaseService {
	/**
	 * 新增实体
	 */
	<T> int insert(Class<T> clazz, T t);

	/**
	 * 新增实体
	 */
	<T> int insert(String sqlId, Class<T> clazz, Object params);

	/**
	 * 新增实体
	 */
	<T> int insert(String sqlId, Object params);

	/**
	 * 删除实体 T
	 */
	<T> int delete(Class<T> clazz, T t);

	/**
	 * 删除实体 T
	 */
	<T> int delete(String sqlId, Class<T> clazz, Object params);

	/**
	 * 删除实体 T
	 */
	<T> int delete(String sqlId, Object params);

	/**
	 * 修改一个实体
	 */
	<T> int update(Class<T> clazz, Object params);

	/**
	 * 修改一个实体
	 */
	<T> int update(String sqlId, Class<T> clazz, Object params);

	/**
	 * 修改一个实体
	 */
	<T> int update(String sqlId, Object params);

	/**
	 * 校验是否存在
	 */
	<T> boolean exists(Class<T> clazz, T t);

	/**
	 * 按一定条件获取T类型的数据
	 */
	<T> List<T> getData(Class<T> clazz, Object param);

	/**
	 * 通过sqlid获取数据
	 */
	<T> List<T> getData(String sqlId, Class<T> clazz, Object param);

	/**
	 * 通过sqlid获取数据
	 */
	<T> List<T> getData(String sqlId, Object param);

	/**
	 * 通过实体T 和参数获得唯一记录<br/>
	 * 多条取第一条
	 */
	<T> T getDataForObject(Class<T> clazz, Object param);

	/**
	 * 通过sqlid获得单条数据<br/>
	 * 多条取第一条
	 */
	<T> T getDataForObject(String sqlId, Class<T> clazz, Object param);

	/**
	 * 通过sqlid获得单条数据<br/>
	 * 多条取第一条
	 */
	<T> T getDataForObject(String sqlId, Object param);

	/**
	 * 通过id获取数据
	 */
	<T> T getDataById(Class<T> clazz, Object id);

	/**
	 * 分页方法
	 */
	<T, P extends PageVo> PageDto<T> getDataPaging(Class<T> clasz, P pagingPrams);

}
package com.xiaoshabao.base.dao;

import java.util.List;

import com.xiaoshabao.base.component.PageVo;

/**
 * 基本DAO
 * 
 * @param <T>
 *            业务数据类型
 * @param <P>
 *            参数类型
 */
public interface BaseDao {

	/**
	 * 新增实体
	 */
	public <T> int insert(Class<T> clazz, T t);

	/**
	 * 新增实体
	 */
	public <T> int insert(String sqlId, Class<T> clazz, Object params);

	/**
	 * 新增实体
	 */
	public <T> int insert(String sqlId, Object params);

	/**
	 * 删除实体 T
	 */
	public <T> int delete(Class<T> clazz, T t);
	
	/**
	 * 删除实体 T
	 */
	public <T> int delete(String sqlId,Class<T> clazz, Object params);
	
	/**
	 * 删除实体 T
	 */
	public <T> int delete(String sqlId,Object params);

	/**
	 * 修改一个实体
	 */
	public <T> int update(Class<T> clazz, Object params);

	/**
	 * 修改一个实体
	 */
	public <T> int update(String sqlId, Class<T> clazz, Object params);

	/**
	 * 修改一个实体
	 */
	public <T> int update(String sqlId, Object params);

	/**
	 * 校验是否存在
	 */
	public <T> boolean exists(Class<T> clazz, T t);

	/**
	 * 按一定条件获取T类型的数据
	 */
	public <T> List<T> getData(Class<T> clazz, Object param);
	
	/**
	 * 通过sqlid获取数据
	 */
	public <T> List<T> getData(String sqlId, Class<T> clazz,Object param);

	/**
	 * 通过sqlid获取数据
	 */
	public <T> List<T> getData(String sqlId, Object param);
	
	/**
	 * 通过实体T 和参数获得唯一记录<br/>
	 * 多条取第一条
	 */
	public <T> T getDataForObject(Class<T> clazz, Object param);
	
	/**
	 * 通过sqlid获得单条数据<br/>
	 * 多条取第一条
	 */
	public <T> T getDataForObject(String sqlId,Class<T> clazz, Object param);
	
	/**
	 * 通过sqlid获得单条数据<br/>
	 * 多条取第一条
	 */
	public <T> T getDataForObject(String sqlId, Object param);

	/**
	 * 通过id获取数据
	 */
	public <T> T getDataById(Class<T> clazz, Object id);

	/**
	 * 分页查询业务数据
	 */
	public <T, P extends PageVo> List<T> getDataPaging(Class<T> clazz, P PageVo);

}

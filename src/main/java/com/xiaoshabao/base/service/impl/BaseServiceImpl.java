package com.xiaoshabao.base.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageHelper;
import com.xiaoshabao.base.component.PageDto;
import com.xiaoshabao.base.component.PageVo;
import com.xiaoshabao.base.dao.BaseDao;
import com.xiaoshabao.base.service.BaseService;

/**
 * 抽象Service类
 * <p>
 * 添加共性的东西<br/>
 * </p>
 */
public abstract class BaseServiceImpl implements BaseService {
	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	public BaseServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

	
	@Override
	public <T> int insert(Class<T> clazz, T t) {
		return baseDao.insert(clazz, t);
	}

	@Override
	public <T> int insert(String sqlId, Class<T> clazz, Object params) {
		return baseDao.insert(sqlId, clazz, params);
	}

	@Override
	public <T> int insert(String sqlId, Object params) {
		return baseDao.insert(sqlId, params);
	}

	@Override
	public <T> int delete(Class<T> clazz, T t) {
		return baseDao.delete(clazz, t);
	}

	@Override
	public <T> int delete(String sqlId, Class<T> clazz, Object params) {
		return baseDao.delete(sqlId, clazz, params);
	}

	@Override
	public <T> int delete(String sqlId, Object params) {
		return baseDao.delete(sqlId, params);
	}

	@Override
	public <T> int update(Class<T> clazz, Object params) {
		return baseDao.update(clazz, params);
	}

	@Override
	public <T> int update(String sqlId, Class<T> clazz, Object params) {
		return baseDao.update(sqlId, clazz, params);
	}

	@Override
	public <T> int update(String sqlId, Object params) {
		return baseDao.update(sqlId, params);
	}

	@Override
	public <T> boolean exists(Class<T> clazz, T t) {
		return baseDao.exists(clazz, t);
	}

	@Override
	public <T> List<T> getData(Class<T> clazz, Object param) {
		return baseDao.getData(clazz, param);
	}

	@Override
	public <T> List<T> getData(String sqlId, Class<T> clazz, Object param) {
		return baseDao.getData(sqlId, clazz, param);
	}

	@Override
	public <T> List<T> getData(String sqlId, Object param) {
		return baseDao.getData(sqlId, param);
	}

	@Override
	public <T> T getDataForObject(Class<T> clazz, Object param) {
		return baseDao.getDataForObject(clazz, param);
	}

	@Override
	public <T> T getDataForObject(String sqlId, Class<T> clazz, Object param) {
		return baseDao.getDataForObject(sqlId, clazz, param);
	}

	@Override
	public <T> T getDataForObject(String sqlId, Object param) {
		return baseDao.getDataForObject(sqlId, param);
	}

	@Override
	public <T> T getDataById(Class<T> clazz, Object id) {
		return baseDao.getDataById(clazz, id);
	}

	// 通过这个方法把分页查询DAO实例
	@Override
	public <T, P extends PageVo> PageDto<T> getDataPaging(Class<T> clasz,
			P pageVo) {
		PageHelper.startPage(pageVo.getIndex(),pageVo.getSize());
		PageDto<T> result=new PageDto<T>(pageVo);
		result.setData(this.baseDao.getDataPaging(clasz, pageVo));
		return result;
	}

}

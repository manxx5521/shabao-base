package com.xiaoshabao.base.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xiaoshabao.base.component.PageVo;
import com.xiaoshabao.base.dao.BaseDao;
import com.xiaoshabao.base.enums.DaoEnum;

/**
 * 通用的数据库操作组件
 * 
 * @param <T>
 * @param <P>
 */
@Repository("mybatisBaseDao")
public class MybatisBaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

	/**
	 * 目标对象类名称
	 */
	protected String clazzName;

	// 注入sqlSession
	@Resource
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public <T> int insert(Class<T> clazz, T t) {
		return this.getSqlSession().insert(
				DaoEnum.INSERT.getVlaue() + clazz.getSimpleName(), t);
	}
	
	@Override
	public <T> int insert(String sqlId, Class<T> clazz, Object params) {
		return this.getSqlSession().insert(sqlId, params);
	}

	@Override
	public <T> int insert(String sqlId, Object params) {
		return this.getSqlSession().insert(sqlId, params);
	}
	

	@Override
	public <T> int delete(Class<T> clazz, T t) {
		return this.getSqlSession().delete(
				DaoEnum.DELETE.getVlaue() + clazz.getSimpleName(), t);
	}
	
	@Override
	public <T> int delete(String sqlId, Class<T> clazz, Object params) {
		return this.getSqlSession().delete(sqlId, params);
	}

	@Override
	public <T> int delete(String sqlId, Object params) {
		return this.getSqlSession().delete(sqlId, params);
	}

	@Override
	public <T> int update(Class<T> clazz, Object p) {
		return this.getSqlSession().update(
				DaoEnum.UPDATE.getVlaue() + clazz.getSimpleName(), p);

	}
	
	@Override
	public <T> int update(String sqlId, Class<T> clasz, Object params) {
		return update(sqlId, params);
	}

	@Override
	public <T> int update(String sqlId, Object params) {
		return this.getSqlSession().update(
				sqlId, params);
	}

	@Override
	public <T> boolean exists(Class<T> clazz, T t) {
		List<T> result = this.getSqlSession().<T> selectList(
				DaoEnum.EXISTS.getVlaue() + clazz.getSimpleName(), t);
		if (result == null || result.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public <T> List<T> getData(Class<T> clazz, Object param) {
		return getSqlSession().selectList(
				DaoEnum.GETDATA.getVlaue() + clazz.getSimpleName(), param);
	}
	
	@Override
	public <T> List<T> getData(String sqlid,Class<T> clazz, Object param) {
		return this.getSqlSession().selectList(sqlid, param);
	}

	@Override
	public <T> List<T> getData(String sqlid, Object param) {
		return this.getSqlSession().selectList(sqlid, param);
	}
	
	@Override
	public <T> T getDataForObject(Class<T> clazz, Object param) {
		return this.getSqlSession().selectOne(
				DaoEnum.GETDATA.getVlaue() + clazz.getSimpleName(), param);
	}

	@Override
	public <T> T getDataForObject(String sqlId, Class<T> clazz, Object param) {
		return this.getSqlSession().selectOne(sqlId, param);
	}

	@Override
	public <T> T getDataForObject(String sqlId, Object param) {
		return this.getSqlSession().selectOne(sqlId, param);
	}

	@Override
	public <T> T getDataById(Class<T> clazz, Object id) {
		return this.getSqlSession().selectOne(
				"get" + clazz.getSimpleName() + "ById", id);
	}

	@Override
	public <T, P extends PageVo> List<T> getDataPaging(Class<T> clazz,
			P pageVo) {
		return this.getSqlSession().<T> selectList(
				DaoEnum.PAGINGQUERY.getVlaue() + clazz.getSimpleName(),
				pageVo);
	}

}

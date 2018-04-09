package com.xiaoshabao.base.service.impl;

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

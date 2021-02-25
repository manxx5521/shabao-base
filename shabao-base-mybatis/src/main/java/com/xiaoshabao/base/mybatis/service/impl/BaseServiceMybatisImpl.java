package com.xiaoshabao.base.mybatis.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoshabao.base.service.CommonService;

/**
 * 抽象Service类
 * <p>
 * 添加共性的东西<br/>
 * </p>
 */
public abstract class BaseServiceMybatisImpl<Mapper extends BaseMapper<entity>, entity>
		extends ServiceImpl<Mapper, entity> {
	protected Logger logger;

	// @Resource(name = "mybatisBaseDao")
	// protected BaseDao baseDao;

	@Resource(name = "commonServiceImpl")
	protected CommonService common;

	public BaseServiceMybatisImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

}

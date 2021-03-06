package com.xiaoshabao.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Controller 基类
 * 
 */
public class BaseController {
	
	public BaseController() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

	protected Logger logger;

	/**重定向标识符*/
	protected final static String REDIRECT="redirect:";
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

}

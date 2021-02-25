package com.xiaoshabao.base.service;

public interface CommonService {
	/**
	 * 错误信息
	 */
	void error(String message);
	/**
	 * 错误信息
	 */
	void error(String message,Exception e);
	/**
	 * 提示信息
	 */
	void message(String message);
	/**
	 * 提示信息
	 */
	void message(String message,Exception e);
}

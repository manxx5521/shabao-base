package com.xiaoshabao.base.exception;

/**
 * Service层业务异常
 * <p>
 * 用于输出业务曾提示信息
 * </p>
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String mess) {
		super(mess);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}

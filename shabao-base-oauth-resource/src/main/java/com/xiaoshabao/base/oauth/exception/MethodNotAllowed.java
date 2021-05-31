package com.xiaoshabao.base.oauth.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoshabao.base.oauth.component.PigAuth2ExceptionSerializer;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@JsonSerialize(using = PigAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends PigAuth2Exception {

	public MethodNotAllowed(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "method_not_allowed";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.METHOD_NOT_ALLOWED.value();
	}

}

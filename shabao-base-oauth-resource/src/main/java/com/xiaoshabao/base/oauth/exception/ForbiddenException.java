package com.xiaoshabao.base.oauth.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoshabao.base.oauth.component.PigAuth2ExceptionSerializer;

/**
 */
@JsonSerialize(using = PigAuth2ExceptionSerializer.class)
public class ForbiddenException extends PigAuth2Exception {

	public ForbiddenException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "access_denied";
	}

	@Override
	public int getHttpErrorCode() {
		return HttpStatus.FORBIDDEN.value();
	}

}

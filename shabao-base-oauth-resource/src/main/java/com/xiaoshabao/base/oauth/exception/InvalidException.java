
package com.xiaoshabao.base.oauth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xiaoshabao.base.oauth.component.PigAuth2ExceptionSerializer;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@JsonSerialize(using = PigAuth2ExceptionSerializer.class)
public class InvalidException extends PigAuth2Exception {

	public InvalidException(String msg, Throwable t) {
		super(msg);
	}

	@Override
	public String getOAuth2ErrorCode() {
		return "invalid_exception";
	}

	@Override
	public int getHttpErrorCode() {
		return 426;
	}

}

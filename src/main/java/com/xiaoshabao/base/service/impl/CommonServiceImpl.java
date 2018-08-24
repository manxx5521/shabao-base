package com.xiaoshabao.base.service.impl;

import org.springframework.stereotype.Service;

import com.xiaoshabao.base.exception.MsgErrorException;
import com.xiaoshabao.base.exception.MsgInfoException;
import com.xiaoshabao.base.service.CommonService;
@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService {

	@Override
	public void error(String message) {
		throw new MsgErrorException(message);
	}

	@Override
	public void error(String message, Exception e) {
		throw new MsgErrorException(message, e);
	}

	@Override
	public void message(String message) {
		throw new MsgInfoException(message);

	}

	@Override
	public void message(String message, Exception e) {
		throw new MsgInfoException(message, e);
	}

}

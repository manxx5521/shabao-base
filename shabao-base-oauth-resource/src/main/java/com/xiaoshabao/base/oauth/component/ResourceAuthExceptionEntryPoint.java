package com.xiaoshabao.base.oauth.component;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoshabao.base.oauth.util.OauthConstants;
import com.xiaoshabao.base.oauth.util.ResultUtil;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author lengleng
 * @date 2019/2/1 客户端异常处理 1. 可以根据 AuthenticationException 不同细化异常处理
 */
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	private final ObjectMapper objectMapper;

	@Override
	@SneakyThrows
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) {
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setContentType(OauthConstants.APPLICATION_JSON_UTF8_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(ResultUtil.fail(authException.getMessage())));
	}

}

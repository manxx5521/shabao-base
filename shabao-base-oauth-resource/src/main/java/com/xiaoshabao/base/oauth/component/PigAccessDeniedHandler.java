
package com.xiaoshabao.base.oauth.component;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoshabao.base.oauth.util.OauthConstants;
import com.xiaoshabao.base.oauth.util.ResultUtil;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * 授权拒绝处理器，覆盖默认的OAuth2AccessDeniedHandler 包装失败信息到PigDeniedException
 */
@Slf4j
@RequiredArgsConstructor
public class PigAccessDeniedHandler extends OAuth2AccessDeniedHandler {

	private final ObjectMapper objectMapper;

	/**
	 * 授权拒绝处理，使用R包装
	 * @param request request
	 * @param response response
	 * @param authException authException
	 */
	@Override
	@SneakyThrows
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException) {
		log.info("授权失败，禁止访问 {}", request.getRequestURI());
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType(OauthConstants.APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpStatus.FORBIDDEN.value());
		PrintWriter printWriter = response.getWriter();
		printWriter.append(objectMapper.writeValueAsString(ResultUtil.fail("授权失败，禁止访问")));
	}

}

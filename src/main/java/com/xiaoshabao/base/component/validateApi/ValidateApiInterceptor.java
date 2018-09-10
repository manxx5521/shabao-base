package com.xiaoshabao.base.component.validateApi;

import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import com.xiaoshabao.base.component.ContextHolderUtils;
import com.xiaoshabao.base.exception.ServiceException;

/**
 * api请求验证注解
 */
@Aspect
@Configuration
public class ValidateApiInterceptor {

	@Before("execution(public * *(..)) && @annotation(com.xiaoshabao.base.component.validateApi.ValidateApi)")
	public void before(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		try {
			ValidateApi validate = method.getAnnotation(ValidateApi.class);
			HttpServletRequest request = ContextHolderUtils.getRequest();
			Enumeration<String> names = request.getHeaderNames();
			String time = null;
			String token = null;
			while (names.hasMoreElements()) {
				String value = names.nextElement();
				if (validate.timeName().equals(value)) {
					time = request.getHeader(value);
				}
				if (validate.tokenName().equals(value)) {
					token = request.getHeader(value);
				}
			}
			if (StringUtils.isEmpty(time)) {
				throw new ServiceException("验证接口时错误，缺少参数名为" + validate.timeName() + "的时间戳！");
			} else {
				if (System.currentTimeMillis() - Long.valueOf(time) > validate.timeUnit().toMillis(validate.expire())) {
					throw new ServiceException("验证接口时错误，参数名为" + validate.timeName() + "的时间戳已经过期！");
				}
			}

			if (StringUtils.isEmpty(token)) {
				throw new ServiceException("验证接口时错误，缺少参数名为" + validate.tokenName() + "的加密字符串！");
			}

			String sha1 = DigestUtils.sha1Hex(time + validate.value());
			if (!token.equals(sha1)) {
				throw new ServiceException("验证接口时错误，加密字符串不匹配！");
			}
		} catch (NumberFormatException e) {
			throw new ServiceException("验证接口参数错误，时间类型必须为时间戳！", e);
		}
	}

	@AfterReturning(value = "execution(public * *(..)) && @annotation(com.xiaoshabao.base.component.validateApi.AddTokenApi)", returning = "ret")
	public void after(JoinPoint joinPoint, Object ret) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		try {
			AddTokenApi addtoken = method.getAnnotation(AddTokenApi.class);
			String time = String.valueOf(System.currentTimeMillis());
			String sha1 = DigestUtils.sha1Hex(time + addtoken.value());
			HttpServletResponse response = ContextHolderUtils.getResponse();
			response.setHeader(addtoken.timeName(), time);
			response.setHeader(addtoken.tokenName(), sha1);
		} catch (Throwable e) {
			throw new ServiceException("添加接口参数异常", e);
		}
	}

}

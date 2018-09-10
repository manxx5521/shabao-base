package com.xiaoshabao.base.component.validateApi;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 验证api是否合法
 * <p>
 * 在request对象中获取参数：<br>
 *   默认timeName()值request_time:请求时间戳<br>
 *   request_token:请求加密字符串（请求时间+当前注解key值 sha1加密）<br>
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ValidateApi{
	/**
	 * 加密key值
	 */
	String value();

	/**
	 * 过期时间,默认为5分钟
	 */
	int expire() default 5;

	/**
	 * 超时时间单位
	 */
	TimeUnit timeUnit() default TimeUnit.MINUTES;
	
	/**
	 * 时间参数名字
	 */
	String timeName() default "request_time";
	
	/**
	 * 加密参数名字
	 */
	String tokenName() default "request_token";
	
}
package com.xiaoshabao.base.component.validateApi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 给请求添加验证参数
 * <p>
 * 在request对象中获取参数：<br>
 *   request_time:请求时间戳<br>
 *   request_token:请求加密字符串（请求时间+当前注解key值 sha1加密）<br>
 * </p>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AddTokenApi {
	/**
	 * 加密key值
	 */
	String value();
	
	/**
	 * 时间参数名字
	 */
	String timeName() default "request_time";
	
	/**
	 * 加密参数名字
	 */
	String tokenName() default "request_token";

}
package com.xiaoshabao.base.component;

import java.util.Locale;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 用来获得ApplicationContext
 */
@Component("springContextHolder")
@Lazy(false)
public final class SpringContextHolder implements DisposableBean,
		ApplicationContextAware {
	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		context = applicationContext;
	}

	@Override
	public void destroy() {
		context = null;
	}

	public static ApplicationContext getApplicationContext() {
		return context;
	}

	public static Object getBean(String name) {
		Assert.hasText(name,"获得SpringBean时需要传入name");
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> type) {
		Assert.hasText(name,"获得SpringBean时需要传入name");
		Assert.notNull(type,"获得SpringBean时需要传入type");
		return context.getBean(name, type);
	}

	public static String getMessage(String code, Object[] args) {
		LocaleResolver localLocaleResolver = (LocaleResolver) getBean(
				"localeResolver", LocaleResolver.class);
		Locale localLocale = localLocaleResolver.resolveLocale(null);
		return context.getMessage(code, args, localLocale);
	}

	public static String getMessage(String code) {
		return getMessage(code, null);
	}
	
//  SpringContextHolder.getApplicationContext().getBeansOfType(FormSessionService.class); 获得某一个借口的所有实现
}

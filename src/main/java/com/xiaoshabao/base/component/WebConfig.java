package com.xiaoshabao.base.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.xiaoshabao.base.component.oss.OSSConstant;
import com.xiaoshabao.base.component.oss.StorageConstant;
import com.xiaoshabao.base.component.sysConfig.SysConfig;

@EnableWebMvc
@Configuration
public class WebConfig  implements WebMvcConfigurer {

	@Autowired
	private SysConfig sysConfig;

	/*
	 * @EnableWebMvc使用后，可能会导致静态资源加载失效。指向 src/main/webapp
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 自定义后重新加载静态支援，解决设置本类后静态资源不加载问题
		registry.addResourceHandler("/templates/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/templates/");
		registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
		registry.addResourceHandler("/public/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/public/");
		registry.addResourceHandler("/webjars/**")
				.addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/META-INF/resources/webjars/");

		//存储文件为绝对目录时，添加映射关系
		if (sysConfig.exists(StorageConstant.typeId)&&sysConfig.getInteger(StorageConstant.typeId)==OSSConstant.Type.LOCAL_ABSOLUTE) {
			registry.addResourceHandler("/f/**").addResourceLocations("file:" + sysConfig.getString(StorageConstant.basePathId));
		}

		// 重新注入swagger
		registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars*").addResourceLocations("classpath:/META-INF/resources/webjars/");

		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	/**
	 * 返回ajax内容时，将long转换为字符串防止精度丢失
	 */
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		/**
		 * 序列换成json时,将所有的long变成string 因为js中得数字类型不能包含所有的java long值
		 */
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		objectMapper.registerModule(simpleModule);
		jackson2HttpMessageConverter.setObjectMapper(objectMapper);
		converters.add(jackson2HttpMessageConverter);
	}
}

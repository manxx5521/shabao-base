package com.xiaoshabao.base.oauth.component;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.xiaoshabao.base.oauth.util.OauthConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态注入ResourceServerConfigurerAdapter实现方便开启EnableResourceServer注解后扫描
 */
@Slf4j
public class PigSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
  
	/**
	 * 根据注解值动态注入资源服务器的相关属性
	 * @param metadata 注解信息
	 * @param registry 注册器
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
		if (registry.isBeanNameInUse(OauthConstants.RESOURCE_SERVER_CONFIGURER)) {
			log.warn("本地存在资源服务器配置，覆盖默认配置:" + OauthConstants.RESOURCE_SERVER_CONFIGURER);
			return;
		}

		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(PigResourceServerConfigurerAdapter.class);
		registry.registerBeanDefinition(OauthConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);

	}

}


package com.xiaoshabao.base.oauth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.xiaoshabao.base.oauth.component.PigResourceServerAutoConfiguration;
import com.xiaoshabao.base.oauth.component.PigResourceServerTokenRelayAutoConfiguration;
import com.xiaoshabao.base.oauth.component.PigSecurityBeanDefinitionRegistrar;
import com.xiaoshabao.base.oauth.feign.PigFeignClientConfiguration;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@ShaBaoResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ PigResourceServerAutoConfiguration.class, PigSecurityBeanDefinitionRegistrar.class,
          PigResourceServerTokenRelayAutoConfiguration.class, PigFeignClientConfiguration.class })
public @interface ShaBaoResourceServer {

}

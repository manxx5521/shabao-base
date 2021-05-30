
package com.xiaoshabao.base.oauth.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ /*PigResourceServerAutoConfiguration.class, PigSecurityBeanDefinitionRegistrar.class,
          PigResourceServerTokenRelayAutoConfiguration.class, PigFeignClientConfiguration.class*/ })
public @interface EnableResourceServer {

}


package com.xiaoshabao.base.oauth2.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.xiaoshabao.base.oauth2.config.OAuth2ClientConfig;
import com.xiaoshabao.base.oauth2.config.ResourceServerConfig;
import com.xiaoshabao.base.oauth2.controller.LoginController;

/**
 * 资源服务注解
 */
@Documented
@Inherited
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
@Import({ OAuth2ClientConfig.class, ResourceServerConfig.class,LoginController.class })
public @interface ShaBaoResourceServer {

}

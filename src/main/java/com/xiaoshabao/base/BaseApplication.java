package com.xiaoshabao.base;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages="com.xiaoshabao.base.controller")
@ComponentScan(basePackages="com.xiaoshabao.base.dao")
@ComponentScan(basePackages="com.xiaoshabao.base.service")
@ComponentScan(basePackages="com.xiaoshabao.base.component")
public class BaseApplication {

}

package com.xiaoshabao.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.xiaoshabao.base.controller")
@ComponentScan(basePackages="com.xiaoshabao.base.dao")
@ComponentScan(basePackages="com.xiaoshabao.base.service")
@ComponentScan(basePackages="com.xiaoshabao.base.component")
@MapperScan(basePackages="com.xiaoshabao.**.mapper")
public class BaseApplication {

}

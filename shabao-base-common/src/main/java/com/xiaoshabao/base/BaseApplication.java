package com.xiaoshabao.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackages={
        "com.xiaoshabao.base.controller",
        "com.xiaoshabao.base.dao",
        "com.xiaoshabao.base.service",
        "com.xiaoshabao.base.component",
        "com.xiaoshabao.base.config"})
//开启事务支持
@EnableTransactionManagement
public class BaseApplication {

}

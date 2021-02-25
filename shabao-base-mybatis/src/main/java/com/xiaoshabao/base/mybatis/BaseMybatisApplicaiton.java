package com.xiaoshabao.base.mybatis;

import org.springframework.context.annotation.ComponentScan;

import com.xiaoshabao.base.BaseApplication;

//@MapperScan(basePackages="com.xiaoshabao.**.mapper")//在
@ComponentScan(basePackages = { "com.xiaoshabao.base.mybatis.config" })
public class BaseMybatisApplicaiton extends BaseApplication {

}

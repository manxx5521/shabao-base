package com.xiaoshabao.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BaseTestApplication extends BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseTestApplication.class, args);
	}

}

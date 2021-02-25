package com.xiaoshabao.base.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	/**web项目上下文 */
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	@Autowired
    protected MockMvc mvc;

	/**所有测试方法执行之前执行该方法 */
	@Before 
	public void before() {
		MockitoAnnotations.initMocks(this);
		//获取mockmvc对象实例(解决druid导致的一个空指针问题)
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	


}

package com.xiaoshabao.base.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

public class StaticControllerTest extends ControllerTest{
	@Test
	public void test01_GetStaticList() throws Exception {
		mvc.perform(get("/p/static/TEST_TYPE"))
		.andDo(print())
		.andExpect(status().isOk());
	}

}

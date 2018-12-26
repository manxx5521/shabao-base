package com.xiaoshabao.base.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;

public class StaticControllerTest extends ControllerTest{
	@Test
	public void test01_GetStaticList() throws Exception {
		String result=mvc.perform(get("/p/static/TEST_TYPE"))
		.andDo(print())
		.andExpect(status().isOk()).andReturn()
		.getResponse().getContentAsString();
		JSONArray json=JSONArray.parseArray(result);
		if(json.size()<1) {
			fail("未能查到数据（应该至少存在一条）");
		}
	}
	
	@Test
	public void test02_GetStaticList() throws Exception {
		String result=mvc.perform(get("/p/static/TEST_TYPE_ERROR"))
		.andDo(print())
		.andExpect(status().isOk()).andReturn()
		.getResponse().getContentAsString();
		JSONArray json=JSONArray.parseArray(result);
		if(json.size()>0) {
			fail("查询错误，当前typeId应该查不到任何谁");
		}
	}

}

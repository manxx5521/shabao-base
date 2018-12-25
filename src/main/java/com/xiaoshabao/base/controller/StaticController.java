package com.xiaoshabao.base.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xiaoshabao.base.entity.StaticEntity;
import com.xiaoshabao.base.service.StaticService;

/**
 * <p>
 * 系统枚举表 前端控制器
 * </p>
 * @author manxx
 * @since 2018-12-25
 */
@RestController
@RequestMapping("/p/static")
public class StaticController {
	
	@Autowired
	private StaticService staticService;

	@GetMapping("/{typeId}")
	public List<StaticEntity> getStaticList(@PathVariable("typeId") String typeId) {
		return staticService.getStatic(typeId);
	}

}

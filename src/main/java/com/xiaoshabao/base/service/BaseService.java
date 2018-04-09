package com.xiaoshabao.base.service;

import com.xiaoshabao.base.component.PageDto;
import com.xiaoshabao.base.component.PageVo;


/**
 * 基本sevice
 */
public interface BaseService {

	/**
	 * 分页方法
	 */
	<T, P extends PageVo> PageDto<T> getDataPaging(Class<T> clasz, P pagingPrams);

}
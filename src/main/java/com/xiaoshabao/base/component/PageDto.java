package com.xiaoshabao.base.component;

import java.util.List;

/**
 * 分页参数
 */
public class PageDto<T> {

	private List<T> data;
	/** 第几页 */
	private Integer index = 1;
	/** 多少行 */
	private Integer size = 10;
	
	public PageDto(){
		
	}
	
	public PageDto(PageVo pageVo){
		this.index=pageVo.getIndex();
		this.size=pageVo.getSize();
	}
	
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
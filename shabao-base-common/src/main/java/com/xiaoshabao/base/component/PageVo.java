package com.xiaoshabao.base.component;

/**
 * 分页参数
 */
public class PageVo {

	/** 第几页 */
	private Integer index = 1;
	/** 多少行 */
	private Integer size = 10;
	
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
	
}
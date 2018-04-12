package com.xiaoshabao.base.lang;

/**
 * 传入名字的静态值
 */
public enum DaoEnum {
	INSERT("insert","插入"),
	DELETE("delete","删除"),
	UPDATE("update","更新"),
	EXISTS("exists","存在"),
	GETDATA("get","获得所有数据"),
	GETONE("one","获得单挑数据"),
	GETBYID("getByid","根据id获得数据（这个不按前缀方式,按getUserById）"),
	PAGINGQUERY("getPaging","获得分页数据");
	
	/**
	 * 值
	 */
	private String vlaue;
	/**
	 * 描述
	 */
	private String describe;
	
	private DaoEnum(String vlaue) {
		this.vlaue = vlaue;
	}
	private DaoEnum(String vlaue, String describe) {
		this.vlaue = vlaue;
		this.describe = describe;
	}
	/**
	 * 获得值
	 */
	public String getVlaue() {
		return vlaue;
	}
	/**
	 * 获得描述
	 */
	public String getDescribe() {
		return describe;
	}
}

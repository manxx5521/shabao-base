package com.xiaoshabao.base.component.sysConfig;

public enum SysEnum {
	/**
	 * 域的名字
	 */
	DOMAIN("site.domain",1);
	
	private String name;
	
	/**类型 1.字符串 2.数字 3.布尔型,4.字符数组*/
	private int type;
	
	private SysEnum(String name,int type){
		this.name=name;
		this.type=type;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}
}

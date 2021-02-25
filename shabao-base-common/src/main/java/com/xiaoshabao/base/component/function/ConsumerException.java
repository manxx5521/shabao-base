package com.xiaoshabao.base.component.function;

/**
 * 带输入不带返回值的函数（抛出异常）
 * @param <T> 输入参数值
 */
public interface ConsumerException<T> {

	/**
	 * 执行
	 * @param t
	 */
	void accept(T t) throws Exception;
}

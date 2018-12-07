package com.xiaoshabao.base.component;

/**
 * AJAX返回值
 * @param <T>
 */
public class AjaxResult {
	/**
	 * 结果是否成功
	 */
	protected boolean success;
	/**
	 * 成功后返回数据
	 */
	protected Object data;
	/**
	 * 返回信息
	 */
	protected String message;
	
	public AjaxResult() {
	}
	/**
	 * 返回错误信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult( String message) {
		this.success = false;
		this.message = message;
	}
	/**
	 * 一般返回成功信息构造
	 * @param success
	 * @param data
	 */
	public AjaxResult( Object data) {
		this.success = true;
		this.data = data;
	}
	
	/**
	 * 一般返回成功信息构造
	 * @param success
	 * @param data
	 */
	public AjaxResult(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}
	
	/**
	 * 返回信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	/**
	 * 返回信息的构造方法
	 * @param success
	 * @param message
	 */
	public AjaxResult(boolean success, String message,Object data) {
		this.success = success;
		this.message = message;
		this.data = data;
	}
	public static AjaxResult ok(String message) {
		return new AjaxResult(true,message);
	}
	
	public static AjaxResult ok(Object data) {
		return new AjaxResult(true,data);
	}
	
	public static AjaxResult fail(String message) {
		return new AjaxResult(false,message);
	}
	
	/**
	 * 设置成功信息
	 * @param message
	 * @return 返回当前类
	 */
	public AjaxResult setSuccessInfo(String message){
		this.success=true;
		this.message=message;
		return this;
	}
	/**
	 * 设置错误信息
	 * @param message
	 * @return 返回当前类
	 */
	public AjaxResult setErrorInfo(String message){
		this.success=false;
		this.message=message;
		return this;
	}
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

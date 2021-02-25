package com.xiaoshabao.base.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	protected Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 视图异常处理
	 * 
	 * @param req
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		// 是否异步请求
		if ((request.getHeader("accept") == null || !(request.getHeader("accept").indexOf("application/json") > -1)
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			Map<String, Object> model = new HashMap<String, Object>();
			if (e instanceof BindException) {
				BindException be = (BindException) e;
				StringBuffer msg = new StringBuffer();
				for (FieldError fieldError : be.getFieldErrors()) {
					msg.append(fieldError.getDefaultMessage()).append("，");
				}
				if (msg.length() > 0) {
					model.put("message", msg.substring(0, msg.length() - 1));
				}
			} else {
				model.put("e", e);
				logger.info("全局异常捕获", e);
			}
			return new ModelAndView("/error/500", model);
		} else {
			AjaxResult result = new AjaxResult();
			result.setSuccess(false);
			if (e instanceof BindException) {
				BindException be = (BindException) e;
				StringBuffer msg = new StringBuffer();
				for (FieldError fieldError : be.getFieldErrors()) {
					msg.append(fieldError.getDefaultMessage()).append("，");
				}
				if (msg.length() > 0) {
					result.setMessage(msg.substring(0, msg.length() - 1));
				}
			} else {
				logger.info("全局异常捕获", e);
				result.setMessage("未知错误");
			}
			return result;
		}
	}

}
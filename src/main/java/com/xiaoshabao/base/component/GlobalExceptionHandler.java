package com.xiaoshabao.base.component;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		if (!(request.getHeader("accept").indexOf("application/json") > -1
				|| (request.getHeader("X-Requested-With") != null
						&& request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("e", e);
			logger.info("全局异常捕获",e);
			return new ModelAndView("/error/500", model);
		} else {
			AjaxResult result = new AjaxResult();
			result.setSuccess(false);
			result.setMessage("错误");
			return result;
		}
	}

}
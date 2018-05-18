package com.xiaoshabao.base.component.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.AjaxResult;
import com.xiaoshabao.base.exception.MsgErrorException;


/**
 * 文件上传
 * 
 * @author ace
 */
@RestController
@RequestMapping("/oss")
public class OssController{
	@Autowired
	private OSSFactory ossFactory;
	/**
	 * 上传文件
	 */
	@RequestMapping("/upload")
	public AjaxResult upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new MsgErrorException("上传文件不能为空");
		}
		//上传文件
		String url = ossFactory.build().upload(file);
		return new AjaxResult(true,"上传成功",url);
	}



}

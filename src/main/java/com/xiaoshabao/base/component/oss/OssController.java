package com.xiaoshabao.base.component.oss;

import java.io.BufferedInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.component.AjaxResult;
import com.xiaoshabao.base.component.oss.core.StorageAble;
import com.xiaoshabao.base.entity.SysFileEntity;
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
	
	/**
	 * 下载文件
	 */
	@RequestMapping("/download/{fileId}")
	public void upload(@PathVariable("fileId") Long fileId,HttpServletResponse response) throws Exception {
		StorageAble factory=ossFactory.build();
		SysFileEntity fileEntity=factory.getFileEntity(fileId);
		
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + fileEntity.getUploadName()+","+fileEntity.getExt());
	    byte[] buff = new byte[1024];
	    try (OutputStream os =  response.getOutputStream();
	    		BufferedInputStream bis =new BufferedInputStream(factory.getFileInputStream(fileEntity));){
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    }
	}



}

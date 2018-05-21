package com.xiaoshabao.base.component.oss.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoshabao.base.exception.MsgErrorException;
import com.xiaoshabao.base.lang.SiteConsts;
import com.xiaoshabao.base.util.SnowflakeUtil;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 本地相对目录
 */
public abstract class BaseLocalStorageService extends BaseStorageService implements ServletContextAware{
	
	protected ServletContext context;
    
	@Override
   	public String upload(MultipartFile file, int x, int y, int width, int height){
		return upload(file, x, y, width, height,Math.max(width, height));
	}

	@Override
	public String upload(MultipartFile file, int x, int y, int width,int height, int maxLength) {
		try (InputStream in=file.getInputStream()){
			return upload(in, file.getOriginalFilename(), x, y, width, height,
					maxLength);
		} catch (IOException e) {
			throw new MsgErrorException("上传文件时，获得文件流错误",e);
		}
	}
	@Override
	public String upload(String filePath, int x, int y, int width,int height, int maxLength) {
		try (InputStream in=new FileInputStream(filePath)){
			return upload(in, FilenameUtils.getName(filePath), x, y, width, height,
					maxLength);
		} catch (IOException e) {
			throw new MsgErrorException("上传文件时，获得文件错误",e);
		}
	}
   	
   	/**
   	 * 上传截取图
   	 * @param inputStream 上传文件的输入流
   	 * @param fileName 文件名字 100.jpg
   	 * @param x 截取的 x 轴位置
   	 * @param y 截取的 y 轴位置
   	 * @param width 截取图片宽度
   	 * @param height 截取图片高度
   	 * @param maxLength 等比例缩小，最大长度
   	 * @return 相对url，本站采用 /f/201805/100.jpg 形式
   	 */
   	protected final String upload(InputStream inputStream,String fileName, int x, int y, int width, int height,int maxLength) {
   		Long fileId=SnowflakeUtil.nextId();
   		//文件路径
        SimpleDateFormat df = new SimpleDateFormat(dataDirPattern);//定义格式，不显示毫秒
        String path=df.format(System.currentTimeMillis())+"/";
   		File dest=new File(getBasePath()+path+fileId+"."+FilenameUtils.getExtension(fileName));
   		if(!dest.getParentFile().exists()){
   			dest.getParentFile().mkdirs();
		}

		// 计算缩放比例
		int tow = width;
		int toh = height;
		if (Math.max(width, height) > maxLength) {
			if (width > maxLength) {
				tow = maxLength;
				toh = height * maxLength / width;
			} else {
				tow = height * maxLength / height;
				toh = maxLength;
			}
		}
		
		try {
			Thumbnails.of(inputStream).sourceRegion(x, y, width, height)
					.size(tow, toh).keepAspectRatio(false).toFile(dest);

			InputStream destIn = new FileInputStream(dest);
			String md5 = DigestUtils.md5Hex(destIn);
			insertEntity(md5, fileName, dest.length());
		} catch (IOException e) {
			throw new MsgErrorException("将文件转换为小图片时，错误。",e);
		}
		return getBaseUrl()+path+fileId+"."+FilenameUtils.getExtension(fileName);
	}



	@Override
   	public String save(byte[] data, String basePath,String relativePath) throws Exception {
   		File dest=new File(basePath+relativePath);
   		if(!dest.getParentFile().exists()){
   			dest.getParentFile().mkdirs();
        }
		FileUtils.writeByteArrayToFile(dest, data);
		return getBaseUrl()+relativePath;
   	}
   	
   	@Override
	public String getUrlFull(Long fileId) {
   		String reUrl=this.getUrl(fileId);
   		if(reUrl==null) {
   			return null;
   		}
   		String domain=config.getString(SiteConsts.ConfigId.DOMAIN_ID);
   		StringBuilder sb=new StringBuilder();
   		sb.append("http://");
   		sb.append(domain);
   		sb.append(reUrl);
		return sb.toString();
	}
   	
   	
   	@Override
   	public final void setServletContext(ServletContext servletContext) {
   		this.context = servletContext;
   	}
   	
}

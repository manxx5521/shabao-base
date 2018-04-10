package com.xiaoshabao.base.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.xiaoshabao.base.entity.SysConfigEntity;
import com.xiaoshabao.base.exception.ServiceException;
import com.xiaoshabao.base.service.SysConfigService;

/**
 * 系统配置类<br>
 * <p>记录系统常用配置信息,
 * 	  数据缓存优先级 数据库>配置文件
 * </p>
 */
@Component("sysConfig")
public class SysConfig{
	
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired  
    private Environment env;
	
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public String getString(String key){
		return (String) getConfigAll(key,Type.STRING);
	}
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public String getString(SysEnum sysEnum){
		return (String) getConfigAll(sysEnum.getName(),Type.STRING);
	}
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public Integer getInteger(String key){
		return (Integer) getConfigAll(key,Type.INTEGER);
	}
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public Integer getInteger(SysEnum sysEnum){
		return (Integer) getConfigAll(sysEnum.getName(),Type.INTEGER);
	}
	
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public Boolean getBoolean(String key){
		return (Boolean) getConfigAll(key,Type.BOOLEAN);
	}
	/**
	 * 获得系统变量值
	 * @param name
	 * @param classz
	 * @return
	 */
	public Boolean getBoolean(SysEnum sysEnum){
		return (Boolean) getConfigAll(sysEnum.getName(),Type.BOOLEAN);
	}
	/**
	 * 验证是否存在变量
	 * @param key
	 * @param type
	 * @return
	 */
	public boolean exists(String key,Type type){
		try {
			getConfigAll(key,type);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private Object getConfigAll(String key,Type type){
		try {
			SysConfigEntity config=this.sysConfigService.getDataById(SysConfigEntity.class, key);
			Integer configType=null;
			String configValue=null;
			if(config!=null){
				configType=config.getType();
				configValue=config.getValue();
			}else if(env.containsProperty("custom.sysConfig"+key)){
				configValue=env.getProperty("custom.sysConfig"+key);
			}
			
			if(configValue!=null){
				switch(type){
					case STRING:	
						if(configType==null||configType==1){
							return configValue;
						}
					case INTEGER:	
						if(configType==null||configType==2){
							return Integer.valueOf(configValue);
						}
					case BOOLEAN:	
						if((configType==null||configType==3)&&"Y".equals(configValue)){
							return Boolean.TRUE;
						}else if((configType==null||configType==3)&&"N".equals(configValue)){
							return Boolean.FALSE;
						}
					default:
						break;
				}
			}
		} catch (Exception e) {
			throw new ServiceException("获取系统参数异常",e);
		}
		throw new ServiceException("获取系统参数异常,系统中不存在符合规则的变量。");
	}
	
	enum Type{
		STRING,INTEGER,BOOLEAN;
	}



	
	
}

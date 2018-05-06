package com.xiaoshabao.base.component.sysConfig;

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
	 */
	public String getString(String key){
		return (String) getConfigNotNull(key,ConfigType.STRING);
	}
	/**
	 * 获得系统变量值
	 */
	public String getString(SysEnum sysEnum){
		return (String) getConfigNotNull(sysEnum.getName(),ConfigType.STRING);
	}
	/**
	 * 获得系统变量值
	 */
	public Integer getInteger(String key){
		return (Integer) getConfigNotNull(key,ConfigType.INTEGER);
	}
	/**
	 * 获得系统变量值
	 */
	public Integer getInteger(SysEnum sysEnum){
		return (Integer) getConfigNotNull(sysEnum.getName(),ConfigType.INTEGER);
	}
	
	/**
	 * 获得系统变量值
	 */
	public Boolean getBoolean(String key){
		return (Boolean) getConfigNotNull(key,ConfigType.BOOLEAN);
	}
	/**
	 * 获得系统变量值
	 */
	public Boolean getBoolean(SysEnum sysEnum){
		return (Boolean) getConfigNotNull(sysEnum.getName(),ConfigType.BOOLEAN);
	}
	/**
	 * 获得系统变量值
	 */
	public String[] getArray(String key){
		return (String[]) getConfigNotNull(key,ConfigType.ARRAY);
	}
	/**
	 * 验证是否存在变量
	 */
	public boolean exists(String key,ConfigType type){
		Object obj =getConfigValue(key,type);
		if(obj==null) {
			return false;
		}
		return true;
	}
	/**
	 * 获得非空变量
	 * @param key
	 * @param type
	 * @return
	 */
	private Object getConfigNotNull(String key,ConfigType type){
		Object obj =getConfigValue(key,type);
		if(obj==null) {
			throw new ServiceException("系统参数“"+key+"”未配置！！");
		}
		return obj;
	}
	/**
	 * 获得变量
	 * @param key
	 * @param type
	 * @return 没有时返回null
	 */
	private Object getConfigValue(String key,ConfigType type) {
		Integer configType=null;
		String configValue=null;
		try {
			SysConfigEntity config=this.sysConfigService.getDataById(SysConfigEntity.class, key);
			if(config!=null){
				configType=config.getType();
				configValue=config.getValue();
			}else if(env.containsProperty("custom.sysConfig."+key)){
				configValue=env.getProperty("custom.sysConfig."+key);
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
					case ARRAY:	
						if(configType==null||configType==4){
							return configValue.split(",");
						}
					default:
						break;
				}
			}
			return configValue;
		} catch (Exception e) {
			throw new ServiceException("获取系统参数异常",e);
		}
	}
	
}


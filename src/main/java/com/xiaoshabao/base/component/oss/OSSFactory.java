package com.xiaoshabao.base.component.oss;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.xiaoshabao.base.component.SpringContextHolder;
import com.xiaoshabao.base.component.oss.core.StorageAble;
import com.xiaoshabao.base.component.sysConfig.SysConfig;

/**
 * 文件上传Factory
 */
@Component
@Lazy
public class OSSFactory {
	@Autowired
	private SysConfig sysConfig;
    
    /**
     * 创建存储对象功能接口
     * @return
     */
    public StorageAble build() {
    	Integer type=sysConfig.getInteger(StorageConstant.typeId);
    	Map<String,StorageAble> ables=SpringContextHolder.getApplicationContext().getBeansOfType(StorageAble.class);
    	
    	for (Map.Entry<String,StorageAble> entry : ables.entrySet()) {
            String name=entry.getKey();
            if((OSSConstant.ablePrefix+type).equals(name)) {
            	StorageAble able= entry.getValue();
            	able.initConfig();
            	return able;
    		}
        }
    	
        /*if (type.equals(OSSConstant.Type.LOCAL)) {
            return SpringContextHolder.getBean(LocalStorageService.class);
        } else if (type.equals(OSSConstant.Type.LOCAL)) {
            return SpringContextHolder.getBean(AliyunStorageService.class);
        } */
        return null;
    }

}

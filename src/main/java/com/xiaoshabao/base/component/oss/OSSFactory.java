package com.xiaoshabao.base.component.oss;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.xiaoshabao.base.component.SpringContextHolder;
import com.xiaoshabao.base.component.SysConfig;
import com.xiaoshabao.base.component.oss.core.StorageAble;

/**
 * 文件上传Factory
 */
@Component
public class OSSFactory {
    
    private Integer type=SpringContextHolder.getBean(SysConfig.class).getInteger("custom.oss.type");
    
    private static Map<String,StorageAble> ables=SpringContextHolder.getApplicationContext().getBeansOfType(StorageAble.class);

    /**
     * 创建存储对象功能接口
     * @return
     */
    public StorageAble build() {
    	
    	for (Map.Entry<String,StorageAble> entry : ables.entrySet()) {
            String name=entry.getKey();
            if((OSSConstant.ablePrefix+type).equals(name)) {
    			return entry.getValue();
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

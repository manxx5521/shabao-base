package com.xiaoshabao.base.component.oss;

import org.springframework.stereotype.Component;

import com.xiaoshabao.base.component.SpringContextHolder;
import com.xiaoshabao.base.component.SysConfig;
import com.xiaoshabao.base.component.oss.core.AliyunStorageService;
import com.xiaoshabao.base.component.oss.core.LocalStorageService;
import com.xiaoshabao.base.component.oss.core.StorageAble;

/**
 * 文件上传Factory
 *
 * @author ace
 */
@Component
public class OSSFactory {
    
    private Integer type=SpringContextHolder.getBean(SysConfig.class).getInteger("");

    /**
     * 创建存储对象功能接口
     * @return
     */
    public StorageAble build() {
        if (type.equals(OSSConstant.Type.LOCAL)) {
            return SpringContextHolder.getBean(LocalStorageService.class);
        } else if (type.equals(OSSConstant.Type.LOCAL)) {
            return SpringContextHolder.getBean(AliyunStorageService.class);
        } 
        return null;
    }

}

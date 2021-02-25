package com.xiaoshabao.base.entity;

/**
 * <p>
 * 系统枚举表
 * </p>
 * @author manxx
 * @since 2018-12-25
 */
public class StaticEntity {

    /**
     * 类型
     */
    private String typeId;
    /**
     * 模块，默认public所有可查
     */
    private String module;

    /**
     * 数据id
     */
    private String dataId;

    /**
     * 数据名字
     */
    private String dataName;
    
    /**排序*/
    private Integer orderNo;

    /**
     * 是否使用
     */
    private Integer used;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
    
    public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Override
    public String toString() {
        return "Static{" +
        "typeId=" + typeId +
        ", module=" + module +
        ", dataId=" + dataId +
        ", dataName=" + dataName +
        ", orderNo=" + orderNo +
        ", used=" + used +
        "}";
    }
}

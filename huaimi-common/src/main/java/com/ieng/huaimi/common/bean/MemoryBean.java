package com.ieng.huaimi.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemoryBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 内存总量
     */
    private String memory;
    /**
     * 内存使用量
     */
    private String memRam;
    /**
     * 使用中
     */
    private String memUsed;
    /**
     * 可用
     */
    private String memFrees;
    /**
     * 内存使用率
     */
    private String memoryUsage;

}

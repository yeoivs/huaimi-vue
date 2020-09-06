package com.ieng.huaimi.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class CpuBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * CPU用户使用率
     */
    private String user;
    /**
     * CPU系统使用率
     */
    private String sys;
    /**
     * CPU当前等待率
     */
    private String wait;
    /**
     * CPU当前错误率
     */
    private String nice;
    /**
     * CPU当前空闲率
     */
    private String idle;
    /**
     * CPU总的使用率
     */
    private String combined;

}

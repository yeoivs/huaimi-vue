package com.ieng.huaimi.common.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiskBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总容量
     */
    private String total;
    /**
     * 剩余大小
     */
    private String free;
    /**
     * 可用大小
     */
    private String avail;
    /**
     * 已使用大小
     */
    private String used;
    /**
     * 资源利用率
     */
    private String usePercent;

}

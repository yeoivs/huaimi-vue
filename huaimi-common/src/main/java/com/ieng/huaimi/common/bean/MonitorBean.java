package com.ieng.huaimi.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MonitorBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private CpuBean cpu;
    private DiskBean disk;
    private MemoryBean memory;
    private List<NetBean> nets;

}

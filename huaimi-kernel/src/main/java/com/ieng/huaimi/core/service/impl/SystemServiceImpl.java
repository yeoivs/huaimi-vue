package com.ieng.huaimi.core.service.impl;

import com.ieng.huaimi.common.bean.MonitorBean;
import com.ieng.huaimi.core.service.SystemService;
import org.springframework.stereotype.Service;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@Service
public class SystemServiceImpl implements SystemService {


    @Override
    public MonitorBean monitor(OperatingSystem operatingSystem, HardwareAbstractionLayer hardware) {
        MonitorBean monitorBean = new MonitorBean();
        GlobalMemory memory = hardware.getMemory();
        monitorBean.getMemory().setMemory(String.valueOf(memory.getTotal()));
        monitorBean.getMemory().setMemFrees(String.valueOf(memory.getAvailable()));
        return monitorBean;
    }
}

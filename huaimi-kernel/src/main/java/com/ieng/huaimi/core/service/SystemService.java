package com.ieng.huaimi.core.service;

import com.ieng.huaimi.common.bean.MonitorBean;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

public interface SystemService {
    MonitorBean monitor(OperatingSystem operatingSystem, HardwareAbstractionLayer hardware);
}

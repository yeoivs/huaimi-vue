package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.bean.MonitorBean;
import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.core.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@RestController
@RequestMapping("/system/info")
public class SystemController {
    @Autowired
    private SystemService systemService;

    @RequestMapping("")
    public ResultBody info(){
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

        MonitorBean monitor = systemService.monitor(operatingSystem, hardware);

        return null;
    }

}

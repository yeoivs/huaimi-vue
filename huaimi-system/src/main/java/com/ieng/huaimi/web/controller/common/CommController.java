package com.ieng.huaimi.web.controller.common;

import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.security.SecurityUtils;
import com.ieng.huaimi.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comm")
public class CommController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menu")
    public ResultBody menuList() {
        UserAccredit userAccredit = SecurityUtils.getUserPrincipal();
        return BUtils.data(permissionService.findMenu(userAccredit.getRoles()));
    }

}

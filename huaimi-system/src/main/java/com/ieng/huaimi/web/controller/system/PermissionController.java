package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.bean.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.security.SecurityUtils;
import com.ieng.huaimi.database.domain.Permission;
import com.ieng.huaimi.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/system/permission")
public class PermissionController{
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menu")
    public ResultBody menuList() {
        UserAccredit userAccredit = SecurityUtils.getUserPrincipal();
        return BUtils.data(permissionService.findMenu(userAccredit.getRoles()));
    }

    @PreAuthorize("@AC.permission('system:permission:list')")
    @GetMapping("/list")
    public ResultBody permissionList() {
        return BUtils.data(permissionService.findPermissionList());
    }

    @PreAuthorize("@AC.permission('system:permission:read')")
    @GetMapping("/{id}")
    public ResultBody readPermission(@PathVariable(name = "id") Long id) {
        return BUtils.data(permissionService.findPermissionById(id));
    }

    @PreAuthorize("@AC.permission('system:permission:save')")
    @PostMapping
    public ResultBody savePermission(@RequestBody Permission permission) {
        permission.setCreateTime(new Date());
        permission.setCreatedBy(SecurityUtils.getUsername());
        return BUtils.succeed(permissionService.savePermission(permission));
    }

    @PreAuthorize("@AC.permission('system:permission:edit')")
    @PutMapping
    public ResultBody editPermission(@RequestBody Permission permission) {
        permission.setModifyTime(new Date());
        permission.setModifiedBy(SecurityUtils.getUsername());
        return BUtils.succeed(permissionService.editPermission(permission));
    }

    @PreAuthorize("@AC.permission('system:permission:del')")
    @DeleteMapping
    public ResultBody delPermission(@RequestParam(name = "id") Long id) {
        return BUtils.succeed(permissionService.delPermission(id));
    }

}

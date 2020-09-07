package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.security.SecurityUtils;
import com.ieng.huaimi.database.domain.Permission;
import com.ieng.huaimi.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/system/permission")
public class PermissionController{
    @Autowired
    private PermissionService permissionService;

    @PreAuthorize("@rbac.permission('system:permission:list')")
    @RequestMapping("/list")
    public ResultBody permissionList(@RequestBody(required = false) Permission permission) {
        List<Permission> list;
        if(StringUtils.isEmpty(permission)){
            list = permissionService.findPermissionList();
        } else {
            list = permissionService.findPermissionCondition(permission);
        }
        return BUtils.data(list);
    }

    @PreAuthorize("@rbac.permission('system:permission:read')")
    @GetMapping("/{id}")
    public ResultBody readPermission(@PathVariable(name = "id") Long id) {
        return BUtils.data(permissionService.findPermissionById(id));
    }

    @PreAuthorize("@rbac.permission('system:permission:save')")
    @PostMapping
    public ResultBody savePermission(@RequestBody Permission permission) {
        permission.setCreateTime(new Date());
        permission.setCreatedBy(SecurityUtils.getUsername());
        permission.setModifyTime(null);
        permission.setModifiedBy(null);
        return BUtils.succeed(permissionService.savePermission(permission));
    }

    @PreAuthorize("@rbac.permission('system:permission:edit')")
    @PutMapping
    public ResultBody editPermission(@RequestBody Permission permission) {
        permission.setCreateTime(null);
        permission.setModifyTime(null);
        permission.setModifyTime(new Date());
        permission.setModifiedBy(SecurityUtils.getUsername());
        return BUtils.succeed(permissionService.editPermission(permission));
    }

    @PreAuthorize("@rbac.permission('system:permission:del')")
    @DeleteMapping
    public ResultBody delPermission(@RequestParam("id") Long id) {
        return BUtils.succeed(permissionService.delPermission(id));
    }

}

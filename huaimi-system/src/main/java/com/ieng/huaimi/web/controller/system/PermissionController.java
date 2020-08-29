package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.domain.ResultBody;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.security.SecurityHolder;
import com.ieng.huaimi.database.entity.Permission;
import com.ieng.huaimi.database.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/system/permission")
public class PermissionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/menu")
    public ResultBody menuList() {
        UserAccredit userAccredit = SecurityHolder.getUserPrincipal();
        return ResultBody.succeed(permissionService.findMenu(userAccredit.getRoles()));
    }

    @PreAuthorize("hasAuthority('system:permission:list')")
    @GetMapping("/list")
    public ResultBody permissionList() {
        return ResultBody.succeed(permissionService.findPermissionList());
    }

    @PreAuthorize("hasAuthority('system:permission:read')")
    @GetMapping("/{id}")
    public ResultBody readPermission(@PathVariable(name = "id") Long id) {
        return ResultBody.succeed(permissionService.findPermissionById(id));
    }

    @PreAuthorize("hasAuthority('system:permission:save')")
    @PostMapping
    public ResultBody savePermission(@RequestBody Permission permission) {
        try {
            permission.setCreateTime(new Date());
            permission.setCreatedBy(SecurityHolder.getUsername());
            permissionService.savePermission(permission);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResultBody.failed("添加角色信息失败");
        }
    }

    @PreAuthorize("hasAuthority('system:permission:edit')")
    @PutMapping
    public ResultBody editPermission(@RequestBody Permission permission) {
        try {
            permission.setModifyTime(new Date());
            permission.setModifiedBy(SecurityHolder.getUsername());
            permissionService.editPermission(permission);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResultBody.failed("更新权限信息失败");
        }
    }

    @PreAuthorize("hasAuthority('system:permission:del')")
    @DeleteMapping
    public ResultBody delPermission(@RequestParam(name = "id") Long id) {
        try {
            permissionService.delPermission(id);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
            return ResultBody.failed("删除权限失败");
        }
    }

}

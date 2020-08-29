package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.domain.ResultBody;
import com.ieng.huaimi.core.security.SecurityHolder;
import com.ieng.huaimi.database.entity.Role;
import com.ieng.huaimi.database.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('system:role:list')")
    @GetMapping("/list")
    public ResultBody roleList() {
        return ResultBody.succeed(roleService.findRoleList());
    }

    @PreAuthorize("hasAuthority('system:role:read')")
    @GetMapping("/{id}")
    public ResultBody readRole(@RequestParam("id") Long id) {
        return ResultBody.succeed(roleService.findRoleById(id));
    }

    @PreAuthorize("hasAuthority('system:role:save')")
    @PostMapping
    public ResultBody saveRole(@RequestBody Role role) {
        try {
            role.setCreateTime(new Date());
            role.setCreatedBy(SecurityHolder.getUsername());
            roleService.saveRole(role);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("添加角色失败");
        }
    }

    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping
    public ResultBody editRole(@RequestBody Role role) {
        try {
            role.setModifyTime(new Date());
            role.setModifiedBy(SecurityHolder.getUsername());
            roleService.editRole(role);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("更新角色信息失败");
        }
    }

    @PreAuthorize("hasAuthority('system:role:remove')")
    @DeleteMapping
    public ResultBody delRole(@RequestParam(name = "id") Long id) {
        try {
            roleService.delRole(id);
            return ResultBody.succeed(null);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("删除角色失败");
        }
    }

}

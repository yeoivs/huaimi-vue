package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.security.SecurityUtils;
import com.ieng.huaimi.database.domain.Role;
import com.ieng.huaimi.database.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PreAuthorize("@rbac.permission('system:role:list')")
    @GetMapping("/list")
    public ResultBody roleList() {
        return BUtils.data(roleService.findRoleList());
    }

    @PreAuthorize("@rbac.permission('system:role:read')")
    @GetMapping("/{id}")
    public ResultBody readRole(@PathVariable("id") Long id) {
        return BUtils.data(roleService.findRoleById(id));
    }

    @PreAuthorize("@rbac.permission('system:role:save')")
    @PostMapping
    public ResultBody saveRole(@RequestBody Role role) {
        role.setCreateTime(new Date());
        role.setCreatedBy(SecurityUtils.getUsername());
        return BUtils.succeed(roleService.saveRole(role));
    }

    @PreAuthorize("@rbac.permission('system:role:edit')")
    @PutMapping
    public ResultBody editRole(@RequestBody Role role) {
        role.setModifyTime(new Date());
        role.setModifiedBy(SecurityUtils.getUsername());
        return BUtils.succeed(roleService.editRole(role));
    }

    @PreAuthorize("@rbac.permission('system:role:del')")
    @DeleteMapping
    public ResultBody delRole(@RequestParam(name = "id") Long id) {
        return BUtils.succeed(roleService.delRole(id));
    }

}

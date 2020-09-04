package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.bean.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.security.SecurityUtils;
import com.ieng.huaimi.database.domain.Role;
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
        return BUtils.data(roleService.findRoleList());
    }

    @PreAuthorize("hasAuthority('system:role:read')")
    @GetMapping("/{id}")
    public ResultBody readRole(@PathVariable("id") Long id) {
        return BUtils.data(roleService.findRoleById(id));
    }

    @PreAuthorize("hasAuthority('system:role:save')")
    @PostMapping
    public ResultBody saveRole(@RequestBody Role role) {
        role.setCreateTime(new Date());
        role.setCreatedBy(SecurityUtils.getUsername());
        return BUtils.succeed(roleService.saveRole(role));
    }

    @PreAuthorize("hasAuthority('system:role:edit')")
    @PutMapping
    public ResultBody editRole(@RequestBody Role role) {
        role.setModifyTime(new Date());
        role.setModifiedBy(SecurityUtils.getUsername());
        return BUtils.succeed(roleService.editRole(role));
    }

    @PreAuthorize("hasAuthority('system:role:del')")
    @DeleteMapping
    public ResultBody delRole(@RequestParam(name = "id") Long id) {
        return BUtils.succeed(roleService.delRole(id));
    }

}

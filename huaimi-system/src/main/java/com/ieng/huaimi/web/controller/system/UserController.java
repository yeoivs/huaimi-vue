package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.view.PageNature;
import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.security.PasswordUtil;
import com.ieng.huaimi.database.domain.User;
import com.ieng.huaimi.database.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("@rbac.permission('system:user:list')")
    @RequestMapping("/list")
    public ResultBody userList(@RequestBody PageNature<User> pageNature){
        return BUtils.data(userService.findPages(pageNature));
    }

    @PreAuthorize("@rbac.permission('system:user:read')")
    @GetMapping("/{id}")
    public ResultBody readUser(@PathVariable(name = "id") Long id){
        return BUtils.data(userService.findUserById(id));
    }

    @PreAuthorize("@rbac.permission('system:user:save')")
    @PostMapping
    public ResultBody saveUser(@RequestBody @Validated User user){
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
        }
        return BUtils.succeed(userService.saveUser(user));
    }

    @PreAuthorize("@rbac.permission('system:user:edit')")
    @PutMapping
    public ResultBody editUser(@RequestBody User user){
        return BUtils.succeed(userService.editUser(user));
    }

    @PreAuthorize("@rbac.permission('system:user:del')")
    @DeleteMapping
    public ResultBody delUser(@RequestBody User user){
        return BUtils.succeed(userService.delUser(user));
    }

}

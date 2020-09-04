package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.bean.PageNature;
import com.ieng.huaimi.common.bean.ResultBody;
import com.ieng.huaimi.common.utils.BUtils;
import com.ieng.huaimi.core.security.PasswordUtil;
import com.ieng.huaimi.database.domain.User;
import com.ieng.huaimi.database.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('system:user:list')")
    @GetMapping("/list")
    public ResultBody userList(@RequestBody PageNature<User> pageNature){
        LOGGER.debug(pageNature.toString());
        return BUtils.data(userService.findPages(pageNature));
    }

    @PreAuthorize("hasAuthority('system:user:read')")
    @GetMapping("/{id}")
    public ResultBody readUser(@PathVariable(name = "id") Long id){
        return BUtils.data(userService.findUserById(id));
    }

    @PreAuthorize("hasAuthority('system:user:save')")
    @PostMapping
    public ResultBody saveUser(@RequestBody @Validated User user){
        LOGGER.debug(user.toString());
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
        }
        return BUtils.succeed(userService.saveUser(user));
    }

    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public ResultBody editUser(@RequestBody User user){
        LOGGER.debug(user.toString());
        return BUtils.succeed(userService.editUser(user));
    }

    @PreAuthorize("hasAuthority('system:user:del')")
    @DeleteMapping
    public ResultBody delUser(@RequestBody User user){
        return BUtils.succeed(userService.delUser(user));
    }

}

package com.ieng.huaimi.web.controller.system;

import com.ieng.huaimi.common.domain.PageNature;
import com.ieng.huaimi.common.domain.ResultBody;
import com.ieng.huaimi.database.entity.User;
import com.ieng.huaimi.database.service.UserService;
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
        return ResultBody.succeed(userService.findPages(pageNature));
    }

    @PreAuthorize("hasAuthority('system:user:read')")
    @GetMapping("/{id}")
    public ResultBody readUser(@PathVariable(name = "id") Long id){
        return ResultBody.succeed(userService.findUserById(id));
    }

    @PreAuthorize("hasAuthority('system:user:save')")
    @PostMapping
    public ResultBody saveUser(@RequestBody @Validated User user){
        LOGGER.debug(user.toString());
        try{
            userService.saveUser(user);
            return ResultBody.succeed(null);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("添加用户失败");
        }
    }

    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public ResultBody editUser(@RequestBody User user){
        LOGGER.debug(user.toString());
        try{
            userService.editUser(user);
            return ResultBody.succeed(null);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("更新用户信息失败");
        }
    }

    @PreAuthorize("hasAuthority('system:user:remove')")
    @DeleteMapping
    public ResultBody delUser(@RequestBody User user){
        LOGGER.debug(user.toString());
        try{
            userService.delUser(user);
            return ResultBody.succeed(null);
        } catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            return ResultBody.failed("删除用户失败");
        }
    }

}

package com.ieng.huaimi.core.service.impl;

import com.ieng.huaimi.common.enums.AccountStatus;
import com.ieng.huaimi.common.enums.RoleType;
import com.ieng.huaimi.common.exception.AccountException;
import com.ieng.huaimi.common.utils.StringUtil;
import com.ieng.huaimi.database.entity.User;
import com.ieng.huaimi.database.service.PermissionService;
import com.ieng.huaimi.database.service.RoleService;
import com.ieng.huaimi.database.service.UserService;
import com.ieng.huaimi.core.bean.UserAccredit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        } else if (StringUtil.isMatch(AccountStatus.DELETE.getCode(), user.getLockState())) {
            throw new AccountException("账号已被删除");
        } else if (StringUtil.isMatch(AccountStatus.DISABLE.getCode(), user.getLockState())) {
            throw new AccountException("账号已被禁用");
        }
        return authPrincipal(user);
    }

    private UserAccredit authPrincipal(User user) {
        UserAccredit userAccredit = new UserAccredit();
        userAccredit.setUser(user);

        Set<String> roles = roleService.findRoleByUsername(user.getUsername());

        Set<String> permissions = new LinkedHashSet<>();

        //判断用户是否拥有角色 否-默认普通用户
        if (!roles.isEmpty()) {
            for (String role : roles) {
                if (RoleType.SYSDBA.getName().equals(role)) {
                    roles.add(RoleType.SYSDBA.getName());
                    permissions.addAll(permissionService.findSetPermissions());
                    break;
                } else {
                    permissions.addAll(permissionService.findPermissionsByRole(role));
                }
            }
        } else {
            roles.add(RoleType.USER.getName());
            permissions.addAll(permissionService.findPermissionsByRole(RoleType.USER.getName()));
        }

        userAccredit.setRoles(roles);
        userAccredit.setPermissions(permissions);

        return userAccredit;
    }

}

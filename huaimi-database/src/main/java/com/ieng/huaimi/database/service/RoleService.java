package com.ieng.huaimi.database.service;

import com.ieng.huaimi.database.domain.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Set<String> findRoleByUsername(String username);

    Role findRoleById(Long id);

    List<Role> findRoleList();

    int saveRole(Role role);

    int editRole(Role role);

    int delRole(Long id);

}

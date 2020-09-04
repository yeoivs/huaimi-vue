package com.ieng.huaimi.database.service;

import com.ieng.huaimi.database.domain.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    Set<String> findSetPermissions();

    Set<String> findPermissions(String username);

    Set<String> findPermissionsByRole(String name);

    Permission findPermissionById(Long id);

    List<Permission> findPermissionList();

    List<Permission> findMenu(Set<String> roles);

    int savePermission(Permission permission);

    int editPermission(Permission permission);

    int delPermission(Long id);


}

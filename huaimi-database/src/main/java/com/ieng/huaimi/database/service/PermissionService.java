package com.ieng.huaimi.database.service;

import com.ieng.huaimi.database.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    Set<String> findSetPermissions();

    Set<String> findPermissions(String username);

    Set<String> findPermissionsByRole(String name);

    Permission findPermissionById(Long id);

    List<Permission> findPermissionList();

    int savePermission(Permission permission);

    int editPermission(Permission permission);

    int delPermission(Long id);

    List<Permission> findMenu(Set<String> roles);

}

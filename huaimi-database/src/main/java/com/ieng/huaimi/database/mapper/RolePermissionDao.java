package com.ieng.huaimi.database.mapper;

import org.apache.ibatis.annotations.Param;

public interface RolePermissionDao {
    int addRolePermissionIds(@Param("roleId") Long id, @Param("permissionIds") Long[] permissionIds);

    int delRolePermissionByRoleId(@Param("roleId") Long id);

    Long[] queryIdsByRoleId(Long id);

    Long[] queryIdsAll();
}

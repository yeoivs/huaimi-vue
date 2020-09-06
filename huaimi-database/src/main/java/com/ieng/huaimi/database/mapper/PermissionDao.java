package com.ieng.huaimi.database.mapper;

import com.ieng.huaimi.database.domain.Permission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface PermissionDao extends Mapper<Permission> {
    Set<String> querySetPermissionByUsername(String username);

    Set<String> querySetPermissionByRole(String name);

    List<Permission> queryMenuByRole(String role);

    List<Permission> queryChildren(Long parentId);

}

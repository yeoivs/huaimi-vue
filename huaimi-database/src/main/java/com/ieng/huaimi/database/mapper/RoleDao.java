package com.ieng.huaimi.database.mapper;

import com.ieng.huaimi.database.domain.Role;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

public interface RoleDao extends Mapper<Role> {

    Set<String> querySetRoleByUsername(String username);

    List<Role> queryRoleByUserId(Long id);

}

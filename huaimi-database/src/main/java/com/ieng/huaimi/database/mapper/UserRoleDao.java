package com.ieng.huaimi.database.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserRoleDao {

    void addIdsByUserId(@Param("userId") Long userId, @Param("roleIds")  Long[] roleIds);

}

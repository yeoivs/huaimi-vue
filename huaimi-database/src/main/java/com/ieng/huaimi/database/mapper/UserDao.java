package com.ieng.huaimi.database.mapper;

import com.ieng.huaimi.database.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {
    List<User> queryUserByCondition(User condition);
}

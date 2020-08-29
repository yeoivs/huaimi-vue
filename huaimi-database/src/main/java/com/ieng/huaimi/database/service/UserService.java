package com.ieng.huaimi.database.service;

import com.github.pagehelper.PageInfo;
import com.ieng.huaimi.common.domain.PageNature;
import com.ieng.huaimi.database.entity.Permission;
import com.ieng.huaimi.database.entity.User;

import java.util.Set;

public interface UserService {
    User findUserById(Long id);

    User findUserByUsername(String username);

    PageInfo<User> findPages(PageNature<User> pageNature);

    void saveUser(User user);

    void editUser(User user);

    void delUser(User user);

}

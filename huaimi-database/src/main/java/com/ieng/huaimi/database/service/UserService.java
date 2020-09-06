package com.ieng.huaimi.database.service;

import com.github.pagehelper.PageInfo;
import com.ieng.huaimi.common.view.PageNature;
import com.ieng.huaimi.database.domain.User;

public interface UserService {
    User findUserById(Long id);

    User findUserByUsername(String username);

    PageInfo<User> findPages(PageNature<User> pageNature);

    int saveUser(User user);

    int editUser(User user);

    int delUser(User user);

}

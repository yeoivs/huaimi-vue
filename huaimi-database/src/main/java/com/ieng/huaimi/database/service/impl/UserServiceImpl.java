package com.ieng.huaimi.database.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ieng.huaimi.common.view.PageNature;
import com.ieng.huaimi.common.enums.RoleType;
import com.ieng.huaimi.database.domain.Role;
import com.ieng.huaimi.database.domain.User;
import com.ieng.huaimi.database.mapper.RoleDao;
import com.ieng.huaimi.database.mapper.UserDao;
import com.ieng.huaimi.database.mapper.UserRoleDao;
import com.ieng.huaimi.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public User findUserById(Long id) {
        User user = userDao.selectByPrimaryKey(id);
        List<Role> roleList = roleDao.queryRoleByUserId(user.getId());
        if(roleList.isEmpty()){
            Role role = new Role();
            role.setPerms(RoleType.USER.getName());
            roleList.add(roleDao.selectOne(role));
        }
        user.setRoles(roleList);
        return user;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.selectOne(user);
    }

    @Override
    public PageInfo<User> findPages(PageNature<User> pageNature) {
        PageHelper.startPage(pageNature.getPage(), pageNature.getSize());
        List<User> userList = userDao.queryUserByCondition(pageNature.getCondition());
        return new PageInfo<>(userList);
    }

    @Transactional
    @Override
    public int saveUser(User user) {
        int count = userDao.insertSelective(user);
        userRoleDao.addIdsByUserId(user.getId(), user.getRoleIds());
        return count;
    }

    @Transactional
    @Override
    public int editUser(User user) {
        return userDao.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public int delUser(User user) {
        Example example = new Example(User.class);
        example.or().andEqualTo("id", user.getId());
        example.or().andBetween("createTime", user.getCreateTime(), user.getModifyTime());
        return userDao.deleteByExample(example);
    }
}

package com.ieng.huaimi.database.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ieng.huaimi.common.domain.PageNature;
import com.ieng.huaimi.common.enums.RoleType;
import com.ieng.huaimi.common.utils.PasswordUtil;
import com.ieng.huaimi.common.utils.StringUtil;
import com.ieng.huaimi.database.entity.Role;
import com.ieng.huaimi.database.entity.User;
import com.ieng.huaimi.database.mapper.RoleDao;
import com.ieng.huaimi.database.mapper.UserDao;
import com.ieng.huaimi.database.mapper.UserRoleDao;
import com.ieng.huaimi.database.service.UserService;
import org.apache.commons.lang3.StringUtils;
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
    public void saveUser(User user) {
        if(StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(PasswordUtil.encode(user.getPassword()));
        }
        userDao.insertSelective(user);
        userRoleDao.addIdsByUserId(user.getId(), user.getRoleIds());
    }

    @Transactional
    @Override
    public void editUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @Transactional
    @Override
    public void delUser(User user) {
        Example example = new Example(User.class);
        example.or().andEqualTo("id", user.getId());
        example.or().andBetween("createTime", user.getCreateTime(), user.getModifyTime());
        userDao.deleteByExample(example);
    }
}

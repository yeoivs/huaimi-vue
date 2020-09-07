package com.ieng.huaimi.database.service.impl;

import com.ieng.huaimi.common.exception.ServiceCode;
import com.ieng.huaimi.common.exception.ServiceException;
import com.ieng.huaimi.common.utils.string.StringUtil;
import com.ieng.huaimi.database.domain.Permission;
import com.ieng.huaimi.database.mapper.PermissionDao;
import com.ieng.huaimi.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Set<String> findPermissions(String username) {
        return permissionDao.querySetPermissionByUsername(username);
    }

    @Override
    public Set<String> findPermissionsByRole(String name) {
        return permissionDao.querySetPermissionByRole(name);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> findPermissionList() {
        return permissionDao.selectAll();
    }

    @Override
    public List<Permission> findPermissionCondition(Permission permission) {
        Example example = new Example(Permission.class);
        example.and().andEqualTo("type", permission.getType());
        example.and().andEqualTo("status", permission.getStatus());
        if(!StringUtils.isEmpty(permission.getPerms())){
            example.and().andLike("perms", String.format("%s%s%s", "%", permission.getPerms(), "%"));
        }
        return permissionDao.selectByExample(example);
    }

    @Override
    public List<Permission> findMenu(Set<String> roles) {
        List<Permission> menus = new ArrayList<>();
        if(!StringUtil.isSetNull(roles)){
            for (String role : roles) {
                menus.addAll(permissionDao.queryMenuByRole(role));
            }
        }
        return menus;
    }

    @Transactional
    @Override
    public int savePermission(Permission permission) {
        return permissionDao.insertSelective(permission);
    }

    @Transactional
    @Override
    public int editPermission(Permission permission) {
        return permissionDao.updateByPrimaryKeySelective(permission);
    }

    @Transactional
    @Override
    public int delPermission(Long id) {
        if(id <= 16){
            throw new ServiceException(ServiceCode.ADMINISTRATOR_FORBID);
        }
        return permissionDao.deleteByPrimaryKey(id);
    }

}

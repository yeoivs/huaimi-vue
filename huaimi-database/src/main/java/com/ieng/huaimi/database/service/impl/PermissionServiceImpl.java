package com.ieng.huaimi.database.service.impl;

import com.ieng.huaimi.common.utils.string.StringUtil;
import com.ieng.huaimi.database.domain.Permission;
import com.ieng.huaimi.database.mapper.PermissionDao;
import com.ieng.huaimi.database.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Set<String> findSetPermissions() {
        return permissionDao.querySetPermissions();
    }

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
        return permissionDao.deleteByPrimaryKey(id);
    }


}

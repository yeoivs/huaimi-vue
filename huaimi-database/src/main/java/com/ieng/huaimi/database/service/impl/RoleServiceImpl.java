package com.ieng.huaimi.database.service.impl;

import com.ieng.huaimi.database.entity.Role;
import com.ieng.huaimi.database.mapper.RoleDao;
import com.ieng.huaimi.database.mapper.RolePermissionDao;
import com.ieng.huaimi.database.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public Set<String> findRoleByUsername(String username) {
        return roleDao.querySetRoleByUsername(username);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDao.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> findRoleList() {
        return roleDao.selectAll();
    }

    @Transactional
    @Override
    public int saveRole(Role role) {
        roleDao.insertSelective(role);
        return insertRolePermission(role);
    }

    @Transactional
    @Override
    public int editRole(Role role) {
        roleDao.updateByPrimaryKeySelective(role);
        rolePermissionDao.delRolePermissionByRoleId(role.getId());
        return insertRolePermission(role);
    }

    @Transactional
    @Override
    public int delRole(Long id) {
        return roleDao.deleteByPrimaryKey(id);
    }

    private int insertRolePermission(Role role){
        if (role != null && role.getPermissionIds() != null) {
            return rolePermissionDao.addRolePermissionIds(role.getId(), role.getPermissionIds());
        }
        return 0;
    }

}

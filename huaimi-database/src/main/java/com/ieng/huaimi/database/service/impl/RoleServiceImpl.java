package com.ieng.huaimi.database.service.impl;

import com.ieng.huaimi.common.enums.RoleType;
import com.ieng.huaimi.common.exception.ServiceException;
import com.ieng.huaimi.common.exception.ServiceCode;
import com.ieng.huaimi.database.domain.Role;
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
        Role role = roleDao.selectByPrimaryKey(id);
        if(role != null){
            if(RoleType.SYSDBA.getName().equals(role.getPerms())){
                role.setPermissionIds(rolePermissionDao.queryIdsAll());
            }else{
                role.setPermissionIds(rolePermissionDao.queryIdsByRoleId(role.getId()));
            }
        }
        return role;
    }

    @Override
    public List<Role> findRoleList() {
        return roleDao.selectAll();
    }

    @Transactional
    @Override
    public int saveRole(Role role) {
        int count = roleDao.insertSelective(role);
        insertRolePermission(role);
        return count;
    }

    @Transactional
    @Override
    public int editRole(Role role) {
        int count = 0;
        if(role != null){
            if(RoleType.SYSDBA.getName().equals(role.getPerms())){
                throw new ServiceException(ServiceCode.ADMINISTRATOR_FORBID);
            }
            count = roleDao.updateByPrimaryKeySelective(role);
            rolePermissionDao.delRolePermissionByRoleId(role.getId());
            insertRolePermission(role);
        }
        return count;
    }

    @Transactional
    @Override
    public int delRole(Long id) {

        if(id != null && String.valueOf(RoleType.SYSDBA.getCode()).equals(id.toString())){
            throw new ServiceException(ServiceCode.ADMINISTRATOR_FORBID);
        }

        return roleDao.deleteByPrimaryKey(id);
    }

    private void insertRolePermission(Role role){
        if (role != null && role.getPermissionIds() != null) {
            rolePermissionDao.addRolePermissionIds(role.getId(), role.getPermissionIds());
        }
    }

}

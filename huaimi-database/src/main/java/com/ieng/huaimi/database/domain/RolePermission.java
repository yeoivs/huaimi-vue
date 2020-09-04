package com.ieng.huaimi.database.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "sys_role_permission")
public class RolePermission implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;
    private Long permissionId;

}

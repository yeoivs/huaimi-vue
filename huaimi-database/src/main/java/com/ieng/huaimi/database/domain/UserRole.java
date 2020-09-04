package com.ieng.huaimi.database.domain;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "sys_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long roleId;

}

package com.ieng.huaimi.database.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "sys_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String perms;
    private Character status;
    private String remark;
    private Date createTime;
    private String createdBy;
    private Date modifyTime;
    private String modifiedBy;

    private Long[] permissionIds;

}

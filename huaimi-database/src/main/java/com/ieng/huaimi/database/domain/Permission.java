package com.ieng.huaimi.database.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "sys_permission")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long parentId;
    private String name;
    private String perms;
    private Character type;
    private String path;
    private String icon;
    private String method;
    private Integer sort;
    private Character status;
    private String remark;
    private Date createTime;
    private String createdBy;
    private Date modifyTime;
    private String modifiedBy;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Permission> children;

}

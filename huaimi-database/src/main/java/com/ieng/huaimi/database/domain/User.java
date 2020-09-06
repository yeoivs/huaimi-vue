package com.ieng.huaimi.database.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String phone;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonIgnore
    private String salt;
    private Character lockState;
    private Date createTime;
    private String createdBy;
    private Date modifyTime;
    private String modifiedBy;
    @LogicDelete
    private Character deleted;

    private List<Role> roles;
    private Long[] roleIds;


}

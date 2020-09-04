package com.ieng.huaimi.database.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 登录
    */
@Data
public class Token implements Serializable {
    /**
    * 用户ID
    */
    private Long userId;

    /**
    * token
    */
    private String token;

    /**
    * 过期时间
    */
    private Date expireTime;

    /**
    * 更新时间
    */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
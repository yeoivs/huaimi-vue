package com.ieng.huaimi.core.security;

import com.ieng.huaimi.core.bean.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHolder {

    /**
     * 获取当前登录的用户
     * @return UserPrincipal
     */
    public static UserPrincipal getUserPrincipal(){
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUsername(){
        return getUserPrincipal().getUsername();
    }

}

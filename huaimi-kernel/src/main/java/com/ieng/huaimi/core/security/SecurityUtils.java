package com.ieng.huaimi.core.security;

import com.ieng.huaimi.core.bean.UserAccredit;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 获取当前登录的用户
     * @return UserPrincipal
     */
    public static UserAccredit getUserPrincipal(){
        return (UserAccredit) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUsername(){
        return getUserPrincipal().getUsername();
    }

}

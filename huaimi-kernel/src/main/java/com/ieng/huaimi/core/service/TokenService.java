package com.ieng.huaimi.core.service;

import com.ieng.huaimi.core.bean.UserPrincipal;

import javax.servlet.http.HttpServletRequest;


public interface TokenService {

    String createToken(UserPrincipal userPrincipal);

    UserPrincipal getPrincipal(HttpServletRequest request);

    void delToken(String uuid);

    void refreshToken(UserPrincipal userPrincipal);

}

package com.ieng.huaimi.core.service;

import com.ieng.huaimi.core.bean.UserAccredit;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    String createToken(UserAccredit userAccredit);

    UserAccredit getPrincipal(HttpServletRequest request);

    void delToken(String uuid);

    void refreshToken(UserAccredit userAccredit);
}

package com.ieng.huaimi.core.service.impl;

import com.ieng.huaimi.common.utils.context.ServletContextHolder;
import com.ieng.huaimi.common.utils.StringUtil;
import com.ieng.huaimi.core.bean.UserAccredit;
import com.ieng.huaimi.core.service.AccessService;
import com.ieng.huaimi.core.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Service("ACCEPT")
public class AccessServiceImpl implements AccessService {
    private static final String SPLIT_POINT = ",";
    @Autowired
    private TokenService tokenService;

    @Override
    public boolean hasPermission(Authentication authentication) {
        HttpServletRequest request = ServletContextHolder.getRequest();
        Object o = authentication.getPrincipal();
        if (request != null && o instanceof UserAccredit) {
            UserAccredit userAccredit = (UserAccredit) o;
            Collection<? extends GrantedAuthority> authorities = userAccredit.getAuthorities();
            return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }

    @Override
    public boolean hasAnyPermission(String... name) {
        if(name != null){
            for (String s : name) {
                if (hasPermission(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(String name) {
        UserAccredit principal = tokenService.getPrincipal(ServletContextHolder.getRequest());
        if(principal != null && !StringUtil.isSetNull(principal.getPermissions())){
            return principal.getPermissions().contains(name);
        }
        return false;
    }

    @Override
    public boolean hasAnyRoles(String name) {
        if (name != null && !"".equals(name.trim())){
            String[] strings = name.split(SPLIT_POINT);
            for (String s : strings) {
                if (hasRole(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasAnyRole(String... name) {
        if(name != null){
            for (String s : name) {
                if (hasRole(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasRole(String name) {
        UserAccredit principal = tokenService.getPrincipal(ServletContextHolder.getRequest());
        if (principal != null && !StringUtil.isSetNull(principal.getRoles())) {
            return principal.getRoles().contains(name);
        }
        return false;
    }
}

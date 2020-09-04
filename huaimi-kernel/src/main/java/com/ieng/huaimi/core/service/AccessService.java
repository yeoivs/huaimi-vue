package com.ieng.huaimi.core.service;

import org.springframework.security.core.Authentication;

public interface AccessService {

    boolean hasURI(Authentication authentication);

    boolean hasAnyPermission(String... name);

    boolean permission(String name);

    boolean hasAnyRoles(String name);

    boolean hasAnyRole(String... name);

    boolean hasRole(String name);

}
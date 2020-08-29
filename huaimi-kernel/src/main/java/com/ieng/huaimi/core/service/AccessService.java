package com.ieng.huaimi.core.service;

import org.springframework.security.core.Authentication;

public interface AccessService {

    boolean hasPermission(Authentication authentication);

    boolean hasAnyPermission(String... name);

    boolean hasPermission(String name);

    boolean hasAnyRoles(String name);

    boolean hasAnyRole(String... name);

    boolean hasRole(String name);

}
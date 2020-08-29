package com.ieng.huaimi.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ieng.huaimi.database.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String token;
    private long accessTime;
    private long expireTime;
    private Set<String> roles;
    private Set<String> permissions;
    private User user;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        permissions.addAll(roles.stream().map(s -> "ROLE_" + s).collect(Collectors.toSet()));
        return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    //账户是否未过期,过期无法验证
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否解锁,锁定的用户无法进行身份验证
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用,禁用的用户不能身份验证
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}

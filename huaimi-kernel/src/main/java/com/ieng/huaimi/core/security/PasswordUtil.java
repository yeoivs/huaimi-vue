package com.ieng.huaimi.core.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

    public static String encode(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

}

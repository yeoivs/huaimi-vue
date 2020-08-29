package com.ieng.huaimi.common.utils;

import java.util.UUID;

public class IDUtil {

    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    public static String uuid(){
        return randomUUID().replace("-", "");
    }

}

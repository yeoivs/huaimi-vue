package com.ieng.huaimi.common.utils.string;

import java.util.UUID;

public class IDUtils {

    public static String randomUUID(){
        return UUID.randomUUID().toString();
    }

    public static String uuid(){
        return randomUUID().replace("-", "");
    }

}

package com.ieng.huaimi.common.utils;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isMatch(Character n1, Character n2){
        return n1.equals(n2);
    }

    public static boolean isNull(Object o){
        return o == null;
    }

    public static boolean isSetNull(Set<?> set){
        return set == null || set.isEmpty();
    }

    public static boolean isEmail(String email){
        Pattern pattern = Pattern.compile("\\w[-\\w.+]*@([a-zA-Z0-9][-a-zA-Z0-9+\\.])+[a-zA-Z]{2,14}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}

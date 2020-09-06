package com.test;

import com.ieng.huaimi.common.view.ResultBody;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RefTest {

    @Test
    public void test1() throws Exception {
        ResultBody body = new ResultBody();
        body.setCode(1);
        body.setMsg("111");
        body.setData(new Object());

        Class<? extends ResultBody> aClass = body.getClass();

        for (Field field : aClass.getDeclaredFields()) {
            if("serialVersionUID".equals(field.getName())) continue;
            field.setAccessible(true);
            String name = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            Method method = aClass.getMethod(name);

            Object invoke = method.invoke(body);
            System.out.println(invoke);

        }


    }


}

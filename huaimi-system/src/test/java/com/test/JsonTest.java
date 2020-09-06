package com.test;

import com.ieng.huaimi.common.view.PageNature;
import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.utils.string.JSONUtils;
import org.junit.Test;

public class JsonTest {

    @Test
    public void test1(){
        PageNature<ResultBody> nature = new PageNature<>();
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(2000);
        resultBody.setMsg("aaaaaaaaaaaaaaaaaaaa");
        nature.setCondition(resultBody);

        String json = JSONUtils.toJsonString(nature);

        long startTime = System.currentTimeMillis();
        System.out.println("jackson to json->\t" + JSONUtils.jsonToObject(json, PageNature.class));
        long endTime = System.currentTimeMillis();
        System.out.println("jackson to json time->\t" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("gson to json ->\t" + JSONUtils.gsonToObject(json, PageNature.class));
        endTime = System.currentTimeMillis();
        System.out.println("gson to json time->\t" + (endTime - startTime));


    }


    @Test
    public void jsonFormat(){
        String json = "{\"code\":0,\"msg\":\"success\",\"data\":{\"token\":\"..VUL-\"}}";


    }


}

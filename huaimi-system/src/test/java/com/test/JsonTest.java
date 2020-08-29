package com.test;

import com.ieng.huaimi.common.domain.PageNature;
import com.ieng.huaimi.common.domain.ResultBody;
import com.ieng.huaimi.common.utils.JSONUtil;
import org.junit.Test;

public class JsonTest {

    @Test
    public void test1(){
        PageNature<ResultBody<Object>> nature = new PageNature<>();
        ResultBody<Object> resultBody = new ResultBody<>();
        resultBody.setCode(2000);
        resultBody.setMessage("aaaaaaaaaaaaaaaaaaaa");
        nature.setCondition(resultBody);

        String json = JSONUtil.toJsonString(nature);

        long startTime = System.currentTimeMillis();
        System.out.println("jackson to json->\t" + JSONUtil.jsonToObject(json, PageNature.class));
        long endTime = System.currentTimeMillis();
        System.out.println("jackson to json time->\t" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println("gson to json ->\t" + JSONUtil.gsonToObject(json, PageNature.class));
        endTime = System.currentTimeMillis();
        System.out.println("gson to json time->\t" + (endTime - startTime));
;

    }


}

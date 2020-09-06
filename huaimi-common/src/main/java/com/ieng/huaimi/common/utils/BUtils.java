package com.ieng.huaimi.common.utils;

import com.ieng.huaimi.common.view.ResultBody;
import com.ieng.huaimi.common.enums.Status;

public class BUtils {

    public static ResultBody success(){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(Status.SUCCEED.status());
        resultBody.setMsg(Status.SUCCEED.message());
        return resultBody;
    }

    public static ResultBody data(Object data){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(Status.SUCCEED.status());
        resultBody.setMsg(Status.SUCCEED.message());
        resultBody.setData(data);
        return resultBody;
    }

    public static ResultBody succeed(Integer count){
        ResultBody resultBody = new ResultBody();
        resultBody.setCode(Status.SUCCEED.status());
        resultBody.setMsg(Status.SUCCEED.message());
        resultBody.setCount(count);
        return resultBody;
    }

    public static ResultBody error(String msg){
        return new ResultBody(Status.FAILED.status(), msg);
    }

    public static ResultBody error(int status, String msg){
        return new ResultBody(status, msg);
    }

    public static ResultBody failed(){
        return new ResultBody(Status.FAILED.status(), Status.FAILED.message());
    }

}

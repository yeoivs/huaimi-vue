package com.ieng.huaimi.common.domain;

import com.ieng.huaimi.common.enums.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultBody<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String message;
    private T data;

    public ResultBody(Integer code, String message){
        this(code, message, null);
    }

    public static <T> ResultBody<T> succeed(T data){
        return effect(ServiceStatus.SUCCEED, data);
    }

    public static <T> ResultBody<T> failed(String message){
        return effect(ServiceStatus.FAILED, message, null);
    }

    public static <T> ResultBody<T> effect(ServiceStatus status){
        return new ResultBody<>(status.status(), status.getMsg());
    }

    public static <T> ResultBody<T> effect(ServiceStatus status, T data){
        return new ResultBody<>(status.status(), status.getMsg(), data);
    }

    public static <T> ResultBody<T> effect(ServiceStatus status, String message, T data){
        return new ResultBody<>(status.status(), message, data);
    }

}

package com.ieng.huaimi.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBody implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;
    private Object data;

    public ResultBody(){}

    public ResultBody(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

}

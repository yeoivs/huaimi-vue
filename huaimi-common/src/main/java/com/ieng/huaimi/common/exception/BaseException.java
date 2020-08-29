package com.ieng.huaimi.common.exception;

public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BaseException(){
        super();
    }

    public BaseException(String message){
        super(message);
    }
}
package com.ieng.huaimi.common.exception;

import com.ieng.huaimi.common.exception.face.BaseCodeInterface;

public class ServiceException extends BaseException{
    private static final long serialVersionUID = 1L;

    protected int errorCode;
    protected String errorMsg;

    public ServiceException(){
        super();
    }

    public ServiceException(String message){
        super(message);
        this.errorMsg = message;
    }

    public ServiceException(BaseCodeInterface baseCodeInterface){
        this.errorCode = baseCodeInterface.getResultCode();
        this.errorMsg = baseCodeInterface.getResultMsg();
    }

    public ServiceException(BaseCodeInterface baseCodeInterface, String message){
        this.errorCode = baseCodeInterface.getResultCode();
        this.errorMsg = message;
    }

}

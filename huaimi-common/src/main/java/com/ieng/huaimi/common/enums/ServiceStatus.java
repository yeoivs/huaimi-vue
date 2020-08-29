package com.ieng.huaimi.common.enums;

public enum ServiceStatus {
    SUCCEED(200, "success"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    FAILED(-1, "be defeated");

    private final int code;
    private final String msg;

    ServiceStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int status(){
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

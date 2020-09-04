package com.ieng.huaimi.common.enums;

public enum Status {
    SUCCEED(0, "success"),
    FAILED(-1, "error");

    private final int code;
    private final String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int status(){
        return code;
    }

    public String message() {
        return msg;
    }


}

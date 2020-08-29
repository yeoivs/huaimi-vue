package com.ieng.huaimi.common.enums;

public enum AccountStatus {
    DELETE('2'),
    DISABLE('0');

    private final char code;

    AccountStatus(char code){
        this.code = code;
    }

    public char getCode() {
        return code;
    }
}

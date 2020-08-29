package com.ieng.huaimi.common.enums;

public enum RoleType {
    SYSDBA('1', "SYSDBA"),
    ADMIN('2', "ADMIN"),
    USER('3', "USER");

    private final char type;
    private final String name;

    RoleType(char type, String name){
        this.type = type;
        this.name = name;
    }

    public char getCode() {
        return type;
    }

    public String getName() {
        return name;
    }
}

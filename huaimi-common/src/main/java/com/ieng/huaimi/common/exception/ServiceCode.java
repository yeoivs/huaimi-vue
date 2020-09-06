package com.ieng.huaimi.common.exception;

import com.ieng.huaimi.common.exception.face.BaseCodeInterface;

public enum ServiceCode implements BaseCodeInterface {
    BODY_NOT_MATCH(400, "请求的数据格式不符"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配"),
    NOT_FOUND(404, "未找到该资源"),
    SERVER_BUSY(500, "服务器正忙,请稍候再试"),
    USER_ACCOUNT_NOT_FOUND(1421, "账号不存在"),
    CURRENT_ACCOUNT_FORBID(1422, "当前账号禁止使用"),
    ACCOUNT_DEL(1423, "账号已被删除"),
    USER_PASSWORD_ERROR(1430, "用户名或密码错误"),
    PASSWORD_ERROR(1431, "密码错误"),
    NotDisableTheCurrentUser(1413, "不允许禁用当前用户"),
    UNDER_AUTHORITY(1214, "权限不足"),
    ADMINISTRATOR_FORBID(2001, "超级管理员,禁止任何操作"),
    SERVER_ERROR(5000, "服务器错误");

    private final int resultCode;
    private final String resultMsg;

    ServiceCode(int resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public int getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}

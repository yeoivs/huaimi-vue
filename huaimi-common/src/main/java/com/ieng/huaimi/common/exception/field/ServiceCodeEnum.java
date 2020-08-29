package com.ieng.huaimi.common.exception.field;

import com.ieng.huaimi.common.exception.face.BaseCodeInterface;

public enum ServiceCodeEnum implements BaseCodeInterface {
    BODY_NOT_MATCH(400, "请求的数据格式不符"),
    SIGNATURE_NOT_MATCH(401, "请求的数字签名不匹配"),
    NOT_FOUND(404, "未找到该资源"),
    SERVER_BUSY(500, "服务器正忙,请稍候再试"),
    USER_ACCOUNT_NOT_FOUND(1421, "账号不存在"),
    USER_PASSWORD_ERROR(1422, "密码错误"),
    NotDisableTheCurrentUser(1420, "不允许禁用当前用户");

    private final int resultCode;
    private final String resultMsg;

    ServiceCodeEnum(int resultCode, String resultMsg){
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

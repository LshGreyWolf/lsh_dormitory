package com.lsh.enums;

import org.omg.CORBA.TIMEOUT;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
public enum AppHttpCodeEnum {

    TOKEN_NOT_NULL(513, "token不能为空"),
    TIMEOUT_OR_ILLEGAL_TOKEN(514, "超时或不合法token"),

    VERSION_FLAT(516,"版本不一致，执行冲突");


    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
package com.lsh.config.exception;

import com.lsh.enums.AppHttpCodeEnum;

public class MyException extends RuntimeException {

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public MyException(AppHttpCodeEnum httpCodeEnum) {
        //最后输出的内容
        super(httpCodeEnum.getMsg());

        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }
}


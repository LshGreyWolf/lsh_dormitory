package com.lsh.enums;

import org.omg.CORBA.TIMEOUT;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),

    PHONENUMBER_EXIST(502, "手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    CONTENTNOTNULL(506, "内容不能为空"),

    FILE_TYPE_ERROR(507, "文件类型错误，请上传jpg格式的文件"),

    USERNAME_NOT_EXIST(508, "用户名不能为空"),
    NICKNAME_NOT_EXIST(509, "昵称不能为空"),
    PASSWORD_NOT_EXIST(510, "密码不能为空"),
    EMAIL_NOT_EXIST(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
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
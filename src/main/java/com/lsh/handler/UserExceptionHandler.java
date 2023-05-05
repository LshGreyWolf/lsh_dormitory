package com.lsh.handler;

import com.lsh.enums.AppHttpCodeEnum;
import com.lsh.utils.Result;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result UnAuthorizedExceptionHandler(UnauthorizedException e) {
        return Result.fail("没有权限");
    }
}
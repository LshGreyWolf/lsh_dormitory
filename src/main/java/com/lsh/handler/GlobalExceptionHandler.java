package com.lsh.handler;

import com.lsh.config.exception.MyException;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
    public Result myException(MyException e) {
        //打印异常信息
        log.error("出现了异常!{}", e);
        //从异常对象中获取提示信息，封装返回
        return Result.fail(e.getCode(), e.getMsg());

    }

}
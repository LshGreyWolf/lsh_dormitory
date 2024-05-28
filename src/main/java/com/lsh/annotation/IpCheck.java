package com.lsh.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * IP白名单校验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface IpCheck {

    /**
     * 白名单IP列表，支持${...}
     */
    @AliasFor("whiteList")
    String value() default "";

    /**
     * 白名单IP列表，支持${...}
     */
    @AliasFor("value")
    String whiteList() default "";

    /**
     * 黑名单IP列表，支持${...}
     */
    String blackList() default "";

}

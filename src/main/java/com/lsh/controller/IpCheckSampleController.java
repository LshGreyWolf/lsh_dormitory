package com.lsh.controller;

import com.lsh.annotation.IpCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample/ip-checker")
public class IpCheckSampleController {

    @GetMapping("/white")
    @IpCheck(value = "0:0:0:0:0:0:0:1")
    String whiteList() {
        return "127.0.0.1";
    }

    @GetMapping("/black")
    @IpCheck(blackList = "0:0:0:0:0:0:0:1")
    String blackList() {
        return "127.0.0.1";
    }

    /**
     * 同时配置白名单和黑名单，要求IP既在白名单，并且不在黑名单，否则抛出异常
     */
    @GetMapping("/all")
    @IpCheck(value = "0:0:0:0:0:0:0:1", blackList = "0:0:0:0:0:0:0:1")
    String all() {
        return "127.0.0.1";
    }

    /**
     * 同时配置白名单和黑名单，要求IP既在白名单，并且不在黑名单，否则抛出异常
     * 支持解析Spring 配置文件
     */
    @GetMapping("/config")
    @IpCheck(value = "${digit.ip.check.white-list}", blackList = "${digit.ip.check.black-list}")
    String config() {
        return "127.0.0.1";
    }

    /**
     * 同时配置白名单和黑名单，要求IP既在白名单，并且不在黑名单，否则抛出异常
     * 支持解析Spring 配置文件
     */
    @GetMapping("/black-config")
    @IpCheck(blackList = "${digit.ip.check.black-list}")
    String blackConfig() {
        return "127.0.0.1";
    }

}

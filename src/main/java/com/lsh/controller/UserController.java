package com.lsh.controller;

import com.github.pagehelper.PageHelper;
import com.lsh.domain.User;
import com.lsh.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description:
 * @author: Administrator
 * @date: 2023/03/05
 * @copyright 版权信息
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<User> selectAll() {
        List<User> list = userService.list(null);
       List<String> userNames =  list.stream().map(User::getName).collect(Collectors.toList());
        System.out.println("userNames = " + userNames);
        return list;
    }
}

package com.lsh.controller;

import com.lsh.utils.JWTUtil;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.service.StudentService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/06
 */
@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;

    @PostMapping
    public Result login(@RequestBody User user) {
        if (user.getType() == 2) { //学生登录的
            Student entity = studentService.login(user.getUserName(), user.getPassword());
            if (entity != null) {
                String token = JWTUtil.signForStudent(entity);
                Map map = new HashMap();
                map.put(JWTUtil.token, token);
                map.put("student", entity);
                return Result.ok("登陆成功", map);
            } else {
                return Result.fail("用户名或密码错误");
            }
        } else {//管理员与宿管员登录
            User entity = userService.login(user.getUserName(), user.getPassword());
            if (entity != null) {
                String token = JWTUtil.sign(entity);
                Map map = new HashMap();
                map.put(JWTUtil.token, token);
                map.put("user", entity);
                return Result.ok("登陆成功", map);
            } else {
                return Result.fail("用户名或密码错误");
            }
        }
    }





}

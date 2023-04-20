package com.lsh.controller;

import com.lsh.domain.Vo.StudentDto;

import com.lsh.utils.JWTUtil;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.service.StudentService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


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

//                用户已经被禁用 todo
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

    @PostMapping("/register")
    public Result register(@Validated @RequestBody StudentDto studentDto) {

        String password = studentDto.getPassword();

        String newPassword = studentDto.getNewPassword();
        if (!password.equals(newPassword)) {
            return Result.fail("两次密码不一致！");
        }
        Integer type = studentDto.getType();
        if (type != 2) {
            return Result.fail("只限制学生注册！");
        }
        boolean flag = studentService.register(studentDto);

        if (!flag) {
            return Result.fail("该手机号已注册！");
        }
        return Result.ok("注册成功，请登录！");
    }

    @PostMapping("/logout")
    public Result logout() {
        UserHolder.removeUser();
        UserHolder.removeStudent();

        return Result.ok("退出成功");
    }


}

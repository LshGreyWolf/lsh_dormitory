package com.lsh.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lsh.domain.Vo.LoginType;
import com.lsh.domain.Vo.StudentDto;

import com.lsh.utils.JWTUtil;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.service.StudentService;
import com.lsh.service.UserService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
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
    @Autowired
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping
    public Result login(@RequestBody User user) {
        if (user.getType() == 2) { //学生登录的
            Student entity = studentService.login(user.getUserName(), user.getPassword());
            if (entity != null) {
                String token = JWTUtil.signForStudent(entity);
                Map map = new HashMap();
                map.put(JWTUtil.token, token);
                map.put("student", entity);

                redisCache.setCacheObject("student", JSONUtil.toJsonStr(entity));
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
                redisCache.setCacheObject("user", JSONUtil.toJsonStr(entity));
                return Result.ok("登陆成功", map);
            } else {
                return Result.fail("用户名或密码错误");
            }
        }


    }

//    @PostMapping
//    public Result login(@RequestBody User user) {
//        Subject userSubject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
//        try {
//            // 登录验证
//            userSubject.login(token);
//            Subject subject = SecurityUtils.getSubject();
//            Serializable tokenId = subject.getSession().getId();
//            return Result.ok(tokenId);
//        } catch (UnknownAccountException e) {
//            return Result.fail("用户不存在");
//        } catch (DisabledAccountException e) {
//            return Result.fail("该用户被禁用");
//        } catch (IncorrectCredentialsException e) {
//            return Result.fail("用户名或密码错误");
//        } catch (Throwable e) {
//            e.printStackTrace();
//            return Result.fail("系统异常");
//        }
//    }

    /**
     * 注册
     *
     * @param studentDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody StudentDto studentDto) {

        String password = studentDto.getPassword();

        String newPassword = studentDto.getNewPassword();
        if (!password.equals(newPassword)) {
            return Result.fail("两次密码不一致！");
        }
        Integer type = studentDto.getType();

        studentDto.setType(2);
        boolean flag = studentService.register(studentDto);

        if (!flag) {
            return Result.fail("该手机号已注册！");
        }
        return Result.ok("注册成功，请登录！");
    }

    @PostMapping("/registerAdmin")
    public Result registerAdmin(@RequestBody User user) {
        String password = user.getPassword();
        String newPassword = user.getNewPassword();
        if (!password.equals(newPassword)) {
            return Result.fail("两次密码不一致！");
        }
        String phone = user.getPhone();
        User one = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (!StringUtils.isEmpty(one)) {
            return Result.fail("该手机号已注册！");
        }
        userService.save(user);
        return Result.ok("注册成功，请登录！");
    }

    /**
     * 登出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result logout() {
        UserHolder.removeUser();
        UserHolder.removeStudent();
        return Result.ok("退出成功");
    }


}

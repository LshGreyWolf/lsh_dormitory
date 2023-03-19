package com.lsh.controller;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.User;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    /**
     * 用户分页查询
     * @param user
     * @return
     */
    @PostMapping("/userQueryByPage")
    public Map<String, Object> userQueryByPage(@RequestBody User user) {
        PageInfo<User> pageInfo = userService.queryByPage(user);
        return Result.ok(pageInfo);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        if (flag) {
            return Result.ok("新增用户成功！");
        }
        return Result.fail("新增用户失败！");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {

        boolean flag = userService.updateUserById(user);
        return Result.ok("修改成功！");
    }

    @GetMapping("/delete")
    public Result deleteUser(String ids){
        int row = userService.delete(ids);
        if (row>0){
            return  Result.ok("删除成功");
        }
        return  Result.fail("删除失败");

    }

}

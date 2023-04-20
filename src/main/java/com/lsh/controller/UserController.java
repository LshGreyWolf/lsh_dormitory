package com.lsh.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Student;
import com.lsh.domain.User;
import com.lsh.domain.UserMenu;
import com.lsh.domain.Vo.PasswordDto;
import com.lsh.service.StudentService;
import com.lsh.service.UserMenuService;
import com.lsh.service.UserService;
import com.lsh.utils.RedisCache;
import com.lsh.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private StudentService studentService;

    /**
     * 用户分页查询
     *
     * @param user
     * @return
     */
    @PostMapping("/userQueryByPage")
    public Map<String, Object> userQueryByPage(@RequestBody User user) {
        PageInfo<User> pageInfo = userService.queryByPage(user);
        redisCache.setCacheObject("user:list", JSONUtil.toJsonStr(pageInfo));
        return Result.ok(pageInfo);
    }

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        log.info("============={}", user.getMenuIds());
        List<Integer> menuIds = user.getMenuIds();
//        user.setStatus(1);
        boolean flag = false;
        flag = userService.addUser(user);
//        UserMenu userMenu = BeanUtil.copyProperties(user, UserMenu.class);
        //同时将数据同步到用户菜单关联表中
        //新增用户时，因为之前没有，不用删除

        user = userService.getUser(user);
        //循环插入
        for (Integer menuId : menuIds) {
            flag = userMenuService.saveUserMenu(user.getId(), menuId);
        }
        if (flag) {
            return Result.ok("新增用户成功！");
        }
        return Result.fail("新增用户失败！");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) {

        boolean flag = userService.updateUserById(user);
        //先删除
        LambdaQueryWrapper<UserMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserMenu::getUserId, user.getId());
        userMenuService.remove(queryWrapper);

        for (Integer menuId : user.getMenuIds()) {
            flag = userMenuService.saveUserMenu(user.getId(), menuId);
        }
        if (flag) {
            return Result.ok("修改成功！");
        }
        return Result.fail("修改失败");
    }

    @GetMapping("/delete")
    public Result deleteUser(String ids) {
        int row = userService.delete(ids);
        if (row > 0) {
            return Result.ok("删除成功");
        }
        return Result.fail("删除失败");

    }

    @GetMapping("/updateStatus")
    public Result updateStatus(User user) {
        userService.updateStatusById(user);
        return Result.ok("更改成功！");
    }

    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody User user) {
        User user1 = new User();
        user1.setPassword("123456");
        user1.setId(user.getId());
        userService.updateUserById(user1);
        return Result.ok("重置密码成功！");
    }

    @Value("${server.port}")
    private String port;

    private static final String ip = "http://localhost";

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();//获取文件的名称

        //通过Hutool工具包的IdUtil类获取uuid作为前缀
        String prefix = IdUtil.fastSimpleUUID();
        String rootFilePath = System.getProperty("user.dir") + "/src/main/resources/files/" + prefix + "_" + filename;
        //使用Hutool工具包将我们接收到文件保存到rootFilePath中
        FileUtil.writeBytes(file.getBytes(), rootFilePath);
        return Result.ok("localhost:9001/"+prefix + "_" + filename);
//        return Result.ok(ip + ":" + port + "/static/" + prefix);
    }


    @PostMapping("/modifyPassword")
    public Result modifyPassword(@RequestBody PasswordDto passwordDto, HttpServletRequest request) {
        Integer type = passwordDto.getType();

        String password = passwordDto.getPassword();

        String newPassword = passwordDto.getNewPassword();

        String reNewPassword = passwordDto.getReNewPassword();

        // 0 user  1 student
        if (type ==0){
            User user = (User) request.getAttribute("user");
            User user1 = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getId, user.getId()));
            String oldPassword = user1.getPassword();
            if (!Objects.equals(oldPassword, password)){
                return  Result.fail("原密码错误，请重新输入！");
            }else {
                if (!newPassword.equals(reNewPassword)){
                    return Result.fail("两次输入的密码不一致，请重新输入！");
                }
                User user2 = new User();
                user2.setPassword(newPassword);
                user2.setId(user.getId());
                userService.updateById(user2);
            }
        }
        if (type ==1){
            Student student = (Student) request.getAttribute("student");
            Student student1 = studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getId, student.getId()));
            String oldPassword = student1.getPassword();
            if (!Objects.equals(oldPassword, password)){
                return  Result.fail("原密码错误，请重新输入！");
            }else {
                if (!newPassword.equals(reNewPassword)){
                    return Result.fail("两次输入的密码不一致，请重新输入！");
                }
                Student student2 = new Student();
                student2.setPassword(newPassword);
                student2.setId(student.getId());
                studentService.updateById(student2);
            }
        }

        return Result.ok("修改成功！");
    }


}

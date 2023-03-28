package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.Notice;
import com.lsh.domain.User;
import com.lsh.service.NoticeService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/28
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;
    @Autowired
    private UserService userService;

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody Notice notice){
        PageInfo<Notice> pageInfo = noticeService.queryByPage(notice);
        pageInfo.getList().forEach(item->{

            Integer userId = item.getUserId();
            User user = new User();
            user.setId(userId);
            item.setUser(userService.getUser(user));
        });
        return Result.ok(pageInfo);
    }


    @PostMapping("create")
    public Result create(@RequestBody Notice notice, HttpServletRequest request){
        User user = (User) request.getAttribute("user");
        notice.setUserId(user.getId());
        int flag = noticeService.create(notice);
        if(flag>0){
            return Result.ok("新增成功！");
        }else{
            return Result.fail("新增失败！");
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        int flag = noticeService.delete(ids);
        if(flag>0){
            return Result.ok("删除成功！");
        }else{
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Notice notice){
        int flag = noticeService.updateSelective(notice);
        if(flag>0){
            return Result.ok("更新成功！");
        }else{
            return Result.fail("更新失败！");
        }
    }



}

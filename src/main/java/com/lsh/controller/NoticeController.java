package com.lsh.controller;

import com.github.pagehelper.PageInfo;
import com.lsh.domain.Notice;
import com.lsh.domain.NoticeReceive;
import com.lsh.domain.User;
import com.lsh.service.NoticeReceiveService;
import com.lsh.service.NoticeService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    @Autowired
    private NoticeReceiveService noticeReceiveService;

    @PostMapping("query")
    public Map<String, Object> query(@RequestBody Notice notice) {
        PageInfo<Notice> pageInfo = noticeService.queryByPage(notice);
        pageInfo.getList().forEach(item -> {

            Integer userId = item.getUserId();
            User user = new User();
            user.setId(userId);
            item.setUser(userService.getUser(user));
        });
        return Result.ok(pageInfo);
    }


    @PostMapping("/saveNotice")
    public Result saveNotice(@RequestBody Notice notice) {

        User user = UserHolder.getUser();
        notice.setUserId(user.getId());

        //将公告插入公告表
        int flag = noticeService.insertNotice(notice);
        //插入公告_接收者关联表
        List<Integer> buildingIds = notice.getBuildingIds();
        for (Integer buildingId : buildingIds) {
            NoticeReceive  noticeReceive = new NoticeReceive();
            noticeReceive.setBuildingId(buildingId);
            noticeReceive.setNoticeId(notice.getId());
            noticeReceiveService.saveNoticeReceive(noticeReceive);
        }
        if (flag > 0) {
            return Result.ok("新增公告成功！");
        } else {
            return Result.fail("新增公告失败！");
        }
    }

    @GetMapping("/deleteNotice")
    public Result deleteNotice(String ids) {
        int flag = noticeService.delete(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("update")
    public Result updateNotice(@RequestBody Notice notice) {
        int flag = noticeService.updateNotice(notice);
        if (flag > 0) {
            return Result.ok("更新成功！");
        } else {
            return Result.fail("更新失败！");
        }
    }


}

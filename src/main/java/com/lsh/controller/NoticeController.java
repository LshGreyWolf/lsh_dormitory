package com.lsh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.Building;
import com.lsh.domain.Notice;
import com.lsh.domain.NoticeReceive;
import com.lsh.domain.User;
import com.lsh.service.BuildingService;
import com.lsh.service.NoticeReceiveService;
import com.lsh.service.NoticeService;
import com.lsh.service.UserService;
import com.lsh.utils.Result;
import com.lsh.utils.UserHolder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lsh.constants.SystemConstants.USER_TYPE_ADMINISTRATORS;

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
    private BuildingService buildingService;
    @Autowired
    private NoticeReceiveService noticeReceiveService;

    @PostMapping("query")
    public Result query(@RequestBody Notice notice) {
        PageInfo<Notice> pageInfo = noticeService.queryByPage(notice);
        List<Notice> noticeList = noticeService.list();
        noticeList.forEach(item -> {
            Integer userId = item.getUserId();
            User user = new User();
            user.setId(userId);
            item.setUser(userService.getUser(user));
        });

        User param = UserHolder.getUser();
        User loginUser = userService.getUser(param);
        if (loginUser.getType() == USER_TYPE_ADMINISTRATORS) {
            //得到用户id，查询改用户负责的楼栋，查询公告的时候加上楼栋限制
            Building building = buildingService.
                    getOne(new LambdaQueryWrapper<Building>().eq(Building::getUserId, loginUser.getId()));
            Integer buildingId = building.getId();
            //根据用户负责的楼栋来查询改楼栋的公告
            List<NoticeReceive> receiveList = noticeReceiveService
                    .list(new LambdaQueryWrapper<NoticeReceive>().eq(NoticeReceive::getBuildingId, buildingId));
            List<Integer> idList = receiveList.stream().map(item -> {
                return item.getNoticeId();
            }).collect(Collectors.toList());
            List<Notice> notices = noticeService.queryByPageUser(idList);
            notices.forEach(item -> {
                Integer userId = item.getUserId();
                User user = new User();
                user.setId(userId);
                item.setUser(userService.getUser(user));
            });
            return Result.ok(notices);
        }
        return Result.ok(noticeList);
    }


    @PostMapping("/saveNotice")
    public Result saveNotice(@RequestBody Notice notice) {
        User user = UserHolder.getUser();
        notice.setUserId(user.getId());
        notice.setCreateTime(new Date());
        //将公告插入公告表
        int flag = noticeService.insertNotice(notice);
        //插入公告_接收者关联表
        List<Integer> buildingIds = notice.getBuildingIds();
        for (Integer buildingId : buildingIds) {
            NoticeReceive noticeReceive = new NoticeReceive();
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
        int flag = noticeService.deleteNotice(ids);
        if (flag > 0) {
            return Result.ok("删除成功！");
        } else {
            return Result.fail("删除失败！");
        }
    }

    @PostMapping("/updateNotice")
    public Result updateNotice(@RequestBody Notice notice) {
        int flag = noticeService.updateNotice(notice);
        if (flag > 0) {
            return Result.ok("更新成功！");
        } else {
            return Result.fail("更新失败！");
        }
    }


}

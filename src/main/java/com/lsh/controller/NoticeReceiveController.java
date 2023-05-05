package com.lsh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.lsh.domain.NoticeReceive;
import com.lsh.service.NoticeReceiveService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/30
 */
@RestController
@RequestMapping("/noticeReceive")
public class NoticeReceiveController {
    @Autowired
    private NoticeReceiveService noticeReceiveService;

    /**
     * 查询公告接收者（用于更新公告时的回显）
     * @param noticeReceive
     * @return
     */
    @PostMapping("/queryNoticeReceive")
    public Map<String, Object> queryNoticeReceive(@RequestBody NoticeReceive noticeReceive) {
        PageInfo<NoticeReceive> pageInfo = noticeReceiveService.queryNoticeReceive(noticeReceive);
        return Result.ok(pageInfo);
    }

    /**
     * 更新
     * @param noticeReceive
     * @return
     */
    @PostMapping("/updateNoticeReceive")
    public Result updateNoticeReceive(@RequestBody NoticeReceive noticeReceive) {
        int flag = noticeReceiveService.updateNoticeReceive(noticeReceive);
        if (flag > 0) {
            return Result.ok("更新成功！");
        } else {
            return Result.fail("更新失败！");
        }
    }


}

package com.lsh.controller;

import com.lsh.service.NoticeReceiveService;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description:
 * @author: lsh
 * @date: 2023/03/30
 */
@RestController
@RequestMapping("/NoticeReceive")
public class NoticeReceiveController {
    @Autowired
    private NoticeReceiveService noticeReceiveService;



}

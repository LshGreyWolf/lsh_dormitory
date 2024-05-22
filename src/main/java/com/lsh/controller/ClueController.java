package com.lsh.controller;

import com.lsh.event.ClueEvent;
import com.lsh.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/clue")
public class ClueController {


    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/save")
    @ResponseBody
    public Result save(@RequestBody String message) {
        applicationEventPublisher.publishEvent(new ClueEvent(this, message));
        return Result.ok("发送成功");
    }
}
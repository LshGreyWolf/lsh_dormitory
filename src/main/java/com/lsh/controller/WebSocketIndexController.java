package com.lsh.controller;

import com.lsh.service.PaperService;
import com.lsh.utils.QZFactory;
import com.lsh.utils.Result;
import com.lsh.websocket.ClueServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class WebSocketIndexController {
    @Autowired
    private  ClueServer clueServer;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("webSocketUrl", clueServer.getWebSocketUrl());
        return "/index";
    }
    @GetMapping("/test")
    public Result index(String beanName) {
        PaperService paperService = QZFactory.chooseCreate(beanName);
        paperService.buildPaperTemp();
        return Result.ok();
    }
}

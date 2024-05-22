package com.lsh.controller;

import com.lsh.websocket.ClueServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class IndexController {
    @Autowired
    private  ClueServer clueServer;
    @GetMapping
    public String index(Model model) {
        model.addAttribute("webSocketUrl", clueServer.getWebSocketUrl());
        return "/index";
    }
}

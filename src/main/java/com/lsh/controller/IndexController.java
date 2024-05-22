package com.lsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class IndexController {
    @GetMapping
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("webSocketUrl", ClueServer.getWebSocketUrl(request));
        return "/index";
    }
}

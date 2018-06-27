package com.zheng.news.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 * zheng
 **/
@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("hello", "你好");
        return "/ind.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("hello", "你好");
        return "/login.html";
    }
}

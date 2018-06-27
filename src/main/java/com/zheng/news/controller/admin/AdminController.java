package com.zheng.news.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zheng
 * @date 2018/6/27
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String index() {
        return "/admin/index.html";
    }
}

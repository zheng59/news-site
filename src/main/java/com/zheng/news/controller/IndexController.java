package com.zheng.news.controller;

import com.zheng.news.model.Contact;
import com.zheng.news.model.NewsCategory;
import com.zheng.news.repositories.ContactRepository;
import com.zheng.news.repositories.NoticeRepository;
import com.zheng.news.service.ImageLinkService;
import com.zheng.news.service.NewsCategoryService;
import com.zheng.news.service.NewsService;
import com.zheng.news.service.TextLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * IndexController
 * zheng
 **/
@Controller
public class IndexController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ImageLinkService imageLinkService;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TextLinkService textLinkService;

    @GetMapping(value = {"/index",""})
    public String index(Model model) {
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("notice", noticeRepository.findTopByOrderByIdDesc());
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        return "/index.html";
    }

    @GetMapping("/list.html")
    public String list(@RequestParam Integer categoryId, Model model) {
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("Children", newsCategoryService.findAllByParentId(categoryId));
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        return "/list.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("hello", "你好");
        return "/login.html";
    }
}

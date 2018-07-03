package com.zheng.news.controller;

import com.zheng.news.model.Contact;
import com.zheng.news.model.News;
import com.zheng.news.model.NewsCategory;
import com.zheng.news.model.Notice;
import com.zheng.news.repositories.AdmissionRepository;
import com.zheng.news.repositories.ContactRepository;
import com.zheng.news.repositories.NoticeRepository;
import com.zheng.news.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private SlideshowService slideshowService;

    @Value("${news.search.intro}")
    private String searchIntro;

    @GetMapping(value = {"/index",""})
    public String index(Model model) {
        List<NewsCategory> categoryList = newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY);
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("category1", newsService.findByCategoryId(categoryList.get(0).getId(),PageRequest.of(0,6,Sort.Direction.DESC,"createdAt")).getContent());
        model.addAttribute("category2", newsService.findByCategoryId(categoryList.get(1).getId(),PageRequest.of(0,6,Sort.Direction.DESC,"createdAt")).getContent());
        model.addAttribute("category3", newsService.findByCategoryId(categoryList.get(2).getId(),PageRequest.of(0,6,Sort.Direction.DESC,"createdAt")).getContent());
        model.addAttribute("category4", newsService.findByCategoryId(categoryList.get(3).getId(),PageRequest.of(0,6,Sort.Direction.DESC,"createdAt")).getContent());
        model.addAttribute("notice", noticeRepository.findTopByOrderByIdDesc());
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("imageLinks", imageLinkService.findAll());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        model.addAttribute("searchIntro", searchIntro);
        model.addAttribute("slideshows", slideshowService.findAll());
        return "/index.html";
    }

    @GetMapping("/list.html")
    public String list(@RequestParam Integer categoryId,
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       Model model) {
        if (page < 0) {
            page = 0;
        }
        NewsCategory currentCategory = newsCategoryService.find(categoryId);
        NewsCategory currentTopCategory;
        if (currentCategory.getParentId().equals(NewsCategory.TOP_CATEGORY)) {
            currentTopCategory = currentCategory;
        }else {
            currentTopCategory =  newsCategoryService.find(currentCategory.getParentId());
        }
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("children", newsCategoryService.findAllByParentId(currentTopCategory.getId()));
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        model.addAttribute("currentTopCategory",currentTopCategory);
        model.addAttribute("page",newsService.findByCategoryId(categoryId,PageRequest.of(page,size,Sort.Direction.DESC,"createdAt")));
        model.addAttribute("currentCategory",currentCategory);
        return "/list.html";
    }

    @GetMapping("/news.html")
    public String list(@RequestParam Integer id,
                       Model model) {
        News news = newsService.find(id).get();
        NewsCategory currentCategory = newsCategoryService.find(news.getCategory().getId());
        NewsCategory currentTopCategory;
        if (currentCategory.getParentId().equals(NewsCategory.TOP_CATEGORY)) {
            currentTopCategory = currentCategory;
        }else {
            currentTopCategory =  newsCategoryService.find(currentCategory.getParentId());
        }
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        model.addAttribute("currentTopCategory",currentTopCategory);
        model.addAttribute("currentCategory",currentCategory);
        model.addAttribute("news",news);
        return "/news.html";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("hello", "你好");
        return "/login.html";
    }

    @GetMapping(value ="/admission.html")
    public String image(@RequestParam String cardNumber,Model model) {
        model.addAttribute("admission", admissionRepository.findByCardNumber(cardNumber));
        return "/admission.html";
    }

    @GetMapping("/noticeList.html")
    public String noticeList(
                       @RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       Model model) {
        if (page < 0) {
            page = 0;
        }
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        model.addAttribute("page",noticeRepository.findAll(PageRequest.of(page,size)));
        return "/noticeList.html";
    }

    @GetMapping("/notice.html")
    public String notice(@RequestParam Integer id,
                       Model model) {
        Notice news = noticeRepository.findById(id).get();
        model.addAttribute("categoryList", newsCategoryService.findAllByParentId(NewsCategory.TOP_CATEGORY));
        model.addAttribute("textLinks", textLinkService.findALl());
        model.addAttribute("contact", contactRepository.findOne(Example.of(new Contact())).orElse(new Contact()));
        model.addAttribute("news",news);
        return "/notice.html";
    }

    @GetMapping("/index01.html")
    public String index01() {
        return "/index01.html";
    }
}

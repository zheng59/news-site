package com.zheng.news.controller.admin;

import com.zheng.news.model.News;
import com.zheng.news.model.NewsCategory;
import com.zheng.news.service.NewsCategoryService;
import com.zheng.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * NewsController
 * zheng
 **/
@RequestMapping("/admin/news")
@Controller
public class NewsManagerController {

    private final NewsCategoryService newsCategoryService;

    private final NewsService newsService;

    @Autowired
    public NewsManagerController(NewsCategoryService newsCategoryService, NewsService newsService) {
        this.newsCategoryService = newsCategoryService;
        this.newsService = newsService;
    }

    @GetMapping("/add.html")
    public String add(Model model) {
        List<NewsCategory> categories = newsCategoryService.findAllNode();
        model.addAttribute("categories", categories);
        return "/admin/news/add";
    }

    @GetMapping("/edit.html")
    public String edit() {
        return "/admin/news/edit";
    }

    @GetMapping("/list.html")
    public String find(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "8") Integer size,
            Model model) {
        Page<News> newsPage = newsService.query(categoryId, PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));
        List<NewsCategory> categories = newsCategoryService.findAllNode();
        model.addAttribute("newsPage", newsPage);
        model.addAttribute("categories", categories);
        return "/admin/news/list";
    }

    @PostMapping
    public ResponseEntity<News> create(@RequestBody News news) {
        return ResponseEntity.ok(newsService.create(news));
    }

    @GetMapping
    public ResponseEntity<Page<News>> query(@RequestParam(required = false) Integer categoryId,
                                            @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "8") Integer size) {
        return ResponseEntity.ok(newsService.query(categoryId, PageRequest.of(page, size, Sort.Direction.DESC, "createdAt")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> find(@PathVariable("id") Integer id) {
        return newsService.find(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<News> delete(@PathVariable("id") Integer id) {
        newsService.delete(id);
        return ResponseEntity.ok().build();
    }
}

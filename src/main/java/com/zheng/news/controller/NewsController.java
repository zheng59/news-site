package com.zheng.news.controller;

import com.zheng.news.model.Admission;
import com.zheng.news.model.News;
import com.zheng.news.model.NewsCategory;
import com.zheng.news.repositories.NewsRepository;
import com.zheng.news.service.NewsCategoryService;
import com.zheng.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * NewsController
 * zheng
 **/
@SuppressWarnings("unchecked")
@RequestMapping("/news")
@Controller
public class NewsController {

    private final NewsService newsService;

    private final NewsCategoryService newsCategoryService;

    @Autowired
    public NewsController(NewsService newsService, NewsCategoryService newsCategoryService) {
        this.newsService = newsService;
        this.newsCategoryService = newsCategoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> find(@PathVariable("id") Integer id) {
        return newsService.find(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }




}

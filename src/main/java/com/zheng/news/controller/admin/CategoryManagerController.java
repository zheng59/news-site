package com.zheng.news.controller.admin;

import com.zheng.news.model.NewsCategory;
import com.zheng.news.service.NewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * CategoryController
 * zheng
 **/

@Controller
@RequestMapping("/admin/news/category")
public class CategoryManagerController {

    @Autowired
    private NewsCategoryService newsCategoryService;

    @PostMapping
    public ResponseEntity<NewsCategory> create(@RequestBody NewsCategory category) {
        return ResponseEntity.ok(newsCategoryService.create(category)) ;
    }

    @GetMapping
    public ResponseEntity<List<NewsCategory>> finAll() {
        return ResponseEntity.ok(newsCategoryService.findAllNode()) ;
    }


}

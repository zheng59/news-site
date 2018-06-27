package com.zheng.news.controller.admin;

import com.zheng.news.model.Slideshow;
import com.zheng.news.service.SlideshowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SlideshowController
 * zheng
 **/
@RequestMapping("/admin/settings")
@Controller
public class SlideshowController {

    @Autowired
    private SlideshowService slideshowService;

    @GetMapping("/slideshow/edit.html")
    public String index() {
        return "/admin/settings/slideshow/edit.html";
    }

    @GetMapping("/slideshow/list.html")
    public String list() {
        return "/admin/settings/slideshow/list.html";
    }

    @GetMapping("/slideshow/{id}")
    public ResponseEntity<Slideshow> find(@PathVariable(name = "id") Integer id) {
        return slideshowService.find(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/slideshow")
    public ResponseEntity<Slideshow> create(@RequestBody Slideshow slideshow) {
        return ResponseEntity.ok(slideshowService.insert(slideshow));
    }

    @DeleteMapping("/slideshow/{id}")
    public ResponseEntity<Slideshow> delete(@PathVariable(name = "id") Integer id) {
        slideshowService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/slideshow")
    public ResponseEntity<List<Slideshow>> query() {
        return ResponseEntity.ok(slideshowService.findAll());
    }


}


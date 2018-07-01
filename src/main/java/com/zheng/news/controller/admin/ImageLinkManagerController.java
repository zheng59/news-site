package com.zheng.news.controller.admin;

import com.zheng.news.model.ImageLink;
import com.zheng.news.model.Slideshow;
import com.zheng.news.service.ImageLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ImageLinkManagerController
 * zheng
 **/
@Controller()
@RequestMapping("/admin/link")
public class ImageLinkManagerController {

    @Autowired
    private ImageLinkService imageLinkService;

//    @GetMapping("/image.html")
//    public String index() {
//        return "admin/settings/image.html";
//    }

    @GetMapping("/image/edit.html")
    public String index() {
        return "/admin/settings/image/edit.html";
    }

    @GetMapping("/image/list.html")
    public String list() {
        return "/admin/settings/image/list.html";
    }

    @PostMapping("/image")
    public ResponseEntity<ImageLink> create(@RequestBody ImageLink imageLink) {
        return ResponseEntity.ok(imageLinkService.save(imageLink));
    }

    @DeleteMapping("/image/{id}")
    public ResponseEntity<ImageLink> delete(@PathVariable(name = "id") Integer id) {
        imageLinkService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image")
    public ResponseEntity<List<ImageLink>> findAll() {
        return ResponseEntity.ok(imageLinkService.findAll());
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<ImageLink> find(@PathVariable(name = "id") Integer id) {
        return imageLinkService.find(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/image")
//    public ResponseEntity<ImageLink> find() {
//        return ResponseEntity.ok(imageLinkService.find());
//    }
//
//    @PostMapping("/image")
//    public ResponseEntity<ImageLink> save(@RequestBody ImageLink imageLink) {
//        return ResponseEntity.ok(imageLinkService.save(imageLink));
//    }
}

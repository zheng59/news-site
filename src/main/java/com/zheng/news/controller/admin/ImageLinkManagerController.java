package com.zheng.news.controller.admin;

import com.zheng.news.model.ImageLink;
import com.zheng.news.service.ImageLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ImageLinkManagerController
 * zheng
 **/
@Controller()
@RequestMapping("/admin/link")
public class ImageLinkManagerController {

    @Autowired
    private ImageLinkService imageLinkService;

    @GetMapping("/image.html")
    public String index() {
        return "/admin/settings/image.html";
    }

    @GetMapping("/image")
    public ResponseEntity<ImageLink> find() {
        return ResponseEntity.ok(imageLinkService.find());
    }

    @PostMapping("/image")
    public ResponseEntity<ImageLink> save(@RequestBody ImageLink imageLink) {
        return ResponseEntity.ok(imageLinkService.save(imageLink));
    }
}

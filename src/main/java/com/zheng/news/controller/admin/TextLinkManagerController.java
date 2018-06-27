package com.zheng.news.controller.admin;

import com.zheng.news.model.TextLink;
import com.zheng.news.service.TextLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * TextLinkController
 * zheng
 **/
@Controller
@RequestMapping("/admin/link")
public class TextLinkManagerController {

    @Autowired
    private TextLinkService textLinkService;

    @GetMapping("/text.html")
    public String index() {
        return "/admin/settings/textLink.html";
    }

    @PostMapping("/text")
    public ResponseEntity<TextLink> create(@RequestBody TextLink textLink) {
        return ResponseEntity.ok(textLinkService.create(textLink));
    }

    @DeleteMapping("/text/{id}")
    public ResponseEntity<TextLink> delete(@PathVariable(name = "id") Integer id) {
        textLinkService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/text")
    public ResponseEntity<Page<TextLink>> query(@RequestParam(name = "page" ,defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size" ,defaultValue = "5")Integer size) {
        return ResponseEntity.ok(textLinkService.findAll(page, size));
    }


}

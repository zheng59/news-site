package com.zheng.news.controller.admin;

import com.zheng.news.model.Notice;
import com.zheng.news.repositories.NoticeRepository;
import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

/**
 * NoticeManagerController
 * zheng
 **/
@Controller
@RequestMapping("/admin")
public class NoticeManagerController {

    @Autowired
    private NoticeRepository noticeRepository;


    @GetMapping("/notice/edit.html")
    public String edit() {
        return "/admin/settings/notice/edit.html";
    }

    @GetMapping("/notice/list.html")
    public String list() {
        return "/admin/settings/notice/list.html";
    }

    @GetMapping("/notice/add.html")
    public String add() {
        return "/admin/settings/notice/add.html";
    }

    @GetMapping("/notice")
    public ResponseEntity<Page<Notice>> edit(@RequestParam Integer page,
                       @RequestParam Integer size) {
        return ResponseEntity.ok(noticeRepository.findAll(PageRequest.of(page, size)));
    }

    @PostMapping("/notice")
    public ResponseEntity<Notice> create(@RequestBody Notice notice) {
        notice.setCreatedAt(ZonedDateTime.now());
        return ResponseEntity.ok(noticeRepository.save(notice));
    }

    @GetMapping("/notice/{id}")
    public ResponseEntity<Notice> find(@PathVariable("id") Integer id) {
        return noticeRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/notice/{id}")
    public ResponseEntity<Notice> delete(@PathVariable("id") Integer id) {
        noticeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

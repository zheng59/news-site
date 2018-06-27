package com.zheng.news.controller.admin;

import com.zheng.news.model.Admission;
import com.zheng.news.repositories.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ImageController
 * zheng
 **/
@Controller
@RequestMapping("/admin/admission")
public class AdmissionManagerController {

    @Autowired
    private AdmissionRepository imageRepository;

    @GetMapping("/edit.html")
    public String index() {
        return "/admin/settings/admission/edit.html";
    }

    @GetMapping("/list.html")
    public String list() {
        return "/admin/settings/admission/list.html";
    }

    @PostMapping
    public ResponseEntity<Admission> create(@RequestBody Admission image) {
        return ResponseEntity.ok(imageRepository.save(image));
    }

    @GetMapping
    public ResponseEntity<Page<Admission>> query(@RequestParam(name = "page" ,defaultValue = "0") Integer page,
                                                 @RequestParam(name = "size" ,defaultValue = "5")Integer size) {
        return ResponseEntity.ok(imageRepository.findAll(PageRequest.of(page, size)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Admission> create(@PathVariable(name = "id") Integer id) {
        imageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admission> find(@PathVariable(name = "id") Integer id) {
        return imageRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }




}

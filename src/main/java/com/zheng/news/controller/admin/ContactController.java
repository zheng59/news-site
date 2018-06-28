package com.zheng.news.controller.admin;

import com.zheng.news.model.Contact;
import com.zheng.news.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ContactController
 * zheng
 **/
@Controller
@RequestMapping("/admin")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact.html")
    public String index() {
        return "/admin/settings/contact.html";
}

    @PostMapping("/contact")
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        contactRepository.deleteAll();
        return ResponseEntity.ok(contactRepository.save(contact));
    }

    @GetMapping("/contact")
    public ResponseEntity<Contact> find() {
        return contactRepository.findOne(Example.of(new Contact())).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}

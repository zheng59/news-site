package com.zheng.news.service;

import com.zheng.news.model.TextLink;
import com.zheng.news.repositories.TextLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TextLinkService
 * zheng
 **/
@Service
public class TextLinkService {

    @Autowired
    private TextLinkRepository textLinkRepository;

    public TextLink create(TextLink textLink) {
        return textLinkRepository.save(textLink);
    }

    public List<TextLink> findALl(){
        return textLinkRepository.findAll();
    }

    public void delete(Integer id) {
        textLinkRepository.deleteById(id);
    }

    public Page<TextLink> findAll(Integer page, Integer size) {
        return textLinkRepository.findAll(PageRequest.of(page, size));
    }

}

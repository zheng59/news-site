package com.zheng.news.service;

import com.zheng.news.model.ImageLink;
import com.zheng.news.repositories.ImageLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * ImageLinkService
 * zheng
 **/
@Service
public class ImageLinkService {

    @Autowired
    private ImageLinkRepository imageLinkRepository;

    public ImageLink find() {
        List<ImageLink> imageLinks = imageLinkRepository.findAll();
        if (CollectionUtils.isEmpty(imageLinks)) {
            return null;
        } else {
            return imageLinks.get(0);
        }
    }

    public ImageLink save(ImageLink imageLink) {
        return imageLinkRepository.save(imageLink);
    }
}

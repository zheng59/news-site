package com.zheng.news.service;

import com.zheng.news.model.Slideshow;
import com.zheng.news.repositories.SlideshowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * SlideshowService
 * zheng
 **/

@Service
public class SlideshowService {

    @Autowired
    private SlideshowRepository slideshowRepository;

    public Optional<Slideshow> find(Integer id) {
        return slideshowRepository.findById(id);
    }


    public List<Slideshow> create(List<Slideshow> slideshowList) {
        setLocation(slideshowList);
        slideshowRepository.deleteAll();
        slideshowRepository.saveAll(slideshowList);
        return slideshowRepository.findAll(new Sort(Sort.Direction.ASC, "location"));
    }

    public List<Slideshow> findAll() {
        return slideshowRepository.findAll(new Sort(Sort.Direction.ASC,"location"));
    }

    public Slideshow insert(Slideshow slideshow) {
        if (slideshowRepository.count() >= 8) {
            throw new IndexOutOfBoundsException("幻灯片最多不能超过八张");
        }
        if (slideshow.getLocation() == null) {
            slideshow.setLocation(100);
        }
        return slideshowRepository.save(slideshow);

    }

    public void delete(Integer id) {
            slideshowRepository.deleteById(id);
    }



    /**
     * 根据为幻灯片添加位置属性
     * @param slideshowList
     */
    private void setLocation(List<Slideshow> slideshowList) {
        for (int i = 0; i < slideshowList.size(); i++) {
            slideshowList.get(i).setLocation(i);
        }

    }
}

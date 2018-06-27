package com.zheng.news.repositories;

import com.zheng.news.model.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Slideshow Repository
 * zheng
 **/
public interface SlideshowRepository extends JpaRepository<Slideshow,Integer> {

    List<Slideshow> findAllByLocationGreaterThan(Integer localtion);

    List<Slideshow> findAllByLocationGreaterThanEqualAndLocationLessThan(Integer greater,Integer less);
}

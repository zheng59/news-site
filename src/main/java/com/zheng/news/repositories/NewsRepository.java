package com.zheng.news.repositories;

import com.zheng.news.model.News;
import com.zheng.news.model.NewsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * NewsRepository
 * zheng
 **/
public interface NewsRepository extends JpaRepository<News,Integer> {
    List<News> findAllByCategoryIn(List<NewsCategory> newsCategories);

    Page<News> findAllByCategoryId(Integer categoryId, Pageable pageable);

    void deleteByCategoryId(Integer newsCategoryId);

    Page<News> findAllByCategoryIn(List<NewsCategory> newsCategories, Pageable pageable);

}

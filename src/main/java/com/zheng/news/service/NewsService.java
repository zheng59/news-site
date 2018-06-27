package com.zheng.news.service;

import com.zheng.news.model.News;
import com.zheng.news.model.NewsCategory;
import com.zheng.news.repositories.NewsCategoryRepository;
import com.zheng.news.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * NewsService
 * zheng
 **/
@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    public News create(News news) {
        NewsCategory category = Optional.of(news)
                .map(News::getCategory)
                .map(NewsCategory::getId)
                .map(it -> newsCategoryRepository.findById(it).orElse(null)).orElse(null);
        news.setCategory(category);
        news.setCreatedAt(ZonedDateTime.now());
        return newsRepository.save(news);
    }

    public void delete(Integer id) {
        newsRepository.deleteById(id);
    }

    public Optional<News> find(Integer id) {
        return newsRepository.findById(id);
    }

    public List<News> findAll(List<NewsCategory> newsCategories) {
        return new ArrayList<>();
    }

    public Page<News> query(Integer categoryId, Pageable pageable) {
        return Objects.nonNull(categoryId)?
                newsRepository.findAllByCategoryId(categoryId, pageable)
                :newsRepository.findAll(pageable);
    }

}

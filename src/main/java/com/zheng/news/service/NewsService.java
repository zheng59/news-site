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
import java.util.*;

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
        news.setCreatedAt(new Date());
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

    public Page<News> findByCategoryId(Integer categoryId, Pageable pageable) {
        Optional<NewsCategory> newsCategory = newsCategoryRepository.findById(categoryId);
        if (newsCategory.map(NewsCategory::getParentId).orElse(0).equals(NewsCategory.TOP_CATEGORY)) {
            List<NewsCategory> newsCategories = newsCategoryRepository.findAllByParentId(categoryId);
            newsCategories.add(newsCategoryRepository.findById(categoryId).get());
            return newsRepository.findAllByCategoryIn(newsCategories,pageable);
        }else {
            return query(categoryId, pageable);
        }
    }


}

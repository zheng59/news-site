package com.zheng.news.service;

import com.zheng.news.model.NewsCategory;
import com.zheng.news.repositories.NewsCategoryRepository;
import com.zheng.news.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * NewsCategoryService
 * zheng
 **/
@Service
public class NewsCategoryService {

    @Autowired
    private NewsCategoryRepository newsCategoryRepository;

    @Autowired
    private NewsRepository newsRepository;


    public NewsCategory create(NewsCategory newsCategory) {
        return newsCategoryRepository.save(newsCategory);
    }

    public NewsCategory find(Integer id) {
        return newsCategoryRepository.findById(id).orElse(new NewsCategory());
    }

    public NewsCategory update(NewsCategory example) {
        return newsCategoryRepository.save(example);
    }

    public void delete(Integer id) {
         newsCategoryRepository.findById(id).ifPresent(newsCategory->{
             appendChildren(newsCategory);
             Collection<Integer> newsCategoryIds = new HashSet<>();
             getTreeIdToCollection(newsCategory, newsCategoryIds);
             newsCategoryRepository.deleteByIdIn(newsCategoryIds);
             newsCategoryIds.forEach(newsRepository::deleteByCategoryId);
        });
    }

    public List<NewsCategory> findAllByParentId(Integer parentId) {
        return newsCategoryRepository.findAllByParentId(parentId);
    }




    private void appendChildren(NewsCategory newsCategory) {
        Collection<NewsCategory> Children = newsCategoryRepository.findAllByParentId(newsCategory.getId());
        if (!CollectionUtils.isEmpty(Children)) {
            Children.forEach(this::appendChildren);
            newsCategory.setChildren(Children);
        }
    }

    private void getTreeIdToCollection(NewsCategory newsCategory, Collection<Integer> newsCategoryIds) {
        if (CollectionUtils.isEmpty(newsCategory.getChildren())) {
            newsCategory.getChildren().forEach(it->getTreeIdToCollection(it,newsCategoryIds));
        }
        newsCategoryIds.add(newsCategory.getId());
    }

    public List<NewsCategory> findAllNode() {
        return newsCategoryRepository.findAllByParentId(NewsCategory.TOP_CATEGORY).stream()
                .peek(this::appendChildren).collect(Collectors.toList());
    }

}

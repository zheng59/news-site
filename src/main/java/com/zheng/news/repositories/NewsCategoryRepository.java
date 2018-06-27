package com.zheng.news.repositories;

import com.zheng.news.model.NewsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface NewsCategoryRepository extends JpaRepository<NewsCategory,Integer> {

    List<NewsCategory> findAllByParentId(Integer parentId);

    void deleteByIdIn(Collection<Integer> ids);
}

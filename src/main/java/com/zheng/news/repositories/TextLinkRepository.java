package com.zheng.news.repositories;

import com.zheng.news.model.TextLink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TextLinkRepository
 * zheng
 **/
public interface TextLinkRepository extends JpaRepository<TextLink,Integer> {
}

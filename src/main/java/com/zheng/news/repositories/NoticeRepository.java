package com.zheng.news.repositories;

import com.zheng.news.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * NoticeRepository
 * zheng
 **/
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

    Notice findTopByOrderByIdDesc();
}

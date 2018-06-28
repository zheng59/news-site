package com.zheng.news.repositories;

import com.zheng.news.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ContractRepositpry
 * zheng
 **/
public interface ContactRepository extends JpaRepository<Contact,Integer> {
}

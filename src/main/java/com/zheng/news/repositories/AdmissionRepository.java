package com.zheng.news.repositories;

import com.zheng.news.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * ImageRepository
 * zheng
 **/
public interface AdmissionRepository extends JpaRepository<Admission,Integer> {

    Admission findByCardNumber(String cardNumber);
}

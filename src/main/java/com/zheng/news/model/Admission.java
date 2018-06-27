package com.zheng.news.model;

import com.zheng.news.validation.Insert;
import com.zheng.news.validation.Update;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 准考证模型
 * zheng
 **/
@Entity
@Table(

)
public class Admission {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Lob
    private String base64;

    private String cardNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}

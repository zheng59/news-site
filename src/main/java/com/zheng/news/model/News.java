package com.zheng.news.model;


import javax.persistence.*;
import java.time.ZonedDateTime;

import static javax.persistence.ConstraintMode.NO_CONSTRAINT;

/**
 * 新闻类
 * zheng
 **/
@Entity
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String title;

    @Lob
    private String content;

    private ZonedDateTime createdAt;

    private String source;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(NO_CONSTRAINT))
    private NewsCategory category;

    private NewsStatus status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public NewsCategory getCategory() {
        return category;
    }

    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    public NewsStatus getStatus() {
        return status;
    }

    public void setStatus(NewsStatus status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

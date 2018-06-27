package com.zheng.news.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 轮播图
 * Slideshow
 * zheng
 **/

@Entity
public class Slideshow {

    @NotNull
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Lob
    private String base64Image;

    private String text;

    @NotNull
    private String url;

    /**
     *  所在位置 如果存在 覆盖原来
     */
    private Integer location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }
}

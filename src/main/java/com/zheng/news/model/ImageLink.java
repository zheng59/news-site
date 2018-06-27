package com.zheng.news.model;

import javax.persistence.*;

/**
 * ImageLink
 * zheng
 **/
@Entity
public class ImageLink {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Lob
    private String base64Image;

    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

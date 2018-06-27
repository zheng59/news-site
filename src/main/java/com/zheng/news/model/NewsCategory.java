package com.zheng.news.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;


/**
 * 新闻类别
 * NewsCategory
 * zheng
 **/
@Entity
public class NewsCategory {

    public static Integer  TOP_CATEGORY = -1;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer parentId;

    @javax.persistence.Transient
    private Collection<NewsCategory> Children;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Collection<NewsCategory> getChildren() {
        return Children;
    }

    public void setChildren(Collection<NewsCategory> children) {
        Children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCategory that = (NewsCategory) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, parentId);
    }
}

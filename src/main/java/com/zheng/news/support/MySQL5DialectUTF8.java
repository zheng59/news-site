package com.zheng.news.support;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * hibernate
 * zheng
 **/
public class MySQL5DialectUTF8 extends MySQL5Dialect {

    @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}

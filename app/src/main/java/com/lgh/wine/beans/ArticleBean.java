package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/7/18.
 * 模块：
 */
public class ArticleBean implements Serializable{
    private String article_id;
    private String article_title;
    private String article_author;
    private long create_time;
    private String article_abstract;// 摘要
    private String article_content;
    private String article_reading;//读数
    private String article_icon;
    private String article_url;
    private boolean if_articel;//是否点赞
    private String comment_num;//评论数量
    private String article_like;

    public String getArticle_like() {
        return article_like;
    }

    public void setArticle_like(String article_like) {
        this.article_like = article_like;
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getArticle_abstract() {
        return article_abstract;
    }

    public void setArticle_abstract(String article_abstract) {
        this.article_abstract = article_abstract;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_reading() {
        return article_reading;
    }

    public void setArticle_reading(String article_reading) {
        this.article_reading = article_reading;
    }

    public String getArticle_icon() {
        return article_icon;
    }

    public void setArticle_icon(String article_icon) {
        this.article_icon = article_icon;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public boolean isIf_articel() {
        return if_articel;
    }

    public void setIf_articel(boolean if_articel) {
        this.if_articel = if_articel;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }
}

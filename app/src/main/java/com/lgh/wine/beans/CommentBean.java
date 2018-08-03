package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/7/18.
 * 模块：
 */
public class CommentBean implements Serializable{
    private String user_id;
    private int composite_score;//综合评分
    private int quality_score;// 质量评分
    private int transport_score;//物流评分
    private int serve_score;//服务评分
    private String comment_pics;
    private String comment_content;
    private String is_show;
    private String create_time;
    private String goods_name;
    private String goods_pic;
    private String user_name;
    private String user_avaer;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getComposite_score() {
        return composite_score;
    }

    public void setComposite_score(int composite_score) {
        this.composite_score = composite_score;
    }

    public int getQuality_score() {
        return quality_score;
    }

    public void setQuality_score(int quality_score) {
        this.quality_score = quality_score;
    }

    public int getTransport_score() {
        return transport_score;
    }

    public void setTransport_score(int transport_score) {
        this.transport_score = transport_score;
    }

    public int getServe_score() {
        return serve_score;
    }

    public void setServe_score(int serve_score) {
        this.serve_score = serve_score;
    }

    public String getComment_pics() {
        return comment_pics;
    }

    public void setComment_pics(String comment_pics) {
        this.comment_pics = comment_pics;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_avaer() {
        return user_avaer;
    }

    public void setUser_avaer(String user_avaer) {
        this.user_avaer = user_avaer;
    }
}

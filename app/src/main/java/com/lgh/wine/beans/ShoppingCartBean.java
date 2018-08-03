package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/7/18.
 * 模块：
 */
public class ShoppingCartBean implements Serializable {
    private String cart_id;
    private String goods_name;
    private String goods_pics;
    private String create_time;
    private String user_id;
    private double goods_price;
    private String goods_id;
    private int goods_count;
    private String goods_pic;
    private String grade_id;//定制酒->散酒id
    private String og_id;


    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_pics() {
        return goods_pics;
    }

    public void setGoods_pics(String goods_pics) {
        this.goods_pics = goods_pics;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public int getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(int goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getGrade_id() {
        return grade_id;
    }

    public void setGrade_id(String grade_id) {
        this.grade_id = grade_id;
    }

    public String getOg_id() {
        return og_id;
    }

    public void setOg_id(String og_id) {
        this.og_id = og_id;
    }
}

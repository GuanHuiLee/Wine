package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/8/2.
 * 模块：
 */
public class GoodsDetailBean implements Serializable {
    private String name;
    private double price;
    private int count;
    private String goods_id;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }
}

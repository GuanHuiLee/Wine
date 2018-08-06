package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ProductBean implements Serializable {
    private String detail_pictures;
    private String product_incense;//香型
    private String product_addr;//
    private String product_craft;//工艺
    private String product_save;//存储方式
    private int product_type;
    private String product_brand;
    private String product_degree;//度数
    private String prodcut_producer;//生产厂
    private String product_volume;//规格

    private String product_content;//净含量
    private int if_collect;//是否收藏
    private String product_original;//原价
    private String update_time;
    private String product_id;//规格
    private String product_icon;//图片
    private String product_judge;//评论
    private float product_price;//单价
    private String product_stock;//库存
    private String product_name;
    private String product_pictures;//图片集合
    private String product_sale;//销量
    private String shopping_cart_count;//购物车数量

    public String getDetail_pictures() {
        return detail_pictures;
    }

    public void setDetail_pictures(String detail_pictures) {
        this.detail_pictures = detail_pictures;
    }

    public String getProduct_incense() {
        return product_incense;
    }

    public void setProduct_incense(String product_incense) {
        this.product_incense = product_incense;
    }

    public String getProduct_addr() {
        return product_addr;
    }

    public void setProduct_addr(String product_addr) {
        this.product_addr = product_addr;
    }

    public String getProduct_craft() {
        return product_craft;
    }

    public void setProduct_craft(String product_craft) {
        this.product_craft = product_craft;
    }

    public String getProduct_save() {
        return product_save;
    }

    public void setProduct_save(String product_save) {
        this.product_save = product_save;
    }

    public int getProduct_type() {
        return product_type;
    }

    public void setProduct_type(int product_type) {
        this.product_type = product_type;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_degree() {
        return product_degree;
    }

    public void setProduct_degree(String product_degree) {
        this.product_degree = product_degree;
    }

    public String getProdcut_producer() {
        return prodcut_producer;
    }

    public void setProdcut_producer(String prodcut_producer) {
        this.prodcut_producer = prodcut_producer;
    }

    public String getProduct_volume() {
        return product_volume;
    }

    public void setProduct_volume(String product_volume) {
        this.product_volume = product_volume;
    }

    public String getProduct_content() {
        return product_content;
    }

    public void setProduct_content(String product_content) {
        this.product_content = product_content;
    }

    public int getIf_collect() {
        return if_collect;
    }

    public void setIf_collect(int if_collect) {
        this.if_collect = if_collect;
    }

    public String getProduct_original() {
        return product_original;
    }

    public void setProduct_original(String product_original) {
        this.product_original = product_original;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_icon() {
        return product_icon;
    }

    public void setProduct_icon(String product_icon) {
        this.product_icon = product_icon;
    }

    public String getProduct_judge() {
        return product_judge;
    }

    public void setProduct_judge(String product_judge) {
        this.product_judge = product_judge;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_pictures() {
        return product_pictures;
    }

    public void setProduct_pictures(String product_pictures) {
        this.product_pictures = product_pictures;
    }

    public String getProduct_sale() {
        return product_sale;
    }

    public void setProduct_sale(String product_sale) {
        this.product_sale = product_sale;
    }

    public String getShopping_cart_count() {
        return shopping_cart_count;
    }

    public void setShopping_cart_count(String shopping_cart_count) {
        this.shopping_cart_count = shopping_cart_count;
    }

    @Override
    public String toString() {
        return "ProductBean{" +
                "detail_pictures='" + detail_pictures + '\'' +
                ", product_incense='" + product_incense + '\'' +
                ", product_addr='" + product_addr + '\'' +
                ", product_craft='" + product_craft + '\'' +
                ", product_save='" + product_save + '\'' +
                ", product_type='" + product_type + '\'' +
                ", product_brand='" + product_brand + '\'' +
                ", product_degree='" + product_degree + '\'' +
                ", prodcut_producer='" + prodcut_producer + '\'' +
                ", product_volume='" + product_volume + '\'' +
                ", product_content='" + product_content + '\'' +
                ", if_collect='" + if_collect + '\'' +
                ", product_original='" + product_original + '\'' +
                ", update_time='" + update_time + '\'' +
                ", product_id='" + product_id + '\'' +
                ", product_icon='" + product_icon + '\'' +
                ", product_judge='" + product_judge + '\'' +
                ", product_price='" + product_price + '\'' +
                ", product_stock='" + product_stock + '\'' +
                ", product_name='" + product_name + '\'' +
                ", product_pictures='" + product_pictures + '\'' +
                ", product_sale='" + product_sale + '\'' +
                ", shopping_cart_count='" + shopping_cart_count + '\'' +
                '}';
    }
}

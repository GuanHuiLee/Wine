package com.lgh.wine.beans;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/19.
 * 模块：
 */
public class ProductDetailBean {
    private String product_original;
    private String product_volume;
    private int if_collect;
    private String product_content;
    private double product_price;
    private String product_id;
    private String product_judge;
    private String product_brand;
    private String product_stock;
    private String product_detail_id;
    private String product_craft;
    private String detail_pictures;
    private String product_name;
    private String product_pictures;
    private String product_sale;
    private String product_addr;
    private String product_incense;
    private int product_type;
    private String product_save;
    private int shopping_cart_count;
    private String prodcut_producer;
    private String product_material;
    private String product_icon;
    private int product_degree;

    private List<ProductChildBean> child_list;

    public static class ProductChildBean {
        private String product_original;
        private long create_time;
        private double product_price;
        private String product_name;
        private String product_pictures;
        private String product_sale;
        private long update_time;
        private int product_type;
        private String product_grade;
        private String product_id;
        private int product_judge;
        private String product_icon;
        private String product_stock;
        private String grade_name;


        public String getProduct_original() {
            return product_original;
        }

        public void setProduct_original(String product_original) {
            this.product_original = product_original;
        }

        public long getCreate_time() {
            return create_time;
        }

        public void setCreate_time(long create_time) {
            this.create_time = create_time;
        }

        public double getProduct_price() {
            return product_price;
        }

        public void setProduct_price(double product_price) {
            this.product_price = product_price;
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

        public long getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(long update_time) {
            this.update_time = update_time;
        }

        public int getProduct_type() {
            return product_type;
        }

        public void setProduct_type(int product_type) {
            this.product_type = product_type;
        }

        public String getProduct_grade() {
            return product_grade;
        }

        public void setProduct_grade(String product_grade) {
            this.product_grade = product_grade;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getProduct_judge() {
            return product_judge;
        }

        public void setProduct_judge(int product_judge) {
            this.product_judge = product_judge;
        }

        public String getProduct_icon() {
            return product_icon;
        }

        public void setProduct_icon(String product_icon) {
            this.product_icon = product_icon;
        }

        public String getProduct_stock() {
            return product_stock;
        }

        public void setProduct_stock(String product_stock) {
            this.product_stock = product_stock;
        }

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }
    }


    public String getProduct_original() {
        return product_original;
    }

    public void setProduct_original(String product_original) {
        this.product_original = product_original;
    }

    public String getProduct_volume() {
        return product_volume;
    }

    public void setProduct_volume(String product_volume) {
        this.product_volume = product_volume;
    }

    public int getIf_collect() {
        return if_collect;
    }

    public void setIf_collect(int if_collect) {
        this.if_collect = if_collect;
    }

    public String getProduct_content() {
        return product_content;
    }

    public void setProduct_content(String product_content) {
        this.product_content = product_content;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_judge() {
        return product_judge;
    }

    public void setProduct_judge(String product_judge) {
        this.product_judge = product_judge;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public void setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
    }

    public String getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(String product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_detail_id() {
        return product_detail_id;
    }

    public void setProduct_detail_id(String product_detail_id) {
        this.product_detail_id = product_detail_id;
    }

    public String getProduct_craft() {
        return product_craft;
    }

    public void setProduct_craft(String product_craft) {
        this.product_craft = product_craft;
    }

    public String getDetail_pictures() {
        return detail_pictures;
    }

    public void setDetail_pictures(String detail_pictures) {
        this.detail_pictures = detail_pictures;
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

    public String getProduct_addr() {
        return product_addr;
    }

    public void setProduct_addr(String product_addr) {
        this.product_addr = product_addr;
    }

    public String getProduct_incense() {
        return product_incense;
    }

    public void setProduct_incense(String product_incense) {
        this.product_incense = product_incense;
    }

    public int getProduct_type() {
        return product_type;
    }

    public void setProduct_type(int product_type) {
        this.product_type = product_type;
    }

    public String getProduct_save() {
        return product_save;
    }

    public void setProduct_save(String product_save) {
        this.product_save = product_save;
    }

    public int getShopping_cart_count() {
        return shopping_cart_count;
    }

    public void setShopping_cart_count(int shopping_cart_count) {
        this.shopping_cart_count = shopping_cart_count;
    }

    public String getProdcut_producer() {
        return prodcut_producer;
    }

    public void setProdcut_producer(String prodcut_producer) {
        this.prodcut_producer = prodcut_producer;
    }

    public String getProduct_material() {
        return product_material;
    }

    public void setProduct_material(String product_material) {
        this.product_material = product_material;
    }

    public String getProduct_icon() {
        return product_icon;
    }

    public void setProduct_icon(String product_icon) {
        this.product_icon = product_icon;
    }

    public int getProduct_degree() {
        return product_degree;
    }

    public void setProduct_degree(int product_degree) {
        this.product_degree = product_degree;
    }

    public List<ProductChildBean> getChild_list() {
        return child_list;
    }

    public void setChild_list(List<ProductChildBean> child_list) {
        this.child_list = child_list;
    }
}

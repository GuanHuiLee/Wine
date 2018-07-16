package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class BannerBean {
    private String banner_id;
    private String banner_type;//状态
    private String banner_status;//类型
    private String banner_image;//图片地址
    private String banner_url;//跳转地址

    public String getBanner_id() {
        return banner_id;
    }

    public void setBanner_id(String banner_id) {
        this.banner_id = banner_id;
    }

    public String getBanner_type() {
        return banner_type;
    }

    public void setBanner_type(String banner_type) {
        this.banner_type = banner_type;
    }

    public String getBanner_status() {
        return banner_status;
    }

    public void setBanner_status(String banner_status) {
        this.banner_status = banner_status;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }


    @Override
    public String toString() {
        return "BannerBean{" +
                "banner_id='" + banner_id + '\'' +
                ", banner_type='" + banner_type + '\'' +
                ", banner_status='" + banner_status + '\'' +
                ", banner_image='" + banner_image + '\'' +
                ", banner_url='" + banner_url + '\'' +
                '}';
    }
}

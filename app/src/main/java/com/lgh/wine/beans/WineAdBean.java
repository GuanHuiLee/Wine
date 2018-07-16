package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class WineAdBean {
    private String ad_id;
    private String ad_type;
    private String ad_status;
    private String ad_image;

    public String getAd_id() {
        return ad_id;
    }

    public void setAd_id(String ad_id) {
        this.ad_id = ad_id;
    }

    public String getAd_type() {
        return ad_type;
    }

    public void setAd_type(String ad_type) {
        this.ad_type = ad_type;
    }

    public String getAd_status() {
        return ad_status;
    }

    public void setAd_status(String ad_status) {
        this.ad_status = ad_status;
    }

    public String getAd_image() {
        return ad_image;
    }

    public void setAd_image(String ad_image) {
        this.ad_image = ad_image;
    }

    @Override
    public String toString() {
        return "WineAdBean{" +
                "ad_id='" + ad_id + '\'' +
                ", ad_type='" + ad_type + '\'' +
                ", ad_status='" + ad_status + '\'' +
                ", ad_image='" + ad_image + '\'' +
                '}';
    }
}

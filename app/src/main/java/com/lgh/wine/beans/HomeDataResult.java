package com.lgh.wine.beans;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class HomeDataResult {
    private List<BannerBean> bannerList;

    private List<ProductBean> productList;

    private List<WineAdBean> brandWineAdList;//品牌图片
    private List<WineAdBean> bulkWineAdList;//定制
    private List<WineAdBean> customMadeWineAdList;//散酒


    public List<BannerBean> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerBean> bannerList) {
        this.bannerList = bannerList;
    }

    public List<ProductBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductBean> productList) {
        this.productList = productList;
    }

    public List<WineAdBean> getBrandWineAdList() {
        return brandWineAdList;
    }

    public void setBrandWineAdList(List<WineAdBean> brandWineAdList) {
        this.brandWineAdList = brandWineAdList;
    }

    public List<WineAdBean> getBulkWineAdList() {
        return bulkWineAdList;
    }

    public void setBulkWineAdList(List<WineAdBean> bulkWineAdList) {
        this.bulkWineAdList = bulkWineAdList;
    }

    public List<WineAdBean> getCustomMadeWineAdList() {
        return customMadeWineAdList;
    }

    public void setCustomMadeWineAdList(List<WineAdBean> customMadeWineAdList) {
        this.customMadeWineAdList = customMadeWineAdList;
    }

    @Override
    public String toString() {
        return "HomeDataResult{" +
                "bannerList=" + bannerList +
                ", productList=" + productList +
                ", brandWineAdList=" + brandWineAdList +
                ", bulkWineAdList=" + bulkWineAdList +
                ", customMadeWineAdList=" + customMadeWineAdList +
                '}';
    }
}

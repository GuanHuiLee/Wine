package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.model.ProductModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface ProductContract {
    public interface View extends BaseView {
        void showProductList(List<ProductBean> beans);

        void showProductDetail(ProductDetailBean bean);

        void showSpoorList(List<SpoorBean> beans);

        void dealDeleteSpoorListResult();
    }

    public abstract class Presenter extends BasePresenter<View, ProductModel> {

        public Presenter(View view, ProductModel model) {
            super(view, model);
        }

        public abstract void getProductList(Map<String, Object> params);

        public abstract void getProductDetail(Map<String, Object> params);

        public abstract void deleteSpoorList(Map<String, Object> params);

        public abstract void getSpoorList(Map<String, Object> params);
    }
}

package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.model.ProductModel;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ProductContract {
    public interface View extends BaseView {
        void getProductList(List<ProductBean> beans);
    }

    public abstract class Presenter extends BasePresenter<View, ProductModel> {

        public Presenter(View view, ProductModel model) {
            super(view, model);
        }

        public abstract void getProductList(int srartnum, int type);
    }
}

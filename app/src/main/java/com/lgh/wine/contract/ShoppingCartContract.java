package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface ShoppingCartContract {
    public interface View extends BaseView {

        void dealAddShoppingCartResult();

        void showShoppingCart(List<ShoppingCartBean> list);

        void dealDeleteShoppingCartResult();
    }

    public abstract class Presenter extends BasePresenter<View, ShoppingCartModel> {

        public Presenter(View view, ShoppingCartModel model) {
            super(view, model);
        }


        public abstract void addShoppingCart(Map<String, Object> params);

        public abstract void getShoppingCartList(Map<String, Object> params);

        public abstract void deleteShoppingCartInfo(Map<String, Object> params);
    }
}

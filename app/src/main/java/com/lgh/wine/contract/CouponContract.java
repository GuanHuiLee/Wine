package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.model.ProductModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface CouponContract {
    public interface View extends BaseView {
        void showCouponList(List<CouponBean> beans);

        void dealAddUserCouponResult();

        void showUserCouponList(List<CouponBean> beans);

        void showUserCouponByPrice(CouponBean bean);
    }

    public abstract class Presenter extends BasePresenter<View, CouponModel> {

        public Presenter(View view, CouponModel model) {
            super(view, model);
        }

        public abstract void getCouponList(String userId);

        public abstract void addUserCoupon(String userId, String couponId);

        public abstract void getUserCouponList(String userId, int type);

        public abstract void getUserCouponByPrice(String userId, float price);
    }
}

package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.HomeDataResult;
import com.lgh.wine.model.HomeModel;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface HomeContract {
    public interface View extends BaseView {
        void showHomeData(HomeDataResult result);
    }
    public abstract class Presenter extends BasePresenter<View,HomeModel>{

        public Presenter(View view, HomeModel model) {
            super(view, model);

        }
        public abstract void getHomeData();
    }
}

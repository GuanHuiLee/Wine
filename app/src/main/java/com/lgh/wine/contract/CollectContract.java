package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface CollectContract {
    public interface View extends BaseView {

        void dealCollectResult();
    }

    public abstract class Presenter extends BasePresenter<View, CollectModel> {

        public Presenter(View view, CollectModel model) {
            super(view, model);
        }


        public abstract void addCollect(Map<String, Object> params);
    }
}

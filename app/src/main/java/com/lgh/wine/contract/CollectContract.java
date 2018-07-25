package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.CollectBean;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface CollectContract {
    public interface View extends BaseView {

        void dealAddCollectResult();

        void showCollectList(List<CollectBean> list);

        void dealDeleteCollect();
    }

    public abstract class Presenter extends BasePresenter<View, CollectModel> {

        public Presenter(View view, CollectModel model) {
            super(view, model);
        }


        public abstract void addCollect(Map<String, Object> params);

        public abstract void deleteCollect(Map<String, Object> params);

        public abstract void getCollect(Map<String, Object> params);
    }
}

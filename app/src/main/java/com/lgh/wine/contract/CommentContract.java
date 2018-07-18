package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.model.CommentModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface CommentContract {
    public interface View extends BaseView {

        void showCommentList(List<CommentBean> list);
    }

    public abstract class Presenter extends BasePresenter<View, CommentModel> {

        public Presenter(View view, CommentModel model) {
            super(view, model);
        }


        public abstract void getCommentList(Map<String, Object> params);
    }
}

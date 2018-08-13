package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.beans.UserArticleBean;
import com.lgh.wine.model.ArticleModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface ArticleContract {
    public interface View extends BaseView {

        void showArticleList(List<ArticleBean> list);

        void showUserArticleList(List<UserArticleBean> list);

        void showArticleDetail(ArticleBean bean);

        void dealAddUserResult();
    }

    public abstract class Presenter extends BasePresenter<View, ArticleModel> {

        public Presenter(View view, ArticleModel model) {
            super(view, model);
        }


        public abstract void getArticleList(Map<String, Object> params);

        public abstract void getUserArticleList(Map<String, Object> params);

        public abstract void getArticleDetail(Map<String, Object> params);

        public abstract void addUserArticle(Map<String, Object> params);
    }
}

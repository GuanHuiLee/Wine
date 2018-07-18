package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.contract.ArticleContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.ArticleModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ArticlePresenter extends ArticleContract.Presenter {

    public ArticlePresenter(ArticleContract.View view, ArticleModel model) {
        super(view, model);
    }

    @Override
    public void getArticleList(Map<String, Object> params) {
        model.getArticleList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        List<ArticleBean> list = new Gson().fromJson(body.getData(), new TypeToken<List<ArticleBean>>() {
                        }.getType());
                        view.showArticleList(list);
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.hideProgress();
                    view.showError(message);
                }
            }
        });
    }

    @Override
    public void getArticleDetail(Map<String, Object> params) {
        model.getArticleDetail(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        ArticleBean bean = new Gson().fromJson(body.getData(), ArticleBean.class);
                        view.showArticleDetail(bean);
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.hideProgress();
                    view.showError(message);
                }
            }
        });
    }

    @Override
    public void addUserArticle(Map<String, Object> params) {
        model.addUserArticle(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddUserResult();
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.hideProgress();
                    view.showError(message);
                }
            }
        });
    }
}
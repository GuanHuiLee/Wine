package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.contract.CommentContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.CommentModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CommentPresenter extends CommentContract.Presenter {

    public CommentPresenter(CommentContract.View view, CommentModel model) {
        super(view, model);
    }

    @Override
    public void getCommentList(Map<String, Object> params) {
        model.getCommentList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        List<CommentBean> list = new Gson().fromJson(body.getData(), new TypeToken<List<CommentBean>>() {
                        }.getType());
                        view.showCommentList(list);
                    } else view.showError(body.getMsg());
                    view.hideProgress();
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.showError(message);
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void addComment(Map<String, Object> params) {
        model.addComment(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddCommentResult();
                    } else view.showError(body.getMsg());
                    view.hideProgress();
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.showError(message);
                    view.hideProgress();
                }
            }
        });
    }
}

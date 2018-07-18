package com.lgh.wine.presenter;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.contract.CollectContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CollectPresenter extends CollectContract.Presenter {

    public CollectPresenter(CollectContract.View view, CollectModel model) {
        super(view, model);
    }

    @Override
    public void addCollect(Map<String, Object> params) {
        model.addCollect(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealCollectResult();
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

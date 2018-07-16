package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.HomeDataResult;
import com.lgh.wine.contract.HomeContract;
import com.lgh.wine.model.HomeModel;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class HomePresenter extends HomeContract.Presenter {
    public HomePresenter(HomeContract.View view, HomeModel model) {
        super(view, model);
    }

    @Override
    public void getHomeData() {
        model.getHomeData(new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                BaseResult<String> body = response.body();
                if (body.getCode() == 200) {
                    HomeDataResult result = new Gson().fromJson(body.getData(), HomeDataResult.class);
                    view.showHomeData(result);
                } else view.showError(body.getMsg());
            }

            @Override
            public void onFail(String message) {
                view.showError(message);

            }
        });
    }
}

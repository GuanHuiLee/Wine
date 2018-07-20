package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ShoppingCartPresenter extends ShoppingCartContract.Presenter {

    public ShoppingCartPresenter(ShoppingCartContract.View view, ShoppingCartModel model) {
        super(view, model);
    }

    @Override
    public void addShoppingCart(Map<String, Object> params) {
        model.addShoppingCart(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddShoppingCartResult();
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
    public void getShoppingCartList(Map<String, Object> params) {
        model.getShoppingCartList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        List<ShoppingCartBean> list = new Gson().fromJson(body.getData(), new TypeToken<List<ShoppingCartBean>>() {
                        }.getType());
                        view.showShoppingCart(list);
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
    public void deleteShoppingCartInfo(Map<String, Object> params) {
        model.deleteShoppingCartInfo(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealDeleteShoppingCartResult();
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

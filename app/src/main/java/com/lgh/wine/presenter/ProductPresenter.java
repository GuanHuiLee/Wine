package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.model.ProductModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ProductPresenter extends ProductContract.Presenter {

    public ProductPresenter(ProductContract.View view, ProductModel model) {
        super(view, model);
    }

    @Override
    public void getProductList(Map<String, Object> params) {
        model.getProductList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        List<ProductBean> list = new Gson().fromJson(data, new TypeToken<List<ProductBean>>() {
                        }.getType());
                        view.showProductList(list);
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
    public void getProductDetail(Map<String, Object> params) {
        view.showProgress("加载中");
        model.getProductDetail(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        ProductDetailBean bean = new Gson().fromJson(data, ProductDetailBean.class);
                        view.showProductDetail(bean);
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

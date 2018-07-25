package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.contract.UploadFileContract;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.model.UploadFileModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class UploadFilePresenter extends UploadFileContract.Presenter {

    public UploadFilePresenter(UploadFileContract.View view, UploadFileModel model) {
        super(view, model);
    }

    @Override
    public void uploadFile(String params) {
        model.uploadFile(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealUploadFileResult(body.getData());
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

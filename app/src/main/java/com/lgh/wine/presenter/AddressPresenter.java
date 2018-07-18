package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.AddressContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.AddressModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class AddressPresenter extends AddressContract.Presenter {

    public AddressPresenter(AddressContract.View view, AddressModel model) {
        super(view, model);
    }


    @Override
    public void getAddressList(Map<String, Object> params) {
        model.getAddressList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        List<AddressBean> list = new Gson().fromJson(body.getData(), new TypeToken<List<AddressBean>>() {
                        }.getType());
                        view.showAddressList(list);
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
    public void getDefaultAddress(Map<String, Object> params) {
        model.getDefaultAddress(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        AddressBean bean = new Gson().fromJson(body.getData(), AddressBean.class);
                        view.showDefaultAddress(bean);
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
    public void addAddress(Map<String, Object> params) {
        model.addAddress(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddAddressResult();
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
    public void deleteAddress(Map<String, Object> params) {
        model.deleteAddress(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    view.hideProgress();
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealDeleteAddressResult();
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

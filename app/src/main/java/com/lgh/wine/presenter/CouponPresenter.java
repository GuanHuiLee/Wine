package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.contract.CouponContract;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.model.ProductModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CouponPresenter extends CouponContract.Presenter {

    public CouponPresenter(CouponContract.View view, CouponModel model) {
        super(view, model);
    }

    @Override
    public void getCouponList(String userId) {
        model.getCouponList(userId, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        List<CouponBean> list = new Gson().fromJson(data, new TypeToken<List<CouponBean>>() {
                        }.getType());
                        view.showCouponList(list);
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
    public void addUserCoupon(String userId, String couponId) {
        model.addUserCoupon(userId, couponId, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddUserCouponResult();
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
    public void getUserCouponList(String userId, int type) {
        model.getUserCouponList(userId, type, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        List<CouponBean> list = new Gson().fromJson(data, new TypeToken<List<CouponBean>>() {
                        }.getType());
                        view.showUserCouponList(list);
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
    public void getUserCouponByPrice(String userId, float price) {
        model.getUserCouponByPrice(userId, price, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        CouponBean bean = new Gson().fromJson(data, CouponBean.class);
                        view.showUserCouponByPrice(bean);
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

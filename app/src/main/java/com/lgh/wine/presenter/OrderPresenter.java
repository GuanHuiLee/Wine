package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

import retrofit2.Response;

/**
 * Created by ligh on 2018/7/16.
 * 模块：
 */
public class OrderPresenter extends OrderContract.Presenter {

    public OrderPresenter(OrderContract.View view, OrderModel model) {
        super(view, model);
    }

    @Override
    public void addOrder(Map<String, Object> params) {
        view.showProgress("加载中");
        model.addOrder(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealAddOrderResult(body.getData());
                    } else if (body.getCode() == 1001) {
                        view.showCodeError(body.getMsg());
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
    public void getOrderDetail(Map<String, Object> params) {
        model.getOrderDetail(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        OrderBean bean = new Gson().fromJson(body.getData(), OrderBean.class);
                        view.showOrderDetail(bean);
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
    public void getOrderList(Map<String, Object> params) {
        model.getOrderList(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        List<OrderBean> bean = new Gson().fromJson(body.getData(), new TypeToken<List<OrderBean>>() {
                        }.getType());
                        view.showOrderList(bean);
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
    public void getOrderStatusNum(Map<String, Object> params) {
        model.getOrderStatusNum(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        OrderStatusBean bean = new Gson().fromJson(body.getData(), OrderStatusBean.class);
                        view.showOrderStatusNum(bean);
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
    public void deleteOrder(Map<String, Object> params) {
        model.deleteOrder(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealDeleteOrderResult();
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
    public void updateOrder(Map<String, Object> params) {
        model.updateOrder(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.dealUpdateOrderResult();
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
    public void getPaySign(Map<String, Object> params) {
        model.getPaySign(params, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.showPaySign(body.getData());
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
    public void getTracker(Map<String, Object> par) {
        model.getTracker(par, new MyCallBack<TrackerBean>() {
            @Override
            public void onSuc(Response<TrackerBean> response) {
                if (isAttach) {
                    TrackerBean body = response.body();
                    TrackerBean.MetaBean meta = body.getMeta();
                    if (meta.getCode() == 200) {
                        view.showTracker(body.getData());
                    } else view.showError(meta.getMessage());
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

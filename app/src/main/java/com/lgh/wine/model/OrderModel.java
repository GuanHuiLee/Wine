package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class OrderModel extends BaseModel {
    private static OrderModel model;

    public static OrderModel newInstance() {
        if (model == null)
            model = new OrderModel();
        return model;
    }

    public void addOrder(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().addOrder(params).enqueue(callBack);
    }

    public void getOrderDetail(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getOrderDetail(params).enqueue(callBack);
    }

    public void getOrderList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getOrderList(params).enqueue(callBack);
    }

    public void getOrderStatusNum(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getOrderStatusNum(params).enqueue(callBack);
    }

    public void deleteOrder(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().deleteOrder(params).enqueue(callBack);
    }

    public void updateOrder(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().updateOrder(params).enqueue(callBack);
    }

    public void getPaySign(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getPaySign(params).enqueue(callBack);
    }
}

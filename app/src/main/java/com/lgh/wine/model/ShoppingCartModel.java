package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ShoppingCartModel extends BaseModel {
    private static ShoppingCartModel model;

    public static ShoppingCartModel newInstance() {
        if (model == null)
            model = new ShoppingCartModel();
        return model;
    }

    public void addShoppingCart(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().addShoppingCart(params).enqueue(callBack);
    }

    public void getShoppingCartList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getShoppingCartList(params).enqueue(callBack);
    }

    public void deleteShoppingCartInfo(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().deleteShoppingCartInfo(params).enqueue(callBack);
    }
}

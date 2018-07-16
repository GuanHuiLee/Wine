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
public class ProductModel extends BaseModel {
    private static ProductModel model;

    public static ProductModel newInstance() {
        if (model == null)
            model = new ProductModel();
        return model;
    }

    public void getProductList(int startNum, int type, int order, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();

        ApiFactory.getService().getProductList(params).enqueue(callBack);
    }
}

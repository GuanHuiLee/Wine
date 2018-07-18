package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CollectModel extends BaseModel {
    private static CollectModel model;

    public static CollectModel newInstance() {
        if (model == null)
            model = new CollectModel();
        return model;
    }

    public void addCollect(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().addCollect(params).enqueue(callBack);
    }

}

package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class HomeModel extends BaseModel {
    private static HomeModel model;

    public static HomeModel newInstance() {
        if (model == null)
            model = new HomeModel();
        return model;
    }

    public void getHomeData(MyCallBack callBack) {
        ApiFactory.getService().getHomeData().enqueue(callBack);
    }
}

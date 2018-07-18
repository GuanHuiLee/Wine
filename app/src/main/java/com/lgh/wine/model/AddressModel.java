package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class AddressModel extends BaseModel {
    private static AddressModel model;

    public static AddressModel newInstance() {
        if (model == null)
            model = new AddressModel();
        return model;
    }

    public void getAddressList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getAddressList(params).enqueue(callBack);
    }

    public void getDefaultAddress(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getDefaultAddress(params).enqueue(callBack);
    }

    public void addAddress(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().addAddress(params).enqueue(callBack);
    }

    public void deleteAddress(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().deleteAddress(params).enqueue(callBack);
    }
}

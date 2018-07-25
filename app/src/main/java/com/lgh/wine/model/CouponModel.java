package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;
import com.lgh.wine.utils.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CouponModel extends BaseModel {
    private static CouponModel model;

    public static CouponModel newInstance() {
        if (model == null)
            model = new CouponModel();
        return model;
    }

    public void getCouponList(String userId, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, userId);
        ApiFactory.getService().getCouponList(params).enqueue(callBack);
    }

    public void addUserCoupon(String userId, String couponId, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, userId);
        params.put("coupon_id", couponId);
        ApiFactory.getService().addUserCoupon(params).enqueue(callBack);
    }

    public void getUserCouponList(String userId, int type, MyCallBack callBack) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, userId);
        params.put("is_used", type);
        ApiFactory.getService().getUserCouponList(params).enqueue(callBack);
    }
}

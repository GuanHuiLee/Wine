package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.api.ApiService;
import com.lgh.wine.api.HttpLoggingInterceptor;
import com.lgh.wine.base.BaseModel;
import com.lgh.wine.utils.Constant;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public void getTracker(Map<String, Object> params, MyCallBack callBack) {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .build();

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.TRACKER_IP)
                .client(mClient)//设置读写连接超时
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = mRetrofit.create(ApiService.class);
        apiService.getTrackerList(Constant.TRACKER_KEY,params).enqueue(callBack);
    }
}

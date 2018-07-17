package com.lgh.wine.api;


import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.beans.SmsCodeInput;
import com.lgh.wine.beans.VerifyPhoneInput;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ligh on 2018/7/6.
 * 模块：
 */
public interface ApiService {
    /**
     * 获取验证码
     *
     * @return
     */
    @POST("sms_code")
    @FormUrlEncoded
    Call<BaseResult<String>> getSmsCode(@FieldMap Map<String, Object> params);


    /**
     * 登录
     *
     * @param
     * @return
     */
    @POST("login")
    @FormUrlEncoded
    Call<BaseResult<String>> login(@FieldMap Map<String, Object> params);

    /**
     * 验证手机号
     *
     * @return
     */
    @POST("verify_code")
    @FormUrlEncoded
    Call<BaseResult<String>> verifyCode(@FieldMap Map<String, Object> params);


    /**
     * 注册
     *
     * @return
     */
    @POST("register")
    @FormUrlEncoded
    Call<BaseResult<String>> register(@FieldMap Map<String, Object> params);


    /**
     * 首页
     *
     * @return
     */
    @POST("index")
    Call<BaseResult<String>> getHomeData();

    /**
     * 商品列表
     *
     * @param params
     * @return
     */
    @POST("productList")
    @FormUrlEncoded
    Call<BaseResult<String>> getProductList(@FieldMap Map<String, Object> params);


    @POST("productDetail")
    @FormUrlEncoded
    Call<BaseResult<String>> getProductDetail(@FieldMap Map<String, Object> params);
}

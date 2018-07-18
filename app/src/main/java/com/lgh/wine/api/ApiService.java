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


    /**
     * 商品详情
     *
     * @param params
     * @return
     */
    @POST("productDetail")
    @FormUrlEncoded
    Call<BaseResult<String>> getProductDetail(@FieldMap Map<String, Object> params);

    /**
     * 获取优惠劵
     *
     * @param params
     * @return
     */
    @POST("couponList")
    @FormUrlEncoded
    Call<BaseResult<String>> getCouponList(@FieldMap Map<String, Object> params);


    /**
     * 用户领取优惠券
     *
     * @param params
     * @return
     */
    @POST("add_user_coupon_info")
    @FormUrlEncoded
    Call<BaseResult<String>> addUserCoupon(@FieldMap Map<String, Object> params);

    /**
     * 添加到购物车
     *
     * @param params
     * @return
     */
    @POST("addShoppingCartInfo")
    @FormUrlEncoded
    Call<BaseResult<String>> addShoppingCart(@FieldMap Map<String, Object> params);


    /**
     * 添加收藏
     *
     * @param params
     * @return
     */
    @POST("addCollectProductInfo")
    @FormUrlEncoded
    Call<BaseResult<String>> addCollect(@FieldMap Map<String, Object> params);

    /**
     * 评论列表
     *
     * @param params
     * @return
     */
    @POST("select_goods_comment_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getCommentList(@FieldMap Map<String, Object> params);

    /**
     * 获取发现
     *
     * @param params
     * @return
     */
    @POST("article_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getArticleList(@FieldMap Map<String, Object> params);

    /**
     * 文章详情
     *
     * @param params
     * @return
     */
    @POST("article_detail_by_article_id")
    @FormUrlEncoded
    Call<BaseResult<String>> getArticleDetail(@FieldMap Map<String, Object> params);

    /**
     * 点赞
     *
     * @param params
     * @return
     */
    @POST("add_user_article")
    @FormUrlEncoded
    Call<BaseResult<String>> addUserArticle(@FieldMap Map<String, Object> params);


    /**
     * 查询购物车列表
     *
     * @param params
     * @return
     */
    @POST("shoppingCartList")
    @FormUrlEncoded
    Call<BaseResult<String>> getShoppingCartList(@FieldMap Map<String, Object> params);

    /**
     * 获取地址列表
     *
     * @param params
     * @return
     */
    @POST("address")
    @FormUrlEncoded
    Call<BaseResult<String>> getAddressList(@FieldMap Map<String, Object> params);

    /**
     * 获取默认地址
     *
     * @param params
     * @return
     */
    @POST("select_default_address")
    @FormUrlEncoded
    Call<BaseResult<String>> getDefaultAddress(@FieldMap Map<String, Object> params);

    /**
     * 添加&修改地址
     *
     * @param params
     * @return
     */
    @POST("addAddress")
    @FormUrlEncoded
    Call<BaseResult<String>> addAddress(@FieldMap Map<String, Object> params);


    /**
     * 删除地址
     *
     * @param params
     * @return
     */
    @POST("deleteAddress")
    @FormUrlEncoded
    Call<BaseResult<String>> deleteAddress(@FieldMap Map<String, Object> params);
}

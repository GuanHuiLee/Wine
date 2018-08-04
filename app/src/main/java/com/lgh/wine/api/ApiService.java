package com.lgh.wine.api;


import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.beans.SmsCodeInput;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.beans.VerifyPhoneInput;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
     * 收藏列表
     *
     * @param params
     * @return
     */
    @POST("collectProductList")
    @FormUrlEncoded
    Call<BaseResult<String>> getCollect(@FieldMap Map<String, Object> params);

    /**
     * 收藏列表
     *
     * @param params
     * @return
     */
    @POST("deleteCollectProductInfo")
    @FormUrlEncoded
    Call<BaseResult<String>> deleteCollect(@FieldMap Map<String, Object> params);

    /**
     * 获取商品评论列表
     *
     * @param params
     * @return
     */
    @POST("select_goods_comment_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getGoodsCommentList(@FieldMap Map<String, Object> params);


    /**
     * 获取用户评价列表
     *
     * @param params
     * @return
     */
    @POST("select_order_comment_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getOrderCommentList(@FieldMap Map<String, Object> params);

    /**
     * 商品评价
     *
     * @param params
     * @return
     */
    @POST("add_goods_comment")
    @FormUrlEncoded
    Call<BaseResult<String>> addComment(@FieldMap Map<String, Object> params);

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

    /**
     * 删除购物车商品
     *
     * @param params
     * @return
     */
    @POST("deleteShoppingCartInfo")
    @FormUrlEncoded
    Call<BaseResult<String>> deleteShoppingCartInfo(@FieldMap Map<String, Object> params);

    /**
     * 浏览记录
     *
     * @param params
     * @return
     */
    @POST("spoorProductList")
    @FormUrlEncoded
    Call<BaseResult<String>> getSpoorList(@FieldMap Map<String, Object> params);

    /**
     * 删除浏览记录
     *
     * @param params
     * @return
     */
    @POST("deleteSpoorProductInfo")
    @FormUrlEncoded
    Call<BaseResult<String>> deleteSpoorList(@FieldMap Map<String, Object> params);

    /**
     * 反馈
     *
     * @param params
     * @return
     */
    @POST("add_feed_back_info")
    @FormUrlEncoded
    Call<BaseResult<String>> addFeedback(@FieldMap Map<String, Object> params);

    /**
     * 上传文件
     *
     * @return
     */
    @Multipart
    @POST("alogle")
    Call<BaseResult<String>> uploadFile(@Part MultipartBody.Part partList);

    /**
     * 我的优惠券
     *
     * @param params
     * @return
     */
    @POST("user_coupon_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getUserCouponList(@FieldMap Map<String, Object> params);

    /**
     * 根据金额获取优惠券
     *
     * @param params
     * @return
     */
    @POST("user_coupon_ifused")
    @FormUrlEncoded
    Call<BaseResult<String>> getUserCouponByPrice(@FieldMap Map<String, Object> params);

    /**
     * 修改个人信息
     *
     * @param params
     * @return
     */
    @POST("updateUser")
    @FormUrlEncoded
    Call<BaseResult<String>> updateUser(@FieldMap Map<String, Object> params);

    /**
     * 生成订单
     *
     * @param params
     * @return
     */
    @POST("add_user_order_info")
    @FormUrlEncoded
    Call<BaseResult<String>> addOrder(@FieldMap Map<String, Object> params);

    /**
     * 订单详情
     *
     * @param params
     * @return
     */
    @POST("order_detail_info")
    @FormUrlEncoded
    Call<BaseResult<String>> getOrderDetail(@FieldMap Map<String, Object> params);

    /**
     * 订单列表
     *
     * @param params
     * @return
     */
    @POST("user_order_master_list")
    @FormUrlEncoded
    Call<BaseResult<String>> getOrderList(@FieldMap Map<String, Object> params);


    /**
     * 获取订单每个状态的数量
     *
     * @param params
     * @return
     */
    @POST("user_order_status_num")
    @FormUrlEncoded
    Call<BaseResult<String>> getOrderStatusNum(@FieldMap Map<String, Object> params);

    /**
     * 删除订单
     *
     * @param params
     * @return
     */
    @POST("delete_order_info")
    @FormUrlEncoded
    Call<BaseResult<String>> deleteOrder(@FieldMap Map<String, Object> params);

    /**
     * 修改订单状态
     *
     * @param params
     * @return
     */
    @POST("update_order_info")
    @FormUrlEncoded
    Call<BaseResult<String>> updateOrder(@FieldMap Map<String, Object> params);

    /**
     * 获取支付签名（目前只支持支付宝）
     *
     * @param params
     * @return
     */
    @POST("pay_info_sign")
    @FormUrlEncoded
    Call<BaseResult<String>> getPaySign(@FieldMap Map<String, Object> params);

    /**
     * 查询物流
     *
     * @param params
     * @return
     */
    @POST("trackings/realtime")
    @FormUrlEncoded
    Call<TrackerBean> getTrackerList(@Header("Trackingmore-Api-Key") String key, @FieldMap Map<String, Object> params);
}

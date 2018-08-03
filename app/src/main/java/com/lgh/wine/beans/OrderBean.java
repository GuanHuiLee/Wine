package com.lgh.wine.beans;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niujingtong on 2018/8/1.
 * 模块：
 */
public class OrderBean implements Serializable{
    private String order_id;
    private String user_id;
    private String addr_id;
    private float order_amount;
    private int order_status;//0待支付，1待发货，2待收货，3已完成，4待评价，5支付已关闭

    private String pay_status;
    private long create_time;
    private String leave_content;//留言内容
    private int is_present;//是否为礼品订单
    private String waybill;//运单号
    private String carrier_code;//运输商简码
    private String destination_code;//目的国二字简码
    private String invoice_content;//发票内容
    private int invoice_type;//	发票类型：1个人，2单位
    private String invoice_name;//	发票抬头
    private String invoice_number;//纳税人识别号
    private String coupon_id;//	优惠券ID
    private String popularize;//推广码

    private long pay_time;
    private long send_time;
    private long complete_time;
    private long update_time;
    private List<ShoppingCartBean> orderGoodsList;
    private int order_goods_count;
    private String orderAddress;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public float getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(float order_amount) {
        this.order_amount = order_amount;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getLeave_content() {
        return leave_content;
    }

    public void setLeave_content(String leave_content) {
        this.leave_content = leave_content;
    }

    public int getIs_present() {
        return is_present;
    }

    public void setIs_present(int is_present) {
        this.is_present = is_present;
    }

    public String getWaybill() {
        return waybill;
    }

    public void setWaybill(String waybill) {
        this.waybill = waybill;
    }

    public String getCarrier_code() {
        return carrier_code;
    }

    public void setCarrier_code(String carrier_code) {
        this.carrier_code = carrier_code;
    }

    public String getDestination_code() {
        return destination_code;
    }

    public void setDestination_code(String destination_code) {
        this.destination_code = destination_code;
    }

    public String getInvoice_content() {
        return invoice_content;
    }

    public void setInvoice_content(String invoice_content) {
        this.invoice_content = invoice_content;
    }

    public int getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(int invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    public String getCoupon_id() {
        return coupon_id;
    }

    public void setCoupon_id(String coupon_id) {
        this.coupon_id = coupon_id;
    }

    public String getPopularize() {
        return popularize;
    }

    public void setPopularize(String popularize) {
        this.popularize = popularize;
    }

    public long getPay_time() {
        return pay_time;
    }

    public void setPay_time(long pay_time) {
        this.pay_time = pay_time;
    }

    public long getSend_time() {
        return send_time;
    }

    public void setSend_time(long send_time) {
        this.send_time = send_time;
    }

    public long getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(long complete_time) {
        this.complete_time = complete_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public List<ShoppingCartBean> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<ShoppingCartBean> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public int getOrder_goods_count() {
        return order_goods_count;
    }

    public void setOrder_goods_count(int order_goods_count) {
        this.order_goods_count = order_goods_count;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }
}

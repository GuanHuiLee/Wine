package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/8/1.
 * 模块：
 */
public class OrderStatusBean {
    private String order_num_dpj;//待评价
    private String order_num_dfh;//代发货
    private String order_num_dfk;//代付款
    private String order_num_dsh;//代收货

    public String getOrder_num_dpj() {
        return order_num_dpj;
    }

    public void setOrder_num_dpj(String order_num_dpj) {
        this.order_num_dpj = order_num_dpj;
    }

    public String getOrder_num_dfh() {
        return order_num_dfh;
    }

    public void setOrder_num_dfh(String order_num_dfh) {
        this.order_num_dfh = order_num_dfh;
    }

    public String getOrder_num_dfk() {
        return order_num_dfk;
    }

    public void setOrder_num_dfk(String order_num_dfk) {
        this.order_num_dfk = order_num_dfk;
    }

    public String getOrder_num_dsh() {
        return order_num_dsh;
    }

    public void setOrder_num_dsh(String order_num_dsh) {
        this.order_num_dsh = order_num_dsh;
    }
}

package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/8/1.
 * 模块：
 */
public class OrderStatusBean {
    private int order_num_dpj;//待评价
    private int order_num_dfh;//代发货
    private int order_num_dfk;//代付款
    private int order_num_dsh;//代收货

    public int getOrder_num_dpj() {
        return order_num_dpj;
    }

    public void setOrder_num_dpj(int order_num_dpj) {
        this.order_num_dpj = order_num_dpj;
    }

    public int getOrder_num_dfh() {
        return order_num_dfh;
    }

    public void setOrder_num_dfh(int order_num_dfh) {
        this.order_num_dfh = order_num_dfh;
    }

    public int getOrder_num_dfk() {
        return order_num_dfk;
    }

    public void setOrder_num_dfk(int order_num_dfk) {
        this.order_num_dfk = order_num_dfk;
    }

    public int getOrder_num_dsh() {
        return order_num_dsh;
    }

    public void setOrder_num_dsh(int order_num_dsh) {
        this.order_num_dsh = order_num_dsh;
    }
}

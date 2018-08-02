package com.lgh.wine.beans;

import java.io.Serializable;

/**
 * Created by niujingtong on 2018/8/2.
 * 模块：
 */
public class GoodsInfoBean implements Serializable {
    private int goods_count;
    private String goods_id;

    public GoodsInfoBean(int goods_count, String goods_id) {
        this.goods_count = goods_count;
        this.goods_id = goods_id;
    }
}

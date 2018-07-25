package com.lgh.wine.ui.coupon.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgh.wine.R;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.utils.TimeUtils;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CouponUserAdapter extends BaseQuickAdapter<CouponBean, BaseViewHolder> {
    public CouponUserAdapter(int layoutResId, @Nullable List<CouponBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, CouponBean item) {
        helper.setText(R.id.tv_price, item.getCoupon_price()).setText(R.id.tv_title,
                "满" + item.getCoupon_name() + "可用").setText(R.id.tv_time,
                TimeUtils.changeToYMD(item.getBegin_time()) + "到" + TimeUtils.changeToYMD(item.getEnd_time()));
    }

}

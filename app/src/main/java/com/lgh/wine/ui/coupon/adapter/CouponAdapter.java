package com.lgh.wine.ui.coupon.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgh.wine.R;
import com.lgh.wine.api.Constant;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.WineAdBean;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.TimeUtils;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CouponAdapter extends BaseQuickAdapter<CouponBean, BaseViewHolder> {
    public CouponAdapter(int layoutResId, @Nullable List<CouponBean> data) {
        super(layoutResId, data);
    }

    public OnItemButtonclickListener itemclickListener;

    public void setItemclickListener(OnItemButtonclickListener itemclickListener) {
        this.itemclickListener = itemclickListener;
    }

    @Override
    protected void convert(final BaseViewHolder helper, CouponBean item) {
        helper.setText(R.id.tv_price, item.getCoupon_price() + "元").setText(R.id.tv_title,
                item.getCoupon_name()).setText(R.id.tv_time, "使用时间：" +
                TimeUtils.changeToYMD(item.getBegin_time()) + "~" + TimeUtils.changeToYMD(item.getEnd_time()));

        Button btn_get = helper.getView(R.id.btn_get);
        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemclickListener.OnButtonClick(helper.getAdapterPosition());
            }
        });

    }

    public interface OnItemButtonclickListener {
        abstract void OnButtonClick(int position);
    }
}

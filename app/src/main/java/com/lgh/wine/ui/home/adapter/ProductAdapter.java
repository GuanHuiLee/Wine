package com.lgh.wine.ui.home.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgh.wine.R;
import com.lgh.wine.api.Constant;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.WineAdBean;
import com.lgh.wine.utils.GlideHelper;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ProductAdapter extends BaseQuickAdapter<ProductBean, BaseViewHolder> {
    public ProductAdapter(int layoutResId, @Nullable List<ProductBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProductBean item) {
        ImageView iv_pic = helper.getView(R.id.iv_pic);

        GlideHelper.loadImage(mContext, iv_pic, Constant.IMG_IP + item.getProduct_icon());

        helper.setText(R.id.tv_name, item.getProduct_name());
        helper.setText(R.id.tv_price, "￥" + item.getProduct_price());
    }
}

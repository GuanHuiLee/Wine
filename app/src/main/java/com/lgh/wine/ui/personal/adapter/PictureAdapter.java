package com.lgh.wine.ui.personal.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lgh.wine.R;
import com.lgh.wine.beans.WineAdBean;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import java.util.List;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class PictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public PictureAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ImageView iv_pic = helper.getView(R.id.iv_pic);

        GlideHelper.loadImage(mContext, iv_pic, item);
    }
}

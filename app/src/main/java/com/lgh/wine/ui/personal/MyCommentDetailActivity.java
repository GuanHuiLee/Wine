package com.lgh.wine.ui.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class MyCommentDetailActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.tv_content)
    TextView tv_content;

    @BindView(R.id.rb_service)
    MaterialRatingBar rb_service;
    @BindView(R.id.rb_quality)
    MaterialRatingBar rb_quality;
    @BindView(R.id.rb_attitude)
    MaterialRatingBar rb_attitude;

    private CommentBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_comment_detail;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        bean = (CommentBean) getIntent().getSerializableExtra("data");
        if (bean != null) {
            tv_name.setText(bean.getGoods_name());
            GlideHelper.loadImage(mContext, iv_pic, Constant.IMG_IP + bean.getGoods_pic());
            tv_content.setText(bean.getComment_content());

            rb_service.setProgress(bean.getTransport_score());
            rb_quality.setProgress(bean.getQuality_score());
            rb_attitude.setProgress(bean.getServe_score());
        }
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("评价详情");
    }
}

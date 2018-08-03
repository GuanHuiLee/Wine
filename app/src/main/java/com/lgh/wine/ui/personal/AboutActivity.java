package com.lgh.wine.ui.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.VersionUtil;

import butterknife.BindView;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.tv_version)
    TextView tv_version;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }


    @Override
    protected void initUI() {
        GlideHelper.loadImage(mContext, iv_pic, "http://ocr4l9wvc.bkt.clouddn.com/image/app/qrcodenow_android.png");
        tv_version.setText("版本" + VersionUtil.getVerName(mContext));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("关于我们");
    }
}

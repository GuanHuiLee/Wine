package com.lgh.wine.ui.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;

/**
 * 我的点赞
 */
public class MyLikesActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_likes;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("我的点赞");
    }
}

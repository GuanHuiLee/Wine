package com.lgh.wine.ui.personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;

import butterknife.OnClick;

public class AddOrderCommentSuccessActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order_comment_success;
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
        tv_title.setText("评价完成");
    }

    @OnClick(R.id.tv_comment)
    public void clickView(android.view.View view) {
        startActivity(new Intent(mContext, MyCommentListActivity.class));
        finish();
    }
}

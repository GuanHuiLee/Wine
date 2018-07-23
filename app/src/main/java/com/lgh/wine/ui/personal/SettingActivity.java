package com.lgh.wine.ui.personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
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
        tv_title.setText("设置");
    }

    @OnClick({R.id.tv_about})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.tv_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            default:
                break;
        }
    }
}

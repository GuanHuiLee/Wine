package com.lgh.wine.ui.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.wine.Contract.AccountContract;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.presenter.AccountPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements AccountContract.View {
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_password)
    EditText et_password;

    private AccountPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        presenter = new AccountPresenter(this, AccountModel.newInstance());
        addPresenter(presenter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tvTitle = toolbar.findViewById(R.id.toolbar_title);
        TextView tvOther = toolbar.findViewById(R.id.toolbar_other);
        tvTitle.setTextColor(getResources().getColor(android.R.color.black));
        tvOther.setTextColor(getResources().getColor(android.R.color.black));

        tvOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, RegisterActivity.class));
            }
        });
        tvOther.setText("注册");
        tvTitle.setText("账号登录");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
    }

    @OnClick({R.id.btn_login, R.id.tv_quick_login})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_quick_login:
                startActivity(new Intent(mContext, LoginBySmsCodeActivity.class));
                finish();
                break;
        }
    }

    private void login() {
        String phone = et_phone.getText().toString();
        String pwd = et_password.getText().toString();

        presenter.login(new LoginInput(phone, pwd, 1));
    }

    @Override
    public void showLoginInfo(Account data) {

    }

    @Override
    public void showSmsCode(String code) {

    }

    @Override
    public void verifyCodeSuccess() {

    }

    @Override
    public void registerSuccess() {

    }

}

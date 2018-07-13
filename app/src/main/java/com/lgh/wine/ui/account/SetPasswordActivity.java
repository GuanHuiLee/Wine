package com.lgh.wine.ui.account;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.presenter.AccountPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class SetPasswordActivity extends BaseActivity implements AccountContract.View {
    @BindView(R.id.et_password)
    EditText et_password;

    private AccountPresenter presenter;
    private String phone;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_set_password;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        presenter = new AccountPresenter(this, AccountModel.newInstance());
        addPresenter(presenter);
        phone = getIntent().getStringExtra("phone");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tvTitle = toolbar.findViewById(R.id.toolbar_title);
        TextView tvOther = toolbar.findViewById(R.id.toolbar_other);
        tvTitle.setTextColor(getResources().getColor(android.R.color.black));

        tvTitle.setText("设置密码");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
    }

    @OnClick({R.id.btn_done})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_done:
                setPassword();
                break;
        }
    }

    private void setPassword() {
        String pwd = et_password.getText().toString();

        presenter.register(phone, pwd);
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
        startActivity(new Intent(mContext, LoginActivity.class));
        finish();
    }
}

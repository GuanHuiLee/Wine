package com.lgh.wine.ui.personal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.presenter.AccountPresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.ClearEditText;
import com.lgh.wine.utils.Constant;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class UpdatePwdActivity extends BaseActivity implements AccountContract.View {
    private AccountPresenter presenter;
    @BindView(R.id.ct_pwd_now)
    ClearEditText ct_pwd_now;
    @BindView(R.id.ct_pwd_new_2)
    ClearEditText ct_pwd_new_2;
    @BindView(R.id.ct_pwd_new)
    ClearEditText ct_pwd_new;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_update_pwd;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        presenter = new AccountPresenter(this, AccountModel.newInstance());
        addPresenter(presenter);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("修改密码");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("保存");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePwd();
            }
        });
    }

    private void updatePwd() {
        String pwd_new = ct_pwd_new.getText().toString();
        String pwd_new_2 = ct_pwd_new_2.getText().toString();
        String pwd_now = ct_pwd_now.getText().toString();
        Account account = AccountUtil.getAccount();
        if (!pwd_now.equals(account.getUserPassword())) {
            showError("原密码不对，请重新输入");
            return;
        }
        if (!pwd_new.equals(pwd_new_2)) {
            showError("两次输入新密码不一致，请重新输入");
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("user_password", pwd_new);
        presenter.updateUser(params);
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

    @Override
    public void dealAddFeedbackResult() {

    }

    @Override
    public void dealUpdateUserResult() {
        showError("修改成功");
        finish();
    }
}

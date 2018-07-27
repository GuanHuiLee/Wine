package com.lgh.wine.ui.account;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.presenter.AccountPresenter;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginBySmsCodeActivity extends BaseActivity implements AccountContract.View {

    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.tv_getCode)
    TextView tv_getCode;

    private AccountPresenter presenter;
    private static final int START = 1;
    private static final int STOP = 2;
    private int time = 60;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_by_phone_code;
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

    @OnClick({R.id.btn_login, R.id.tv_login, R.id.tv_getCode})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_login:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.tv_getCode:
                getCode();
                break;
        }
    }

    private void getCode() {
        String phone = et_phone.getText().toString();
        presenter.getSmsCode(phone, 0);
    }

    private void login() {
        String phone = et_phone.getText().toString();
        String pwd = et_code.getText().toString();

        presenter.login(phone, pwd, 0);
    }

    @Override
    public void showLoginInfo(Account data) {

    }

    @Override
    public void showSmsCode(String code) {
        showTime();
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

    }

    private void showTime() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (time > 0) {
                    mHandler.sendEmptyMessage(START);
                } else {
                    mHandler.sendEmptyMessage(STOP);
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    tv_getCode.setEnabled(false);
                    tv_getCode.setText("重新获取(" + time + "s)");
                    time--;
                    break;
                case STOP:
                    tv_getCode.setEnabled(true);
                    time = 60;
                    tv_getCode.setText("获取验证码");
                    break;
            }
        }
    };
}

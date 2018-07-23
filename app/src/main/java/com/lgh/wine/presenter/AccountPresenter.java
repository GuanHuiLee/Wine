package com.lgh.wine.presenter;

import com.google.gson.Gson;
import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.MyCallBack;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.model.AccountModel;

import retrofit2.Response;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class AccountPresenter extends AccountContract.Presenter {

    public AccountPresenter(AccountContract.View view, AccountModel model) {
        super(view, model);
    }

    @Override
    public void login(String phone, String pwd, int type) {
        view.showProgress("登录中");
        model.login(phone, pwd, type, new MyCallBack<BaseResult<String>>() {


            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        String data = body.getData();
                        Account account = new Gson().fromJson(data, Account.class);
                        view.showLoginInfo(account);
                    } else view.showError(body.getMsg());
                    view.hideProgress();
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach) {
                    view.showError(message);
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void getSmsCode(String phone, int type) {
        model.getSmsCode(phone, type, new MyCallBack<BaseResult<String>>() {

            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.showSmsCode(body.getData());
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach)
                    view.showError(message);

            }
        });
    }

    @Override
    public void verifyCode(String phone, String sms_code) {
        model.verifyCode(phone, sms_code, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.verifyCodeSuccess();
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach)
                    view.showError(message);

            }
        });
    }

    @Override
    public void register(String phone, String password) {
        model.register(phone, password, new MyCallBack<BaseResult<String>>() {
            @Override
            public void onSuc(Response<BaseResult<String>> response) {
                if (isAttach) {
                    BaseResult<String> body = response.body();
                    if (body.getCode() == 200) {
                        view.registerSuccess();
                    } else view.showError(body.getMsg());
                }
            }

            @Override
            public void onFail(String message) {
                if (isAttach)
                    view.showError(message);

            }
        });
    }
}

package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.LoginInput;
import com.lgh.wine.model.AccountModel;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public interface AccountContract {
    public interface View extends BaseView {

        /**
         * 显示
         */
        void showLoginInfo(Account data);

        void showSmsCode(String code);

        void verifyCodeSuccess();

        void registerSuccess();

    }

    public abstract class Presenter extends BasePresenter<View, AccountModel> {

        public Presenter(View view, AccountModel model) {
            super(view, model);
        }


        public abstract void login(String phone,String pwd,int type);

        public abstract void getSmsCode(String phone, int type);

        public abstract void verifyCode(String phone, String sms_code);

        public abstract void register(String phone, String password);
    }
}

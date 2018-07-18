package com.lgh.wine.utils;

import com.lgh.wine.beans.Account;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class AccountUtil {

    public static String getToken() {
        Account account = getAccount();
        if (account != null) {
            return account.getToken();
        } else return null;
    }

    public static boolean hasAccount() {
        return getAccount() != null;
    }

    public static Account getAccount() {
        Account account = Account.first(Account.class);
        return account;
    }

    public static String getUserId() {
        return getAccount().getUserId();
    }
}

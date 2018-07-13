package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class SmsCodeInput {
    private String phone;
    private int type;//（1：注册获取验证码、0：登录获取验证码）

    public SmsCodeInput(String phone, int type) {
        this.phone = phone;
        this.type = type;
    }
}

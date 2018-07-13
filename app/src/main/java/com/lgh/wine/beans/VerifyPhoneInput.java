package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class VerifyPhoneInput {
    private String phone;
    private String sms_code;

    public VerifyPhoneInput(String phone, String sms_code) {
        this.phone = phone;
        this.sms_code = sms_code;
    }
}

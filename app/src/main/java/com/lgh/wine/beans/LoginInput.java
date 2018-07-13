package com.lgh.wine.beans;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class LoginInput {
    private String phone;
    private String sms_password;//密码或验证码(必传, md5加密)
    private int type;//（1：密码登录、0：验证码登录）

    public LoginInput(String phone, String sms_password, int type) {
        this.phone = phone;
        this.sms_password = sms_password;
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms_password() {
        return sms_password;
    }

    public void setSms_password(String sms_password) {
        this.sms_password = sms_password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

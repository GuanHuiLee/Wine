package com.lgh.wine.beans;

import com.orm.SugarRecord;

/**
 * Created by niujingtong on 2018/7/13.
 * 模块：
 */
public class Account extends SugarRecord {
    private String userId;
    private String token;
    private long updateTime;
    private int userGrade;
    private String userIcon;
    private String userBirthday;
    private String username;
    private String userNickname;
    private String userPassword;
    private String userPhone;
    private String userAddress;
    private String popularize;
    private int userSex;//性别(1男2女)

    public Account() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(int userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(String userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getPopularize() {
        return popularize;
    }

    public void setPopularize(String popularize) {
        this.popularize = popularize;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", updateTime=" + updateTime +
                ", userGrade=" + userGrade +
                ", userIcon='" + userIcon + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", username='" + username + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", popularize='" + popularize + '\'' +
                ", userSex=" + userSex +
                '}';
    }
}

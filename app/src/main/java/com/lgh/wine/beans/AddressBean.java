package com.lgh.wine.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by niujingtong on 2018/7/18.
 * 模块：
 */
public class AddressBean implements Parcelable {
    private String addr_id;
    private String user_id;
    private String addr_cnee;//收件人
    private String addr_phone;
    private String addr_province;
    private String addr_city;
    private String addr_district;// 县
    private String detail_address;
    private boolean is_default;

    protected AddressBean(Parcel in) {
        addr_id = in.readString();
        user_id = in.readString();
        addr_cnee = in.readString();
        addr_phone = in.readString();
        addr_province = in.readString();
        addr_city = in.readString();
        addr_district = in.readString();
        detail_address = in.readString();
        is_default = in.readByte() != 0;
    }

    public static final Creator<AddressBean> CREATOR = new Creator<AddressBean>() {
        @Override
        public AddressBean createFromParcel(Parcel in) {
            return new AddressBean(in);
        }

        @Override
        public AddressBean[] newArray(int size) {
            return new AddressBean[size];
        }
    };

    public boolean getIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    public String getAddr_id() {
        return addr_id;
    }

    public void setAddr_id(String addr_id) {
        this.addr_id = addr_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAddr_cnee() {
        return addr_cnee;
    }

    public void setAddr_cnee(String addr_cnee) {
        this.addr_cnee = addr_cnee;
    }

    public String getAddr_phone() {
        return addr_phone;
    }

    public void setAddr_phone(String addr_phone) {
        this.addr_phone = addr_phone;
    }

    public String getAddr_province() {
        return addr_province;
    }

    public void setAddr_province(String addr_province) {
        this.addr_province = addr_province;
    }

    public String getAddr_city() {
        return addr_city;
    }

    public void setAddr_city(String addr_city) {
        this.addr_city = addr_city;
    }

    public String getAddr_district() {
        return addr_district;
    }

    public void setAddr_district(String addr_district) {
        this.addr_district = addr_district;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(addr_id);
        parcel.writeString(user_id);
        parcel.writeString(addr_cnee);
        parcel.writeString(addr_phone);
        parcel.writeString(addr_province);
        parcel.writeString(addr_city);
        parcel.writeString(addr_district);
        parcel.writeString(detail_address);
        parcel.writeByte((byte) (is_default ? 1 : 0));
    }
}

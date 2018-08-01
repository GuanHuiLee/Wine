package com.lgh.wine.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by niujingtong on 2018/8/1.
 * 模块：
 */
public class InvoiceBean implements Parcelable {
    private int invoice_content;//发票内容：0不需要，1酒水，2明细
    private int invoice_type;//发票类型：1个人，2单位
    private String invoice_name;//单位名称
    private String invoice_number;//纳税人识别号

    protected InvoiceBean(Parcel in) {
        invoice_content = in.readInt();
        invoice_type = in.readInt();
        invoice_name = in.readString();
        invoice_number = in.readString();
    }

    public InvoiceBean() {
    }

    public static final Creator<InvoiceBean> CREATOR = new Creator<InvoiceBean>() {
        @Override
        public InvoiceBean createFromParcel(Parcel in) {
            return new InvoiceBean(in);
        }

        @Override
        public InvoiceBean[] newArray(int size) {
            return new InvoiceBean[size];
        }
    };

    public int getInvoice_content() {
        return invoice_content;
    }

    public void setInvoice_content(int invoice_content) {
        this.invoice_content = invoice_content;
    }

    public int getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(int invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_name() {
        return invoice_name;
    }

    public void setInvoice_name(String invoice_name) {
        this.invoice_name = invoice_name;
    }

    public String getInvoice_number() {
        return invoice_number;
    }

    public void setInvoice_number(String invoice_number) {
        this.invoice_number = invoice_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(invoice_content);
        parcel.writeInt(invoice_type);
        parcel.writeString(invoice_name);
        parcel.writeString(invoice_number);
    }
}

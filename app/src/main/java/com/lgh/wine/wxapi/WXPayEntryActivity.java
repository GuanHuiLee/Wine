package com.lgh.wine.wxapi;

import android.os.Bundle;

import com.lgh.wine.R;
import com.xgr.easypay.activity.WXPayEntryBaseActivity;

public class WXPayEntryActivity extends WXPayEntryBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
    }

    @Override
    public String getWXAppId() {
        return "wx432ba0b2e3addde9";
    }
}

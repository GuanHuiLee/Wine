package com.lgh.wine.ui.product;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.ui.personal.AddressSelectActivity;

import butterknife.OnClick;

/**
 * 生成订单
 */
public class AddOrderActivity extends BaseActivity {
    private static final int SELECT_ADDRESS = 0x001;
    private static final int SELECT_INVOICE = 0x002;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("订单确认");
    }

    @OnClick({R.id.ll_select_address, R.id.ll_invoice, R.id.tv_add_order})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.ll_select_address:
                startActivityForResult(new Intent(mContext, AddressSelectActivity.class), SELECT_ADDRESS);
                break;
            case R.id.ll_invoice:
                startActivityForResult(new Intent(mContext, InvoiceSelectActivity.class), SELECT_INVOICE);
                break;
            case R.id.tv_add_order:
                break;
            default:
                break;
        }
    }
}

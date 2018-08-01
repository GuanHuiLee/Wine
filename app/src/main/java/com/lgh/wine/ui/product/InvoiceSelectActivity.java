package com.lgh.wine.ui.product;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.InvoiceBean;
import com.lgh.wine.ui.personal.AddAddressActivity;
import com.lgh.wine.utils.ClearEditText;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class InvoiceSelectActivity extends BaseActivity {
    @BindView(R.id.rg_type)
    RadioGroup rg_type;
    @BindView(R.id.rg_name)
    RadioGroup rg_name;
    @BindView(R.id.rg_content)
    RadioGroup rg_content;
    @BindView(R.id.rb_personal)
    RadioButton rb_personal;

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.ll_name)
    LinearLayout ll_name;
    @BindView(R.id.ll_remind)
    LinearLayout ll_remind;
    @BindView(R.id.ct_company)
    ClearEditText ct_company;
    @BindView(R.id.ct_code)
    ClearEditText ct_code;

    private InvoiceBean bean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invoice_select;
    }


    @Override
    protected void initUI() {

        rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_paper) {
                    ll_content.setVisibility(View.VISIBLE);
                    ll_name.setVisibility(View.VISIBLE);
                    rb_personal.setChecked(true);
                } else {
                    ll_content.setVisibility(View.GONE);
                    ll_name.setVisibility(View.GONE);
                    ll_remind.setVisibility(View.GONE);
                }
            }
        });
        rg_content.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_wine) {
                    bean.setInvoice_content(1);
                } else {
                    bean.setInvoice_content(2);
                }
            }
        });
        rg_name.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_personal) {
                    ll_remind.setVisibility(View.GONE);
                    bean.setInvoice_type(1);
                } else {
                    ll_remind.setVisibility(View.VISIBLE);
                    bean.setInvoice_type(2);
                }
            }
        });
    }


    @Override
    protected void initData() {
        bean = new InvoiceBean();
        bean.setInvoice_type(1);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("发票信息");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("确定");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setInvoice_name(ct_company.getText().toString());
                bean.setInvoice_number(ct_code.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("data", bean);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}

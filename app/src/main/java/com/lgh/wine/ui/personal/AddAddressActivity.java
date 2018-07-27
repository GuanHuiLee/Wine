package com.lgh.wine.ui.personal;

import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.JsonBean;
import com.lgh.wine.contract.AddressContract;
import com.lgh.wine.model.AddressModel;
import com.lgh.wine.presenter.AddressPresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.ClearEditText;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GetJsonDataUtil;
import com.lgh.wine.utils.PickerDialogHelper;
import com.yanzhenjie.permission.Setting;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import vn.luongvo.widget.iosswitchview.SwitchView;

public class AddAddressActivity extends BaseActivity implements AddressContract.View {
    private static final int MSG_LOAD_DATA = 0x001;
    private static final int MSG_LOAD_SUCCESS = 0x002;
    private static final int MSG_LOAD_FAIL = 0x003;

    @BindView(R.id.ct_city)
    ClearEditText ct_city;
    @BindView(R.id.ct_detail)
    ClearEditText ct_detail;
    @BindView(R.id.ct_name)
    ClearEditText ct_name;
    @BindView(R.id.ct_phone)
    ClearEditText ct_phone;
    @BindView(R.id.switch_view)
    SwitchView switchView;
    @BindView(R.id.btn_delete)
    Button btn_delete;

    private AddressPresenter presenter;
    public static final int TYPE_ADD = 0;
    public static final int TYPE_UPDATE = 1;
    private int type;
    private TextView tv_title;
    private int isDefault = 1;
    private String strCity;
    private AddressBean addressBean;

    private ArrayList<JsonBean> options1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2 = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3 = new ArrayList<>();
    private boolean isLoaded = false;
    private Thread thread;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }


    @Override
    protected void initUI() {

        switchView.setOnCheckedChangeListener(new SwitchView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchView switchView, boolean b) {
                isDefault = b ? 1 : 0;
            }
        });

        type = getIntent().getIntExtra("type", 0);
        addressBean = getIntent().getParcelableExtra("data");
        tv_title.setText(type == TYPE_ADD ? "新增收货地址" : "修改收货地址");
        btn_delete.setVisibility(type == TYPE_ADD ? View.GONE : View.VISIBLE);
        if (type == TYPE_UPDATE) {
            switchView.setChecked(addressBean.getIs_default());
        }
    }

    @Override
    protected void initData() {
        presenter = new AddressPresenter(this, AddressModel.newInstance());
        addPresenter(presenter);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("新增收货地址");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("保存");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAddress();
            }
        });
    }

    private void addAddress() {
        String detail = ct_detail.getText().toString();
        String name = ct_name.getText().toString();
        String city = ct_city.getText().toString();
        String phone = ct_phone.getText().toString();

        if (TextUtils.isEmpty(detail)) {
            showError("请填写详细地址");
            return;
        }
        if (TextUtils.isEmpty(city)) {
            showError("请填写所在区域");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showError("请填写收件人电话");
            return;
        }

        if (TextUtils.isEmpty(name)) {
            showError("请填写收件人姓名");
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        if (type == TYPE_UPDATE)
            params.put("addr_id", addressBean.getAddr_id());
        params.put("type", type);
        params.put("is_default", isDefault);

        params.put("detail_address", detail);
        params.put("addr_region", strCity);
        params.put("addr_phone", phone);
        params.put("addr_cnee", name);
    }

    @Override
    public void dealAddAddressResult() {
        showError("新增地址成功");
        finish();
    }

    @Override
    public void dealDeleteAddressResult() {
        showError("删除地址成功");
        finish();
    }

    @Override
    public void showDefaultAddress(AddressBean bean) {

    }

    @Override
    public void showAddressList(List<AddressBean> list) {

    }

    @OnClick({R.id.ct_city, R.id.btn_delete})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                deleteAddress();
                break;
            case R.id.ct_city:
                if (isLoaded) {
                    showPickerView();
                } else
                    showError("正在加载地址，请稍后");
                break;
            default:
                break;
        }
    }

    private void showPickerView() {
        OptionsPickerView pickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int o1, int o2, int o3, View v) {
                String str1 = options1.get(o1).getPickerViewText();
                String str2 = options2.get(o1).get(o2);
                String str3 = options3.get(o1).get(o2).get(o3);
                strCity = str1 + "|" + str2 + "|" + str3;

                ct_city.setText(str1 + str2 + str3);
            }
        }).setTitleText("城市选择").setSubmitColor(ContextCompat.getColor(mContext, R.color.color_button))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(mContext, R.color.color_button))//取消按钮文字颜色
                .setTextColorCenter(ContextCompat.getColor(mContext, R.color.color_button)).build();//选中颜色

        pickerView.setPicker(options1, options2, options3);
        pickerView.show();
    }

    private void deleteAddress() {
        Map<String, Object> params = new HashMap<>();
        params.put("addr_id", addressBean.getAddr_id());

        presenter.deleteAddress(params);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAIL:
                    showError("加载地址失败");
                    break;
                default:
                    break;
            }
        }
    };

    private void initJsonData() {
        String jsonData = new GetJsonDataUtil().getJson(mContext, "province.json");
        ArrayList<JsonBean> jsonBeans = parseData(jsonData);

        options1 = jsonBeans;

        for (int i = 0; i < jsonBeans.size(); i++) {
            ArrayList<String> cityList = new ArrayList<>();
            ArrayList<ArrayList<String>> province_areaList = new ArrayList<>();
            for (int j = 0; j < jsonBeans.get(i).getCity().size(); j++) {
                String name = jsonBeans.get(i).getCity().get(j).getName();
                cityList.add(name);
                ArrayList<String> city_areaList = new ArrayList<>();
                if (jsonBeans.get(i).getCity().get(j).getArea() == null || jsonBeans.get(i).getCity().get(j).getArea().size() == 0) {
                    city_areaList.add("");
                } else
                    city_areaList.addAll(jsonBeans.get(i).getCity().get(j).getArea());

                province_areaList.add(city_areaList);
            }
            options2.add(cityList);
            options3.add(province_areaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    private ArrayList<JsonBean> parseData(String jsonData) {
        ArrayList<JsonBean> list = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(jsonData);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean jsonBean = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                list.add(jsonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAIL);
        }
        return list;
    }

}

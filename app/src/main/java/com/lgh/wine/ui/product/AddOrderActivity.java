package com.lgh.wine.ui.product;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.InvoiceBean;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.contract.AddressContract;
import com.lgh.wine.contract.CouponContract;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.model.AddressModel;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.presenter.AddressPresenter;
import com.lgh.wine.presenter.CouponPresenter;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.ui.personal.AddressSelectActivity;
import com.lgh.wine.ui.product.adapter.GoodsAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.ClearEditText;
import com.lgh.wine.utils.Constant;
import com.xgr.easypay.EasyPay;
import com.xgr.easypay.alipay.AliPay;
import com.xgr.easypay.alipay.AlipayInfoImpli;
import com.xgr.easypay.callback.IPayCallback;
import com.xgr.easypay.wxpay.WXPay;
import com.xgr.easypay.wxpay.WXPayInfoImpli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 生成订单
 */
public class AddOrderActivity extends BaseActivity implements AddressContract.View,
        CouponContract.View, OrderContract.View {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_invoice_info)
    TextView tv_invoice_info;
    @BindView(R.id.ct_msg)
    ClearEditText ct_msg;
    @BindView(R.id.ct_code)
    ClearEditText ct_code;
    @BindView(R.id.cb_lipin)
    CheckBox cb_lipin;
    @BindView(R.id.tv_total_price)
    TextView tv_product_price;//商品金额
    @BindView(R.id.tv_coupon)
    TextView tv_coupon;
    @BindView(R.id.tv_count_price)
    TextView tv_count_price;//合计金额

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private static final int SELECT_ADDRESS = 0x001;
    private static final int SELECT_INVOICE = 0x002;

    private AddressPresenter addressPresenter;
    private CouponPresenter couponPresenter;
    private OrderPresenter orderPresenter;

    private AddressBean selectAddress;
    private InvoiceBean selectInvoice;
    private CouponBean selectCouponBean;
    private List<AddressBean> list;

    private List<GoodsDetailBean> goodsList;
    private List<Map<String, Object>> goodsInfoBeans = new ArrayList<>();
    private float total_price;
    private float price;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order;
    }


    @Override
    protected void initUI() {
        goodsList = (List<GoodsDetailBean>) getIntent().getSerializableExtra("data");

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GoodsAdapter adapter = new GoodsAdapter(mContext);
        recyclerView.setAdapter(adapter);
        adapter.loadData(goodsList);
        recyclerView.setNestedScrollingEnabled(false);

        for (GoodsDetailBean bean : goodsList) {
            total_price += bean.getCount() * bean.getPrice();
            Map<String, Object> params = new HashMap<>();
            params.put("goods_id", bean.getGoods_id());
            params.put("goods_count", bean.getCount());
            goodsInfoBeans.add(params);
        }

        price = total_price;
        tv_product_price.setText("￥" + total_price);
        tv_count_price.setText("￥" + total_price);
    }

    @Override
    protected void initData() {
        orderPresenter = new OrderPresenter(this, OrderModel.newInstance());
        couponPresenter = new CouponPresenter(this, CouponModel.newInstance());
        addressPresenter = new AddressPresenter(this, AddressModel.newInstance());
        addPresenter(addressPresenter);
        addPresenter(couponPresenter);
        addPresenter(orderPresenter);

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());

        addressPresenter.getAddressList(params);
        couponPresenter.getUserCouponByPrice(AccountUtil.getUserId(), total_price);
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
                Intent intent = new Intent(mContext, AddressSelectActivity.class);
                if (list != null) {
                    intent.putExtra("data", (Serializable) list);
                }
                startActivityForResult(intent, SELECT_ADDRESS);
                break;
            case R.id.ll_invoice:
                startActivityForResult(new Intent(mContext, InvoiceSelectActivity.class), SELECT_INVOICE);
                break;
            case R.id.tv_add_order:
                addOrder();
                break;
            default:
                break;
        }
    }

    private void addOrder() {
        if (selectInvoice == null) {
            selectInvoice = new InvoiceBean();
        }

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("addr_id", selectAddress.getAddr_id());
        params.put("invoice_content", selectInvoice.getInvoice_content());
        params.put("invoice_type", selectInvoice.getInvoice_type());
        params.put("invoice_name", selectInvoice.getInvoice_name());
        params.put("invoice_number", selectInvoice.getInvoice_number());
        params.put("goods_info", new Gson().toJson(goodsInfoBeans));
        params.put("order_amount", price);
        params.put("popularize", ct_code.getText().toString());
        params.put("is_present", cb_lipin.isChecked() ? 1 : 0);
        params.put("leave_content", ct_msg.getText().toString());
        if (selectCouponBean != null)
            params.put("coupon_id", selectCouponBean.getCoupon_id());

        orderPresenter.addOrder(params);
    }

    @Override
    public void dealAddAddressResult() {

    }

    @Override
    public void dealDeleteAddressResult() {

    }

    @Override
    public void showDefaultAddress(AddressBean bean) {

    }

    @Override
    public void showAddressList(List<AddressBean> list) {
        if (list != null && !list.isEmpty()) {
            for (AddressBean addressBean : list) {
                if (addressBean.getIs_default()) {
                    selectAddress = addressBean;
                    break;
                }
            }
            setAddress();
            this.list = list;
        }
    }

    private void setAddress() {
        tv_address.setText(selectAddress.getAddr_province() +
                selectAddress.getAddr_city() + selectAddress.getAddr_district());
        tv_name.setText("收货人：" + selectAddress.getAddr_cnee() + " " + selectAddress.getAddr_phone());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_ADDRESS) {
                selectAddress = data.getParcelableExtra("data");
                if (selectAddress != null) {
                    setAddress();
                }
            } else if (requestCode == SELECT_INVOICE) {
                selectInvoice = data.getParcelableExtra("data");
                int invoice_content = selectInvoice.getInvoice_content();
                if (invoice_content == 0)
                    tv_invoice_info.setText("不需要");
                else if (invoice_content == 1)
                    tv_invoice_info.setText("酒水");
                else tv_invoice_info.setText("明细");
            }
        }
    }

    @Override
    public void showCouponList(List<CouponBean> beans) {

    }

    @Override
    public void dealAddUserCouponResult() {

    }

    @Override
    public void showUserCouponList(List<CouponBean> beans) {

    }

    @Override
    public void showUserCouponByPrice(CouponBean bean) {
        if (bean != null) {
            this.selectCouponBean = bean;
            float coupon_price = Float.parseFloat(bean.getCoupon_price());
            tv_coupon.setText("￥" + coupon_price);

            price = total_price - coupon_price;
            tv_count_price.setText("￥" + price);
        }
    }

    @Override
    public void dealAddOrderResult(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", id);
        params.put("pay_type", 1);//pay_type:1为支付宝2位微信支付

        orderPresenter.getPaySign(params);
    }

    @Override
    public void showOrderDetail(OrderBean bean) {

    }

    @Override
    public void dealDeleteOrderResult() {

    }

    @Override
    public void dealUpdateOrderResult() {

    }

    @Override
    public void showOrderList(List<OrderBean> list) {

    }

    @Override
    public void showOrderStatusNum(OrderStatusBean bean) {

    }

    @Override
    public void showCodeError(String s) {
        new com.adorkable.iosdialog.AlertDialog(mContext).builder().setMsg(s).setTitle("温馨提示").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ct_code.setText("");
                        addOrder();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public void showPaySign(String s) {
        alipay(s);
    }

    @Override
    public void showTracker(TrackerBean.DataBean trackerBean) {

    }

    /**
     * 支付宝支付
     */
    private void alipay(String s) {
        AliPay aliPay = new AliPay();
        //构造支付宝订单实体。一般都是由服务端直接返回。
        AlipayInfoImpli alipayInfoImpli = new AlipayInfoImpli();
        alipayInfoImpli.setOrderInfo(s);
        //策略场景类调起支付方法开始支付，以及接收回调。
        EasyPay.pay(aliPay, this, alipayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                showError("支付成功");
            }

            @Override
            public void failed() {
                showError("支付失败");
            }

            @Override
            public void cancel() {
                showError("支付取消");
            }
        });
    }

    /**
     * 微信支付
     */
    private void wxpay(WXPayInfoImpli wxPayInfoImpli) {
        String wxAppId = "wx432ba0b2e3addde9";
        WXPay wxPay = WXPay.getInstance(this, wxAppId);
        EasyPay.pay(wxPay, this, wxPayInfoImpli, new IPayCallback() {
            @Override
            public void success() {
                showError("支付成功");
            }

            @Override
            public void failed() {
                showError("支付失败");
            }

            @Override
            public void cancel() {
                showError("支付取消");
            }
        });
    }

}

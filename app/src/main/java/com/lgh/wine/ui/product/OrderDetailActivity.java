package com.lgh.wine.ui.product;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.adorkable.iosdialog.AlertDialog;
import com.google.gson.Gson;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.beans.TrackinfoBean;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.ui.personal.TrackerActivity;
import com.lgh.wine.ui.product.adapter.GoodsAdapter;
import com.lgh.wine.ui.product.adapter.OrderAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailActivity extends BaseActivity implements OrderContract.View {
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.tv_code)
    TextView tv_code;
    @BindView(R.id.tv_lipin)
    TextView tv_lipin;
    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.tv_total_money)
    TextView tv_total_money;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_tracker_status)
    TextView tv_tracker_status;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tv_name;

    private String order_id;
    private OrderPresenter presenter;
    private GoodsAdapter adapter;
    private OrderBean orderBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_detail;
    }


    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new GoodsAdapter(mContext);
    }

    @Override
    protected void initData() {
        order_id = getIntent().getStringExtra("data");
        presenter = new OrderPresenter(this, OrderModel.newInstance());
        addPresenter(presenter);

        getOrderDetail();
    }

    private void getOrderDetail() {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", order_id);

        presenter.getOrderDetail(params);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("订单信息");
    }

    @Override
    public void dealAddOrderResult(String id) {

    }

    @Override
    public void showOrderDetail(OrderBean bean) {
        this.orderBean = bean;
        showTrackerDetail(bean);

        showAddress(bean);

        showProduct(bean);

        setMsg(bean);
    }

    private void setMsg(OrderBean bean) {
        float order_amount = bean.getOrder_amount();
        tv_price.setText("￥" + order_amount);
        tv_total_money.setText("￥" + order_amount);
        tv_type.setText(bean.getInvoice_type() == 1 ? "个人" : "单位");
        tv_code.setText(bean.getInvoice_number());
        tv_lipin.setText(bean.getIs_present() == 0 ? "否" : "是");
    }

    private void showProduct(OrderBean bean) {
        adapter.loadData(getList(bean.getOrderGoodsList()));
    }

    private List<GoodsDetailBean> getList(List<ShoppingCartBean> orderGoodsList) {
        List<GoodsDetailBean> list = new ArrayList<>();
        for (ShoppingCartBean productBean : orderGoodsList) {
            GoodsDetailBean bean = new GoodsDetailBean();
            bean.setCount(productBean.getGoods_count());
            bean.setGoods_id(productBean.getGoods_id());
            bean.setName(productBean.getGoods_name());
            bean.setPrice(productBean.getGoods_price());
            bean.setIcon(productBean.getGoods_pic());

            list.add(bean);
        }
        return list;
    }

    private void showAddress(OrderBean bean) {
        AddressBean orderAddress = bean.getOrderAddress();
        tv_address.setText(orderAddress.getAddr_province() + orderAddress.getAddr_city() +
                orderAddress.getAddr_district() + orderAddress.getDetail_address());
        tv_name.setText("收货人：" + orderAddress.getAddr_cnee() + " " + orderAddress.getAddr_phone());
    }

    private void showTrackerDetail(OrderBean orderBean) {
        String waybillInfo = orderBean.getWaybillInfo();
        TrackerBean trackerBean = new Gson().fromJson(waybillInfo, TrackerBean.class);
        if (trackerBean != null) {
            TrackerBean.DataBean.OriginInfoBean origin_info = trackerBean.getData().getOrigin_info();
            List<TrackinfoBean> trackinfo = origin_info.getTrackinfo();
            TrackinfoBean trackinfoBean = trackinfo.get(0);

            tv_tracker_status.setText(trackinfoBean.getStatusDescription());
            tv_time.setText(trackinfoBean.getDate());
        }
    }

    @Override
    public void dealDeleteOrderResult() {
        new AlertDialog(mContext).builder().setMsg("您的订单已取消，请联系客服，您的付款将通过微信或支付宝给您退回").setTitle("温馨提示").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                }).show();
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

    }

    @Override
    public void showPaySign(String s) {

    }

    @Override
    public void showTracker(TrackerBean.DataBean bean) {

    }

    @OnClick({R.id.tv_cancel, R.id.tv_buy_again, R.id.tv_call, R.id.tv_contact, R.id.tv_all, R.id.tv_copy})
    public void clickView(android.view.View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                cancelOrder();
                break;
            case R.id.tv_buy_again:
                addCart();
                break;
            case R.id.tv_call:
                break;
            case R.id.tv_contact:
                contact();
                break;
            case R.id.tv_all:
                Intent intent = new Intent(mContext, TrackerActivity.class);
                intent.putExtra("data", order_id);
                startActivity(intent);
                break;
            case R.id.tv_copy:
                copy();
                break;
            default:
                break;
        }
    }

    private void contact() {
        String url3521 = "mqqwpa://im/chat?chat_type=wpa&uin=503425407";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url3521)));
    }

    private void addCart() {

    }

    private void cancelOrder() {
        new AlertDialog(mContext).builder().setMsg("确认取消订单").setTitle("温馨提示").
                setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> params = new HashMap<>();
                        params.put("order_id ", order_id);
                        params.put("order_status ", 5);
                        presenter.updateOrder(params);
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

    }

    private void copy() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData mClipData = ClipData.newPlainText("Label", orderBean.getOrder_id());
        cm.setPrimaryClip(mClipData);

        showError("已复制");
    }
}

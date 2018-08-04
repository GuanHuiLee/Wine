package com.lgh.wine.ui.personal;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.beans.TrackinfoBean;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.ui.personal.adapter.TrackerAdapter;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 物流查询
 */
public class TrackerActivity extends BaseActivity implements OrderContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_code)
    TextView tv_code;
    @BindView(R.id.tv_order)
    TextView tv_order;

    private OrderPresenter presenter;
    private TrackerAdapter adapter;
    private String order_id;
    private OrderBean orderBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_logistics;
    }


    @Override
    protected void initUI() {
        adapter = new TrackerAdapter(mContext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new OrderPresenter(this, OrderModel.newInstance());
        addPresenter(presenter);

        order_id = getIntent().getStringExtra("data");
        getOrderDetail();
    }

    private void getOrderDetail() {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", order_id);

        presenter.getOrderDetail(params);
    }

    private void setMsg() {
        tv_name.setText("承运快递：" + orderBean.getCarrier_code());
        tv_order.setText("订单号：" + orderBean.getOrder_id());
        tv_code.setText("运货单号：" + orderBean.getWaybill());
    }


    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("物流查询");

    }

    @Override
    public void dealAddOrderResult(String id) {

    }

    @Override
    public void showOrderDetail(OrderBean bean) {
        orderBean = bean;

        setMsg();

        String waybillInfo = orderBean.getWaybillInfo();
        TrackerBean trackerBean = new Gson().fromJson(waybillInfo, TrackerBean.class);
        if (trackerBean != null) {
            TrackerBean.DataBean.OriginInfoBean origin_info = trackerBean.getData().getOrigin_info();
            List<TrackinfoBean> trackinfo = origin_info.getTrackinfo();

            adapter.loadData(trackinfo);
        }
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

    }

    @Override
    public void showPaySign(String s) {

    }

    @Override
    public void showTracker(TrackerBean.DataBean trackerBean) {

    }
}

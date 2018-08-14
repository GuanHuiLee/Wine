package com.lgh.wine.ui.product;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.ui.personal.AddOrderCommentActivity;
import com.lgh.wine.ui.personal.TrackerActivity;
import com.lgh.wine.ui.product.adapter.OrderAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xgr.easypay.EasyPay;
import com.xgr.easypay.alipay.AliPay;
import com.xgr.easypay.alipay.AlipayInfoImpli;
import com.xgr.easypay.callback.IPayCallback;
import com.xgr.easypay.wxpay.WXPay;
import com.xgr.easypay.wxpay.WXPayInfoImpli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by ligh on 2018/7/12.
 * 模块：
 */
public class OrderFragment extends BaseFragment implements OrderContract.View, OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private OrderPresenter presenter;
    private OrderAdapter adapter;

    public static final int TYPE_ALL = 6;
    public static final int TYPE_NEED_PAY = 0;
    public static final int TYPE_SEND_GOODS = 1;
    public static final int TYPE_RECEIVE_GOODS = 2;
    public static final int TYPE_DONE = 3;
    public static final int TYPE_COMMENT = 4;
    public static final int TYPE_CLOSE = 5;
    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new OrderAdapter(mContext);
        recyclerView.setAdapter(adapter);
        adapter.setOnChildClickListener(new OrderAdapter.OnChildClickListener() {
            @Override
            public void onPayClick(int position) {
                OrderBean item = adapter.getItem(position);
                dealPayClick(item);
            }

            @Override
            public void onDeleteClick(int position) {
                OrderBean item = adapter.getItem(position);
                dealDeleteClick(item);
            }

            @Override
            public void onItemClick(int position) {
                toOrderDetail(position);
            }
        });
        adapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                toOrderDetail(position);
            }
        });
    }

    private void toOrderDetail(int position) {
        Intent intent = new Intent(mContext, OrderDetailActivity.class);
        intent.putExtra("data", adapter.getItem(position).getOrder_id());
        startActivity(intent);
    }

    private void dealDeleteClick(OrderBean item) {
        int order_status = item.getOrder_status();

        switch (order_status) {//0待支付，1待发货，2待收货，3已完成，4待评价，5支付已关闭
            case 0:
                break;
            case 1:
                break;
            case 2:
                dealTracker(item);
                break;
            case 3:

                break;
            case 4:
                deleteOrder(item);
                break;
            case 5:
                break;
            default:
                break;
        }
    }

    private void dealTracker(OrderBean item) {
        Intent intent = new Intent(mContext, TrackerActivity.class);
        intent.putExtra("data", item.getOrder_id());
        startActivity(intent);
    }

    private void deleteOrder(OrderBean item) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", item.getOrder_id());
        presenter.deleteOrder(params);
    }

    private void dealPayClick(OrderBean item) {
        int order_status = item.getOrder_status();

        switch (order_status) {//0待支付，1待发货，2待收货，3已完成，4待评价，5支付已关闭
            case 0:
                pay(item.getOrder_id());
                break;
            case 1:
                reBuy(item);
                break;
            case 2:
                receiveGoods(item);
                break;
            case 3:
                reBuy(item);
                break;
            case 4:
                comment(item);
                break;
            case 5:
                break;
            default:
                break;
        }

    }

    private void comment(OrderBean item) {
        Intent intent = new Intent(mContext, AddOrderCommentActivity.class);
        intent.putExtra("data", item);
        startActivity(intent);
    }

    private void receiveGoods(OrderBean item) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id ", item.getOrder_id());
        params.put("order_status ", 4);
        presenter.updateOrder(params);
    }

    private void reBuy(OrderBean item) {

    }

    private void pay(String order_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("order_id", order_id);
        params.put("pay_type", 1);//pay_type:1为支付宝2位微信支付

        presenter.getPaySign(params);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getInt("type", TYPE_ALL);
    }

    public static OrderFragment newInstance(int type) {
        OrderFragment fragment = new OrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new OrderPresenter(this, OrderModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getData();
    }

    private void getData() {
        Logger.d("type: " + type);

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        presenter.getOrderList(params);
    }

    @Override
    public void hideProgress() {
        if (refreshLayout != null) {//空指针错误
            refreshLayout.finishRefresh();
        }

        int itemCount = adapter.getItemCount();
        if (llNoData != null) {
            llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
        }

        super.hideProgress();
    }

    @Override
    public void dealAddOrderResult(String id) {

    }

    @Override
    public void showOrderDetail(OrderBean bean) {

    }

    @Override
    public void dealDeleteOrderResult() {
        showError("订单删除成功");
        refreshLayout.autoRefresh();
    }

    @Override
    public void dealUpdateOrderResult() {
        showError("成功");
        refreshLayout.autoRefresh();
    }

    @Override
    public void showOrderList(List<OrderBean> beans) {
        if (beans != null) {
            if (type == TYPE_ALL) {
                adapter.loadData(beans);
            } else {
                List<OrderBean> list = new ArrayList<>();
                for (OrderBean bean : beans) {
                    if (bean.getOrder_status() == type) {
                        list.add(bean);
                    }
                }
                adapter.loadData(list);
            }
        }
    }

    @Override
    public void showOrderStatusNum(OrderStatusBean bean) {

    }

    @Override
    public void showCodeError(String s) {

    }

    @Override
    public void showPaySign(String s) {
        alipay(s);
    }

    @Override
    public void showTracker(TrackerBean.DataBean bean) {

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
        EasyPay.pay(aliPay, getActivity(), alipayInfoImpli, new IPayCallback() {
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
        WXPay wxPay = WXPay.getInstance(getActivity(), wxAppId);
        EasyPay.pay(wxPay, getActivity(), wxPayInfoImpli, new IPayCallback() {
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


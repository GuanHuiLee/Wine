package com.lgh.wine.ui.product;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.contract.CouponContract;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.presenter.CouponPresenter;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.ui.coupon.adapter.CouponUserAdapter;
import com.lgh.wine.ui.product.adapter.OrderAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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
        super.hideProgress();
        refreshLayout.finishRefresh();
        int itemCount = adapter.getItemCount();
        llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void dealAddOrderResult(String id) {

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

    }
}

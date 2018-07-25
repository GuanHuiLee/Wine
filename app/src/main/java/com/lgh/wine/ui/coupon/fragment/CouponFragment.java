package com.lgh.wine.ui.coupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.contract.CouponContract;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.presenter.CouponPresenter;
import com.lgh.wine.ui.coupon.adapter.CouponAdapter;
import com.lgh.wine.ui.coupon.adapter.CouponUserAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.lgh.wine.utils.Constant.START_NUM;

/**
 * Created by ligh on 2018/7/12.
 * 模块：
 */
public class CouponFragment extends BaseFragment implements CouponContract.View, OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private CouponPresenter presenter;
    private CouponUserAdapter adapter;

    public static final String TAG = CouponFragment.class.getName();
    public static final int TYPE_NOT_USE = 1;
    public static final int TYPE_USED = 2;
    public static final int TYPE_OVERDUE = 3;
    private int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CouponUserAdapter(R.layout.item_user_coupon, new ArrayList<CouponBean>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.type = getArguments().getInt("type", TYPE_NOT_USE);
    }

    public static CouponFragment newInstance(int type) {
        CouponFragment fragment = new CouponFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initData() {
        presenter = new CouponPresenter(this, CouponModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }


    @Override
    public void showCouponList(List<CouponBean> beans) {

    }

    @Override
    public void dealAddUserCouponResult() {

    }

    @Override
    public void showUserCouponList(List<CouponBean> beans) {
        if (beans != null) {
            adapter.setNewData(beans);
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getData();
    }

    private void getData() {
        Logger.d("type: " + type);
        presenter.getUserCouponList(AccountUtil.getUserId(), type);
    }

    @Override
    public void hideProgress() {
        refreshLayout.finishRefresh();
        super.hideProgress();
        int itemCount = adapter.getItemCount();
        llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }
}

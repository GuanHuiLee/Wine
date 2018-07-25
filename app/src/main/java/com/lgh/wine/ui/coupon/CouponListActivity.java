package com.lgh.wine.ui.coupon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.CouponBean;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.contract.CouponContract;
import com.lgh.wine.model.CouponModel;
import com.lgh.wine.presenter.CouponPresenter;
import com.lgh.wine.ui.coupon.adapter.CouponAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CouponListActivity extends BaseActivity implements OnRefreshListener, CouponContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private CouponPresenter presenter;
    private CouponAdapter adapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_coupon_list;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new CouponAdapter(R.layout.item_coupon, new ArrayList<CouponBean>());
        recyclerView.setAdapter(adapter);
        adapter.setItemclickListener(new CouponAdapter.OnItemButtonclickListener() {
            @Override
            public void OnButtonClick(int position) {
                CouponBean item = adapter.getItem(position);
                presenter.addUserCoupon(AccountUtil.getUserId(), item.getCoupon_id());
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new CouponPresenter(this, CouponModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("优惠券");
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        presenter.getCouponList(AccountUtil.getUserId());
    }


    @Override
    public void showCouponList(List<CouponBean> beans) {
        if (beans != null) {
            adapter.setNewData(beans);
        }
    }

    @Override
    public void dealAddUserCouponResult() {
        showError("领取成功");
//        refreshLayout.autoRefresh();
    }

    @Override
    public void showUserCouponList(List<CouponBean> beans) {

    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
    }
}

package com.lgh.wine.ui.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import butterknife.BindView;

public class ProductListActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoDataBanZu;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_list;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("系列品牌");
        tv_title.setTextColor(getResources().getColor(R.color.black));

        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        toolbar.setTitle("");
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {

    }
}

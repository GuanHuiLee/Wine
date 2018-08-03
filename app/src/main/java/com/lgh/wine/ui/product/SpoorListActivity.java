package com.lgh.wine.ui.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.ui.product.adapter.ProductAdapter;
import com.lgh.wine.ui.product.adapter.SpoorAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.lgh.wine.utils.Constant.START_NUM;
import static com.lgh.wine.utils.Constant.USER_ID;

public class SpoorListActivity extends BaseActivity implements ProductContract.View, OnRefreshLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;
    private ProductPresenter productPresenter;
    private int loadType;
    private SpoorAdapter spoorAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spoor_list;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        spoorAdapter = new SpoorAdapter(mContext);
        recyclerView.setAdapter(spoorAdapter);
        spoorAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                SpoorBean info = spoorAdapter.getItem(position);
            }
        });
    }

    @Override
    protected void initData() {
        params = new HashMap<>();
        params.put(USER_ID, AccountUtil.getUserId());
        params.put(START_NUM, pageNum * size);

        productPresenter = new ProductPresenter(this, ProductModel.newInstance());
        addPresenter(productPresenter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("浏览足迹");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("清空");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> params = new HashMap<>();
                params.put(USER_ID, AccountUtil.getUserId());
                productPresenter.deleteSpoorList(params);
            }
        });
    }

    @Override
    public void showProductList(List<ProductBean> beans) {

    }

    @Override
    public void showProductDetail(ProductDetailBean bean) {

    }

    @Override
    public void showSpoorList(List<SpoorBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            spoorAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            spoorAdapter.insertData(spoorAdapter.getItemCount(), beans);
            ++pageNum;
        }

    }

    @Override
    public void dealDeleteSpoorListResult() {
        showError("已清空");
        refreshLayout.autoRefresh();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.REFRESH;
        pageNum = 0;
        getData();
    }

    private void getData() {
        params.put(START_NUM, pageNum * size);
        productPresenter.getSpoorList(params);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
        int itemCount = spoorAdapter.getItemCount();
        llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }
}


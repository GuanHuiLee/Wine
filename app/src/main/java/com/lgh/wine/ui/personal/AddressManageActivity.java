package com.lgh.wine.ui.personal;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.contract.AddressContract;
import com.lgh.wine.model.AddressModel;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.presenter.AddressPresenter;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.ui.personal.adapter.AddressAdapter;
import com.lgh.wine.ui.product.adapter.ProductAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class AddressManageActivity extends BaseActivity implements AddressContract.View, OnRefreshListener {
    private static final int ADD_UPDATE_ADDRESS = 0x001;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private AddressPresenter presenter;
    private AddressAdapter mAddressAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_manage;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAddressAdapter = new AddressAdapter(mContext);
        recyclerView.setAdapter(mAddressAdapter);
        mAddressAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                AddressBean info = mAddressAdapter.getItem(position);
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                intent.putExtra("type", AddAddressActivity.TYPE_UPDATE);
                intent.putExtra("data", info);
                startActivityForResult(intent, ADD_UPDATE_ADDRESS);
            }
        });

    }

    @Override
    protected void initData() {
        presenter = new AddressPresenter(this, AddressModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("地址管理");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("新增");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddAddressActivity.class);
                intent.putExtra("type", AddAddressActivity.TYPE_ADD);
                startActivityForResult(intent, ADD_UPDATE_ADDRESS);
            }
        });
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
        mAddressAdapter.loadData(list);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());

        presenter.getAddressList(params);
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        refreshLayout.finishRefresh();
        llNoData.setVisibility(mAddressAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ADD_UPDATE_ADDRESS) {
            refreshLayout.autoRefresh();
        }
    }
}

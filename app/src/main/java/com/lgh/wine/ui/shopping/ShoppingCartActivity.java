package com.lgh.wine.ui.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.presenter.ShoppingCartPresenter;
import com.lgh.wine.ui.product.AddOrderActivity;
import com.lgh.wine.ui.product.ProductDetailActivity;
import com.lgh.wine.ui.shopping.adapter.ShoppingCartAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.lgh.wine.utils.Constant.START_NUM;

public class ShoppingCartActivity extends BaseActivity implements ShoppingCartContract.View, OnRefreshLoadMoreListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout ll_no_data;
    @BindView(R.id.ll_clean)
    LinearLayout ll_clean;
    @BindView(R.id.cb_all)
    CheckBox cb_all;
    @BindView(R.id.tv_buy)
    TextView tv_buy;
    @BindView(R.id.tv_totle_price)
    TextView tv_totle_price;

    private ShoppingCartPresenter presenter;
    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;
    private int loadType;
    private ShoppingCartAdapter mShoppingCartAdapter;
    private StringBuilder cart_ids;
    private int total_count;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_cart;
    }


    @Override
    protected void initUI() {
        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mShoppingCartAdapter = new ShoppingCartAdapter(mContext);
        recyclerView.setAdapter(mShoppingCartAdapter);
        mShoppingCartAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                ShoppingCartBean item = mShoppingCartAdapter.getItem(position);

                gotoDetail(item.getGoods_id());
            }
        });
        mShoppingCartAdapter.setOnCheckChangedListener(new ShoppingCartAdapter.OnCheckListener() {
            @Override
            public void onCheckChanged() {
                int total = mShoppingCartAdapter.getItemCount();
                int select = getSelectCount();
                if (select == 0) {
                    cb_all.setText("全选");
                    cb_all.setChecked(false);

                    tv_totle_price.setText("0.00");
                    tv_buy.setText("结算（0）");
                } else {
                    if (select == total) {
                        cb_all.setText("取消全选");
                        cb_all.setChecked(true);
                    } else {
                        cb_all.setText("全选");
                        cb_all.setChecked(false);
                    }

                    tv_buy.setText("结算（" + getSelectCount() + "）");

                    count();
                }

            }

            @Override
            public void onCountChange(int poi) {
                count();
                mShoppingCartAdapter.setItemChecked(poi);
            }
        });
    }

    private void gotoDetail(String info) {
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("data", info);
        startActivity(intent);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        final TextView tv_other = toolbar.findViewById(R.id.toolbar_other);

        tv_title.setText("购物车（3）");
        tv_other.setText("管理");

        tv_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String manage = tv_other.getText().toString();
                if (manage.equals("管理")) {
                    tv_other.setText("完成");
                    ll_clean.setVisibility(View.VISIBLE);
                } else {
                    tv_other.setText("管理");
                    ll_clean.setVisibility(View.GONE);
                }
            }
        });
    }

    private void count() {
        cart_ids = new StringBuilder();
        double total_price = 0.00;
        int count = 0;
        HashMap<Integer, Boolean> map = mShoppingCartAdapter.getMap();
        HashMap<Integer, Integer> countMap = mShoppingCartAdapter.getCountMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                Integer key = entry.getKey();
                Integer i = countMap.get(key);
                ShoppingCartBean item = mShoppingCartAdapter.getItem(key);
                total_price += i * item.getGoods_price() * item.getGoods_count();

                cart_ids.append(item.getCart_id());
                count++;
                if (count < total_count) {
                    cart_ids.append(",");
                }
            }
        }

        tv_totle_price.setText(String.valueOf(total_price));
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = mShoppingCartAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
        total_count = select;
        return select;
    }


    @Override
    protected void initData() {
        presenter = new ShoppingCartPresenter(this, ShoppingCartModel.newInstance());
        addPresenter(presenter);

        params = new HashMap<>();
        params.put(Constant.START_NUM, pageNum * size);
        params.put(Constant.USER_ID, AccountUtil.getUserId());

        refreshLayout.autoRefresh();
    }

    @Override
    public void dealAddShoppingCartResult() {

    }

    @Override
    public void showShoppingCart(List<ShoppingCartBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            mShoppingCartAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            mShoppingCartAdapter.insertData(mShoppingCartAdapter.getItemCount(), beans);
            ++pageNum;
        }
    }

    @Override
    public void dealDeleteShoppingCartResult() {
        showError("删除成功");
        cart_ids.setLength(0);
        refreshLayout.autoRefresh();

        cb_all.setText("全选");
        cb_all.setChecked(false);
        tv_buy.setText("结算（0）");
        tv_totle_price.setText("0.00");
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    private void getData() {
        params.put(START_NUM, pageNum * size);
        presenter.getShoppingCartList(params);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.REFRESH;
        pageNum = 0;
        getData();
    }

    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
        ll_no_data.setVisibility(mShoppingCartAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick({R.id.cb_all, R.id.tv_buy, R.id.tv_delete})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.cb_all:
                mShoppingCartAdapter.selectAll();
                break;
            case R.id.tv_buy:
                if (total_count > 0)
                    addOrder();
                break;
            case R.id.tv_delete:
                delete();
                break;
            default:
                break;
        }
    }

    private List<GoodsDetailBean> getSelectGoods() {
        List<GoodsDetailBean> list = new ArrayList<>();
        HashMap<Integer, Boolean> map = mShoppingCartAdapter.getMap();
        HashMap<Integer, Integer> countMap = mShoppingCartAdapter.getCountMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                ShoppingCartBean productBean = mShoppingCartAdapter.getItem(entry.getKey());
                GoodsDetailBean bean = new GoodsDetailBean();
                bean.setCount(countMap.get(entry.getKey()));
                bean.setGoods_id(productBean.getGoods_id());
                bean.setName(productBean.getGoods_name());
                bean.setPrice(productBean.getGoods_price());
                bean.setIcon(productBean.getGoods_pic());

                list.add(bean);
            }
        }
        return list;
    }

    private void addOrder() {
        Intent intent = new Intent(mContext, AddOrderActivity.class);

        List<GoodsDetailBean> selectGoods = getSelectGoods();
        intent.putExtra("data", (Serializable) selectGoods);
        startActivity(intent);
    }

    private void delete() {
        if (TextUtils.isEmpty(cart_ids)) return;

        Map<String, Object> params = new HashMap<>();
        params.put("cart_ids", cart_ids.toString());

        presenter.deleteShoppingCartInfo(params);
    }
}

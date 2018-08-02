package com.lgh.wine.ui.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.presenter.ShoppingCartPresenter;
import com.lgh.wine.ui.product.AddOrderActivity;
import com.lgh.wine.ui.shopping.adapter.ShoppingCartAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
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

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class ShoppingFragment extends BaseFragment implements ShoppingCartContract.View, OnRefreshLoadMoreListener {
    public static final String TAG = ShoppingFragment.class.getName();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout ll_no_data;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ll_clean)
    LinearLayout ll_clean;
    @BindView(R.id.tv_manage)
    TextView tv_manage;
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
    private String cart_ids = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mShoppingCartAdapter = new ShoppingCartAdapter(mContext);
        recyclerView.setAdapter(mShoppingCartAdapter);
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
            public void onCountChange() {
                count();
            }
        });
    }

    private void count() {
        double totle_price = 0.00;
        HashMap<Integer, Boolean> map = mShoppingCartAdapter.getMap();
        HashMap<Integer, Integer> countMap = mShoppingCartAdapter.getCountMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue()) {
                Integer key = entry.getKey();
                Integer i = countMap.get(key);
                ShoppingCartBean item = mShoppingCartAdapter.getItem(key);
                totle_price += i * item.getGoods_price();

                cart_ids += item.getCart_id();
            }
        }

        tv_totle_price.setText(String.valueOf(totle_price));
    }

    private int getSelectCount() {
        int select = 0;
        HashMap<Integer, Boolean> map = mShoppingCartAdapter.getMap();
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        for (Map.Entry<Integer, Boolean> entry : entries) {
            if (entry.getValue())
                select++;
        }
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
        cart_ids = "";
        refreshLayout.autoRefresh();
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

    @OnClick({R.id.tv_manage, R.id.cb_all, R.id.tv_buy, R.id.tv_delete})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.tv_manage:
                String manage = tv_manage.getText().toString();
                if (manage.equals("管理")) {
                    tv_manage.setText("完成");
                    ll_clean.setVisibility(View.VISIBLE);
                } else {
                    tv_manage.setText("管理");
                    ll_clean.setVisibility(View.GONE);
                }
                break;
            case R.id.cb_all:
                mShoppingCartAdapter.selectAll();
                break;
            case R.id.tv_buy:
                if (getSelectCount() > 0)
                    addOrder();
                break;
            case R.id.tv_delete:
                delete();
                break;
            default:
                break;
        }
    }

    private void addOrder() {
        Intent intent = new Intent(mContext, AddOrderActivity.class);

        List<GoodsDetailBean> selectGoods = getSelectGoods();
        intent.putExtra("data", (Serializable) selectGoods);
        startActivity(intent);
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

    private void delete() {
        if (TextUtils.isEmpty(cart_ids)) return;

        Map<String, Object> params = new HashMap<>();
        params.put("cart_ids", cart_ids);

        presenter.deleteShoppingCartInfo(params);
    }
}

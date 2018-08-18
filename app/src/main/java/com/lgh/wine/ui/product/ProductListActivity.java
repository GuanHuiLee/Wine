package com.lgh.wine.ui.product;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.ui.product.adapter.ProductAdapter;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

import static com.lgh.wine.utils.Constant.START_NUM;

public class ProductListActivity extends BaseActivity implements OnRefreshLoadMoreListener, ProductContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.rb_new)
    RadioButton rb_new;
    @BindView(R.id.rb_price)
    RadioButton rb_price;
    @BindView(R.id.rb_sale)
    RadioButton rb_sale;
    @BindView(R.id.rb_discuss)
    RadioButton rb_discuss;


    private static final String TAG_NEW = "createtime_orderby";//最新
    private static final String TAG_PRICE = "price_orderby";//价格
    private static final String TAG_SALE = "sale_orderby";//销量
    private static final String TAG_DISCUSS = "comment_orderby";//评论


    private static final String TYPE = "product_type";

    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;

    private ProductPresenter productPresenter;
    private ProductAdapter mProductAdapter;
    private int loadType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_list;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(this);

        rb_new.setTag(TAG_NEW);
        rb_discuss.setTag(TAG_DISCUSS);
        rb_price.setTag(TAG_PRICE);
        rb_sale.setTag(TAG_SALE);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mProductAdapter = new ProductAdapter(mContext);
        recyclerView.setAdapter(mProductAdapter);
        mProductAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                ProductBean info = mProductAdapter.getItem(position);
                gotoDetail(info.getProduct_id());
            }
        });

    }

    private void gotoDetail(String info) {
        Intent intent = new Intent(mContext, ProductDetailActivity.class);
        intent.putExtra("data", info);
        startActivity(intent);
    }

    @Override
    protected void initData() {
        int type = getIntent().getIntExtra("type", 1);
        params = new HashMap<>();
        params.put(TYPE, type);
        params.put(START_NUM, pageNum * size);

        productPresenter = new ProductPresenter(this, ProductModel.newInstance());
        addPresenter(productPresenter);
        refreshLayout.autoRefresh();
    }

    @OnCheckedChanged({R.id.rb_new, R.id.rb_sale, R.id.rb_discuss, R.id.rb_price})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String tag = (String) buttonView.getTag();
        if (tag == null) return;

        if (isChecked) {
            params.put(tag, 2);
        } else {
            params.remove(tag);
        }
        refreshLayout.autoRefresh();
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
        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    private void getData() {
        params.put(START_NUM, pageNum * size);
        productPresenter.getProductList(params);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.REFRESH;
        pageNum = 0;
        getData();
    }

    @Override
    public void showProductList(List<ProductBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            mProductAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            mProductAdapter.insertData(mProductAdapter.getItemCount(), beans);
            ++pageNum;
        }
//        refreshLayout.setEnableLoadMore(beans.getTotal() > mProductAdapter.getItemCount());
    }

    @Override
    public void showProductDetail(ProductDetailBean bean) {

    }

    @Override
    public void showSpoorList(List<SpoorBean> beans) {

    }

    @Override
    public void dealDeleteSpoorListResult() {

    }


    @Override
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
        llNoData.setVisibility(mProductAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }

}

package com.lgh.wine.ui.discover;

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
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.contract.ArticleContract;
import com.lgh.wine.model.ArticleModel;
import com.lgh.wine.presenter.ArticlePresenter;
import com.lgh.wine.presenter.ShoppingCartPresenter;
import com.lgh.wine.ui.discover.adapter.ArticleListAdapter;
import com.lgh.wine.ui.home.HomeFragment;
import com.lgh.wine.ui.shopping.adapter.ShoppingCartAdapter;
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

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class DiscoverFragment extends BaseFragment implements ArticleContract.View, OnRefreshLoadMoreListener {
    public static final String TAG = DiscoverFragment.class.getName();

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout ll_no_data;


    private ArticlePresenter presenter;
    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;
    private int loadType;
    private ArticleListAdapter articleListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        articleListAdapter = new ArticleListAdapter(mContext);
        recyclerView.setAdapter(articleListAdapter);

        articleListAdapter.setOnItemViewClickListener(new BaseRecyclerAdapter.OnItemViewClickListener() {
            @Override
            public void onViewClick(View view, int position) {
                ArticleBean item = articleListAdapter.getItem(position);
                Intent intent=new Intent(mContext,ArticleDetailActivity.class);
                intent.putExtra("data",item);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        presenter = new ArticlePresenter(this, ArticleModel.newInstance());
        addPresenter(presenter);
        refreshLayout.autoRefresh();
    }

    @Override
    public void showArticleList(List<ArticleBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            articleListAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            articleListAdapter.insertData(articleListAdapter.getItemCount(), beans);
            ++pageNum;
        }
    }

    @Override
    public void showArticleDetail(ArticleBean bean) {

    }

    @Override
    public void dealAddUserResult() {

    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {

        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    private void getData() {
        params = new HashMap<>();
        params.put(Constant.START_NUM, pageNum * size);
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        presenter.getArticleList(params);
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
        ll_no_data.setVisibility(articleListAdapter.getItemCount() > 0 ? View.GONE : View.VISIBLE);
    }
}

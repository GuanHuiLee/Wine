package com.lgh.wine.ui.personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.beans.UserArticleBean;
import com.lgh.wine.contract.ArticleContract;
import com.lgh.wine.model.ArticleModel;
import com.lgh.wine.model.CommentModel;
import com.lgh.wine.presenter.ArticlePresenter;
import com.lgh.wine.presenter.CommentPresenter;
import com.lgh.wine.ui.personal.adapter.CommentAdapter;
import com.lgh.wine.ui.personal.adapter.LikesAdapter;
import com.lgh.wine.utils.AccountUtil;
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

/**
 * 我的点赞
 */
public class MyLikesActivity extends BaseActivity implements OnRefreshLoadMoreListener, ArticleContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_no_data)
    FrameLayout llNoData;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private Map<String, Object> params;
    private int pageNum = 0;
    private final static int size = 10;
    private LikesAdapter mLikesAdapter;
    private ArticlePresenter articlePresenter;
    private int loadType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_likes;
    }


    @Override
    protected void initUI() {
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setOnRefreshLoadMoreListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLikesAdapter = new LikesAdapter(mContext);
        recyclerView.setAdapter(mLikesAdapter);

    }

    @Override
    protected void initData() {
        articlePresenter = new ArticlePresenter(this, ArticleModel.newInstance());
        addPresenter(articlePresenter);

        params = new HashMap<>();
        params.put(USER_ID, AccountUtil.getUserId());
        params.put(START_NUM, pageNum * size);

        refreshLayout.autoRefresh();
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("我的点赞");
    }


    @Override
    public void showArticleList(List<ArticleBean> list) {

    }

    @Override
    public void showUserArticleList(List<UserArticleBean> beans) {
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            mLikesAdapter.loadData(beans);
            pageNum = 1;
            if (beans != null && beans.size() > 0) {
                refreshLayout.setEnableLoadMore(true);
            }
        } else {
            mLikesAdapter.insertData(mLikesAdapter.getItemCount(), beans);
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
    public void hideProgress() {
        super.hideProgress();
        if (loadType == Constant.LOAD_TYPE.REFRESH) {
            refreshLayout.finishRefresh();
        } else {
            refreshLayout.finishLoadMore();
        }
        int itemCount = mLikesAdapter.getItemCount();
        llNoData.setVisibility(itemCount > 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.LOAD_MORE;
        pageNum += 1;
        getData();
    }

    private void getData() {
        params.put(START_NUM, pageNum * size);
        articlePresenter.getArticleList(params);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        loadType = Constant.LOAD_TYPE.REFRESH;
        pageNum = 0;
        getData();
    }
}

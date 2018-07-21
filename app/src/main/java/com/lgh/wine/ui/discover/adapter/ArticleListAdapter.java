package com.lgh.wine.ui.discover.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.TimeUtils;
import com.lgh.wine.utils.VolumeView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ArticleListAdapter extends BaseRecyclerAdapter<ArticleBean, ArticleListAdapter.ViewHolder> {
    private Context context;

    public ArticleListAdapter(Context mContext) {
        super(mContext);
        this.context = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ArticleBean item = getItem(position);

        GlideHelper.loadRadiusImage(context, holder.iv_pic, item.getArticle_icon(), 10, R.mipmap.iv_error);
        holder.tv_title.setText(item.getArticle_title());
        holder.tv_content.setText(item.getArticle_abstract());
        holder.tv_count.setText(item.getArticle_reading());
        long create_time = item.getCreate_time();
        holder.tv_time.setText(TimeUtils.changeMDHM(create_time));
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tv_title;

        @BindView(R.id.tv_content)
        TextView tv_content;

        @BindView(R.id.iv_pic)
        ImageView iv_pic;

        @BindView(R.id.tv_count)
        TextView tv_count;

        @BindView(R.id.tv_time)
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

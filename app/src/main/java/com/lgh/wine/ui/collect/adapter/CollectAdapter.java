package com.lgh.wine.ui.collect.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.CollectBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CollectAdapter extends BaseRecyclerAdapter<CollectBean, CollectAdapter.ViewHolder> {
    public OnOtherListener listener;

    public CollectAdapter(Context mContext) {
        super(mContext);
    }

    public void setListener(OnOtherListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collect, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        holder.tvName.setText(getItem(position).getGoods_name());
        holder.tvPrice.setText("￥" + getItem(position).getGoods_price());
        GlideHelper.loadImage(mContext, holder.ivPic, Constant.IMG_IP + getItem(position).getGoods_icon());

        holder.iv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onCartClick(getItem(position));
            }
        });
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.iv_pic)
        ImageView ivPic;

        @BindView(R.id.iv_cart)
        ImageView iv_cart;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnOtherListener {
        abstract void onCartClick(CollectBean bean);
    }
}

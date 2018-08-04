package com.lgh.wine.ui.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.TrackinfoBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class TrackerAdapter extends BaseRecyclerAdapter<TrackinfoBean, TrackerAdapter.ViewHolder> {

    public TrackerAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tracker, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TrackinfoBean item = getItem(position);

        holder.tvTime.setText(item.getDate());
        holder.tvStatus.setText(item.getStatusDescription());

        holder.ivDot.setImageResource(position == 0 ? R.mipmap.ic_dot_select : R.mipmap.ic_dot_default);
        holder.tvStatus.setTextColor(position == 0 ? mContext.getResources().getColor(R.color.pink) :
                mContext.getResources().getColor(R.color.color_grey_999999));
        holder.tvTime.setTextColor(position == 0 ? mContext.getResources().getColor(R.color.pink) :
                mContext.getResources().getColor(R.color.color_grey_999999));
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_time)
        TextView tvTime;

        @BindView(R.id.tv_tracker_status)
        TextView tvStatus;


        @BindView(R.id.iv_dot)
        ImageView ivDot;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

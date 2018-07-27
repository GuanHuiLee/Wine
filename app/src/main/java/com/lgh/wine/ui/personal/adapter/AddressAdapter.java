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
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class AddressAdapter extends BaseRecyclerAdapter<AddressBean, AddressAdapter.ViewHolder> {

    public AddressAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        AddressBean item = getItem(position);
        holder.tvName.setText(item.getAddr_cnee());
        holder.tvDetail.setText(item.getDetail_address());
        holder.tvDefault.setVisibility(item.getIs_default() ? View.VISIBLE : View.GONE);
        holder.tvPhone.setText(item.getAddr_phone());
        holder.tvAddress.setText(item.getAddr_province() + item.getAddr_city() + item.getAddr_district());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

        @BindView(R.id.tv_phone)
        TextView tvPhone;

        @BindView(R.id.tv_default)
        TextView tvDefault;

        @BindView(R.id.iv_edit)
        ImageView ivEdit;

        @BindView(R.id.tv_address)
        TextView tvAddress;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

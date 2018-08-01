package com.lgh.wine.ui.personal.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class AddressSelectAdapter extends BaseRecyclerAdapter<AddressBean, AddressSelectAdapter.ViewHolder> {
    private OnChildClickListener onChildClickListener;

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public AddressSelectAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_select, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        AddressBean item = getItem(position);
        holder.tvName.setText(item.getAddr_cnee());
        holder.ivDefault.setVisibility(item.getIs_default() ? View.VISIBLE : View.GONE);
        holder.tvPhone.setText(item.getAddr_phone());
        holder.cb_address.setChecked(item.getIs_default());
        holder.tvAddress.setText(item.getAddr_province() + item.getAddr_city() +
                item.getAddr_district() + item.getDetail_address());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.cb_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChildClickListener != null)
                    onChildClickListener.onCheckBoxClick(position);
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChildClickListener != null)
                    onChildClickListener.onCheckBoxClick(position);
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

        @BindView(R.id.iv_default)
        ImageView ivDefault;

        @BindView(R.id.iv_edit)
        ImageView ivEdit;

        @BindView(R.id.tv_address)
        TextView tvAddress;

        @BindView(R.id.cb_address)
        CheckBox cb_address;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

  public   interface OnChildClickListener {
        void onEditClick(int position);

        void onCheckBoxClick(int position);
    }
}

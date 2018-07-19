package com.lgh.wine.ui.product.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.lgh.wine.R;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ProductGradeAdapter extends BaseRecyclerAdapter<ProductDetailBean.ProductChildBean, ProductGradeAdapter.ViewHolder> {
    public int selctPosition = 0;
    private OnCheckListener onCheckListener;

    public int getSelctPosition() {
        return selctPosition;
    }

    public void setSelctPosition(int selctPosition) {
        this.selctPosition = selctPosition;
    }

    public ProductGradeAdapter(Context mContext) {
        super(mContext);

    }
    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_grade, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ProductDetailBean.ProductChildBean item = getItem(position);
        holder.checkBox.setText(item.getGrade_name());

        holder.checkBox.setChecked(getSelctPosition() == position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                setSelctPosition(adapterPosition);
                notifyDataSetChanged();

                if (onCheckListener != null)
                    onCheckListener.onCheckChanged(adapterPosition);
            }
        });

    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox)
        CheckBox checkBox;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCheckListener {
        void onCheckChanged(int position);
    }
}

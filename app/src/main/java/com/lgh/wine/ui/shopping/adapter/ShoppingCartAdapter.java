package com.lgh.wine.ui.shopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;
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
public class ShoppingCartAdapter extends BaseRecyclerAdapter<ShoppingCartBean, ShoppingCartAdapter.ViewHolder> {
    private OnCheckListener onCheckListener;
    private HashMap<Integer, Boolean> map;

    private HashMap<Integer, Integer> countMap;

    public ShoppingCartAdapter(Context mContext) {
        super(mContext);

    }

    public HashMap<Integer, Integer> getCountMap() {
        return countMap;
    }

    public HashMap<Integer, Boolean> getMap() {
        return map;
    }

    @Override
    public void loadData(List<? extends ShoppingCartBean> datas) {
        super.loadData(datas);
        map = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            map.put(i, false);
        }

        countMap = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            countMap.put(i, 1);
        }
    }

    public void setOnCheckChangedListener(OnCheckListener listener) {
        onCheckListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopping_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        ShoppingCartBean item = getItem(position);
        holder.tvName.setText(item.getGoods_name());
        holder.tvPrice.setText("￥" + item.getGoods_price());
        GlideHelper.loadImage(mContext, holder.ivIcon, Constant.IMG_IP + item.getGoods_pic());

        holder.volume.setOnVolumeChangeListener(new VolumeView.OnVolumeChangeListener() {
            @Override
            public void onVolumeChange(View view, int Volume) {
                countMap.put(holder.getAdapterPosition(), Volume);
                if (map.get(holder.getAdapterPosition())) {
                    onCheckListener.onCountChange();
                }
            }
        });

        holder.checkbox.setChecked(map.get(position));
        holder.checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                map.put(adapterPosition, !map.get(adapterPosition));
                notifyDataSetChanged();

                if (onCheckListener != null)
                    onCheckListener.onCheckChanged();
            }
        });

        holder.volume.setVolume(item.getGoods_count());
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }

    /**
     * 全选
     */
    public void selectAll() {
        Set<Map.Entry<Integer, Boolean>> entries = map.entrySet();
        boolean shouldall = false;
        for (Map.Entry<Integer, Boolean> entry : entries) {
            Boolean value = entry.getValue();
            if (!value) {
                shouldall = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entry : entries) {
            entry.setValue(shouldall);
        }
        notifyDataSetChanged();

        onCheckListener.onCheckChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.tv_price)
        TextView tvPrice;

        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        @BindView(R.id.volume)
        VolumeView volume;

        @BindView(R.id.checkbox)
        CheckBox checkbox;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnCheckListener {
        void onCheckChanged();

        void onCountChange();
    }
}

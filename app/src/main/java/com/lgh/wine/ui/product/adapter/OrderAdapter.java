package com.lgh.wine.ui.product.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.utils.BaseRecyclerAdapter;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class OrderAdapter extends BaseRecyclerAdapter<OrderBean, OrderAdapter.ViewHolder> {
    public OnChildClickListener onChildClickListener;

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
    }

    public OrderAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        OrderBean item = getItem(position);

        List<ShoppingCartBean> orderGoodsList = item.getOrderGoodsList();


        holder.tv_total_price.setText("￥" + item.getOrder_amount());
        holder.tv_product_count.setText("共" + item.getOrder_goods_count() + "件商品 实付金额：");

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        GoodsAdapter adapter = new GoodsAdapter(mContext);
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setNestedScrollingEnabled(false);
        adapter.loadData(getList(orderGoodsList));

        holder.tv_buy_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChildClickListener != null)
                    onChildClickListener.onPayClick(holder.getAdapterPosition());
            }
        });
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onChildClickListener != null)
                    onChildClickListener.onDeleteClick(holder.getAdapterPosition());
            }
        });

        int order_status = item.getOrder_status();
        switch (order_status) {//0待支付，1待发货，2待收货，3已完成，4待评价，5支付已关闭
            case 0:
                holder.tv_time.setVisibility(View.VISIBLE);
                holder.tv_status.setText("等待付款");
                holder.tv_buy_again.setText("去支付");
                holder.tv_delete.setVisibility(View.GONE);
                holder.tv_time.setText(TimeUtils.getDeadline(item.getCreate_time()));
                break;
            case 1:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("已确认");
                holder.tv_buy_again.setText("再次购买");
                holder.tv_delete.setVisibility(View.GONE);
                break;
            case 2:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("已确认");
                holder.tv_buy_again.setText("确认收货");
                holder.tv_delete.setText("查看物流");
                holder.tv_delete.setVisibility(View.VISIBLE);
                break;
            case 3:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("已完成");
                holder.tv_buy_again.setText("去支付");
                holder.tv_delete.setVisibility(View.VISIBLE);
                break;
            case 4:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("待评价");
                holder.tv_buy_again.setText("评价");
                holder.tv_delete.setText("删除订单");
                holder.tv_delete.setVisibility(View.VISIBLE);
                break;
            case 5:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("已取消");
                holder.tv_buy_again.setText("去支付");
                holder.tv_delete.setVisibility(View.VISIBLE);
                break;
            default:
                holder.tv_time.setVisibility(View.GONE);
                holder.tv_status.setText("删除订单");
                holder.tv_buy_again.setText("再次购买");
                holder.tv_delete.setVisibility(View.VISIBLE);
                break;
        }
    }

    private List<GoodsDetailBean> getList(List<ShoppingCartBean> orderGoodsList) {
        List<GoodsDetailBean> list = new ArrayList<>();
        for (ShoppingCartBean productBean : orderGoodsList) {
            GoodsDetailBean bean = new GoodsDetailBean();
            bean.setCount(productBean.getGoods_count());
            bean.setGoods_id(productBean.getGoods_id());
            bean.setName(productBean.getGoods_name());
            bean.setPrice(productBean.getGoods_price());
            bean.setIcon(productBean.getGoods_pic());

            list.add(bean);
        }
        return list;
    }

    @Override
    protected boolean useItemAnimation() {
        return true;
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_delete)
        TextView tv_delete;

        @BindView(R.id.tv_buy_again)
        TextView tv_buy_again;


        @BindView(R.id.tv_total_price)
        TextView tv_total_price;

        @BindView(R.id.tv_product_count)
        TextView tv_product_count;

        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        @BindView(R.id.tv_time)
        TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnChildClickListener {
        void onPayClick(int position);

        void onDeleteClick(int position);
    }
}

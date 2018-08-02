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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class OrderAdapter extends BaseRecyclerAdapter<OrderBean, OrderAdapter.ViewHolder> {

    public OrderAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.model.OrderModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface OrderContract {
    public interface View extends BaseView {

        void dealAddOrderResult();

        void showOrderDetail(OrderBean bean);

        void dealDeleteOrderResult();

        void dealUpdateOrderResult();

        void showOrderList(List<OrderBean> list);

        void showOrderStatusNum(OrderStatusBean bean);
    }

    public abstract class Presenter extends BasePresenter<View, OrderModel> {

        public Presenter(View view, OrderModel model) {
            super(view, model);
        }


        public abstract void addOrder(Map<String, Object> params);

        public abstract void getOrderDetail(Map<String, Object> params);

        public abstract void getOrderList(Map<String, Object> params);

        public abstract void getOrderStatusNum(Map<String, Object> params);

        public abstract void deleteOrder(Map<String, Object> params);

        public abstract void updateOrder(Map<String, Object> params);
    }
}

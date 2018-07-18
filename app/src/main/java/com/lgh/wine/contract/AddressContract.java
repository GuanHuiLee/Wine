package com.lgh.wine.contract;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.AddressBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.model.AddressModel;
import com.lgh.wine.model.ShoppingCartModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface AddressContract {
    public interface View extends BaseView {

        void dealAddAddressResult();

        void dealDeleteAddressResult();

        void showDefaultAddress(AddressBean bean);

        void showAddressList(List<AddressBean> list);
    }

    public abstract class Presenter extends BasePresenter<View, AddressModel> {

        public Presenter(View view, AddressModel model) {
            super(view, model);
        }


        public abstract void getAddressList(Map<String, Object> params);

        public abstract void getDefaultAddress(Map<String, Object> params);

        public abstract void addAddress(Map<String, Object> params);

        public abstract void deleteAddress(Map<String, Object> params);
    }
}

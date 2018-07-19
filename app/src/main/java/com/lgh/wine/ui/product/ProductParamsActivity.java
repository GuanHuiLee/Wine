package com.lgh.wine.ui.product;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.databinding.ActivityProductParamsBinding;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductParamsActivity extends BaseActivity implements ProductContract.View {
    private ProductPresenter productPresenter;
    private ActivityProductParamsBinding binding;
    private String goodsId;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_params);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        goodsId = getIntent().getStringExtra("goodsId");
        productPresenter = new ProductPresenter(this, ProductModel.newInstance());
        addPresenter(productPresenter);

        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", goodsId);
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        productPresenter.getProductDetail(params);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("产品参数");
    }

    @Override
    public void showProductList(List<ProductBean> beans) {

    }

    @Override
    public void showProductDetail(ProductDetailBean bean) {
        if (bean != null)
            binding.setProductDetailBean(bean);
    }
}

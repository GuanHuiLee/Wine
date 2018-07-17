package com.lgh.wine.ui.product;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.api.Constant;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.utils.GlideHelper;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class ProductDetailActivity extends BaseActivity {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_old_price)
    TextView tv_old_price;
    @BindView(R.id.tv_sale_count)
    TextView tv_sale_count;

    private ProductBean productBean;
    private List<String> bannerBeans;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_detail;
    }


    /**
     * 关于竖线的问题用 string.split("\\|")解决。 关于星号的问题用 string.split("\\*")解决。
     * 关于斜线的问题用 sring.split("\\\\")解决。 关于中括号的问题用 sring.split("\\[\\]")解决。
     */
    @Override
    protected void initUI() {
        productBean = (ProductBean) getIntent().getSerializableExtra("data");
        if (productBean == null) return;

        tv_old_price.setText("原价：" + productBean.getProduct_original());
        tv_price.setText("￥" + productBean.getProduct_price());
        tv_title.setText(productBean.getProduct_name());
        tv_sale_count.setText("销量" + productBean.getProduct_sale() + "件");

        String detail_pictures = productBean.getProduct_pictures();
        String[] strings = detail_pictures.split("\\|");

        bannerBeans = Arrays.asList(strings);
        if (bannerBeans.size() > 0) {
            mContentBanner.setData(bannerBeans, null);
        }
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

                GlideHelper.loadImage(mContext, itemView, Constant.IMG_IP + model);
            }
        });
    }

    @OnClick({R.id.ll_discuss, R.id.ll_img_text, R.id.ll_params, R.id.tv_coupon})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.ll_discuss:
                break;
            case R.id.ll_img_text:
                break;
            case R.id.ll_params:
                break;
            case R.id.tv_coupon:
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
    }
}

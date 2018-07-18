package com.lgh.wine.ui.product;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.api.Constant;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.CollectContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.presenter.CollectPresenter;
import com.lgh.wine.presenter.ShoppingCartPresenter;
import com.lgh.wine.ui.coupon.CouponListActivity;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.GlideHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class ProductDetailActivity extends BaseActivity implements ShoppingCartContract.View, CollectContract.View {
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
    @BindView(R.id.tv_collect)
    TextView tv_collect;

    private ProductBean productBean;
    private List<String> bannerBeans;
    private ShoppingCartPresenter cartPresenter;
    private CollectPresenter collectPresenter;
    private boolean isCollect;


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

        isCollect = productBean.getIf_collect();

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

    @OnClick({R.id.ll_discuss, R.id.ll_img_text, R.id.ll_params, R.id.tv_coupon, R.id.tv_add_car, R.id.tv_collect})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.ll_discuss:
                break;
            case R.id.ll_img_text:
                break;
            case R.id.ll_params:
                break;
            case R.id.tv_coupon:
                startActivity(new Intent(mContext, CouponListActivity.class));
                break;
            case R.id.tv_add_car:
                addShoppoingCart();
                break;
            case R.id.tv_collect:
                addCollect();
                break;
            default:
                break;
        }
    }

    /**
     * 添加收藏
     */
    private void addCollect() {
        isCollect = !isCollect;
        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", productBean.getProduct_id());
        params.put("user_id", AccountUtil.getUserId());
        params.put("goods_count", 1);
        params.put("goods_name", productBean.getProduct_name());
        params.put("goods_price", productBean.getProduct_price());
        params.put("goods_icon", productBean.getProduct_pictures());
        params.put("if_collect", isCollect);//1收藏，0取消收藏

        collectPresenter.addCollect(params);
    }

    /**
     * 添加到购物车
     */
    private void addShoppoingCart() {
        String product_type = productBean.getProduct_type();

        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", productBean.getProduct_id());
        params.put("user_id", AccountUtil.getUserId());
        params.put("goods_count", 1);//数量（如果为定制酒是至少6瓶）
        params.put("goods_name", productBean.getProduct_name());//商品名称（如果为定制酒是则将定制酒名称“-”拼接在后面）
        params.put("goods_price", productBean.getProduct_price());
        params.put("goods_pics", productBean.getProduct_pictures());
        params.put("grade_id", productBean.getProduct_id());//定制酒ID（如果为定制酒时上传所选择的散酒id）

        cartPresenter.addShoppingCart(params);
    }

    @Override
    protected void initData() {
        cartPresenter = new ShoppingCartPresenter(this, ShoppingCartModel.newInstance());
        addPresenter(cartPresenter);
        collectPresenter = new CollectPresenter(this, CollectModel.newInstance());
        addPresenter(collectPresenter);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
    }

    @Override
    public void dealShoppingCartResult() {
        showError("添加成功");
    }

    @Override
    public void showShoppingCart(List<ShoppingCartBean> list) {

    }

    @Override
    public void dealCollectResult() {
        Drawable dra = getResources().getDrawable(isCollect ? R.mipmap.ic_collection_def : R.mipmap.ic_collection_sel);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
        tv_collect.setCompoundDrawables(dra, null, null, null);
    }
}

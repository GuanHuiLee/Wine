package com.lgh.wine.ui.product;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.beans.CollectBean;
import com.lgh.wine.beans.GoodsDetailBean;
import com.lgh.wine.beans.SpoorBean;
import com.lgh.wine.ui.shopping.ShoppingCartActivity;
import com.lgh.wine.utils.CommonMethod;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ProductBean;
import com.lgh.wine.beans.ProductDetailBean;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.contract.CollectContract;
import com.lgh.wine.contract.ProductContract;
import com.lgh.wine.contract.ShoppingCartContract;
import com.lgh.wine.model.CollectModel;
import com.lgh.wine.model.ProductModel;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.presenter.CollectPresenter;
import com.lgh.wine.presenter.ProductPresenter;
import com.lgh.wine.presenter.ShoppingCartPresenter;
import com.lgh.wine.ui.coupon.CouponListActivity;
import com.lgh.wine.ui.product.adapter.ProductGradeAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.VolumeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class ProductDetailActivity extends BaseActivity implements ShoppingCartContract.View, CollectContract.View, ProductContract.View {
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
    private ProductPresenter productPresenter;
    private int isCollect;

    private ProductDetailBean productDetailBean;
    private AlertDialog dialog;
    private int count = 1;
    private String grade_name;
    private String grade_id;
    public static final int TYPE_ADD_CART = 1;
    public static final int TYPE_BUY = 2;
    private int type = 0;


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

        dealAddCollectResult();
    }

    @OnClick({R.id.ll_discuss, R.id.ll_img_text, R.id.ll_params,
            R.id.tv_coupon, R.id.tv_add_car, R.id.tv_collect, R.id.tv_buy,
            R.id.tv_car, R.id.tv_service})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.ll_discuss:
                break;
            case R.id.ll_img_text:
                break;
            case R.id.ll_params:
                Intent intent = new Intent(mContext, ProductParamsActivity.class);
                intent.putExtra("goodsId", productBean.getProduct_id());
                startActivity(intent);
                break;
            case R.id.tv_coupon:
                startActivity(new Intent(mContext, CouponListActivity.class));
                break;
            case R.id.tv_add_car:
                if (productDetailBean != null) {
                    type = TYPE_ADD_CART;
                    showDialog();
                }
                break;
            case R.id.tv_collect:
                addCollect();
                break;
            case R.id.tv_buy:
                if (productDetailBean != null) {
                    type = TYPE_BUY;
                    showDialog();
                }
                break;
            case R.id.tv_car:
                startActivity(new Intent(mContext, ShoppingCartActivity.class));
                break;
            case R.id.tv_service:
                CommonMethod.contact(this);
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams params = dialog.getWindow().getAttributes();  //获取对话框当前的参数值、
        params.width = (int) (d.getWidth());
        //宽度设置全屏宽度
        dialog.getWindow().setAttributes(params);     //设置生效
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Dialog_FS);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_buy, null);
        ImageView iv_icon = view.findViewById(R.id.iv_icon);
        TextView tv_price = view.findViewById(R.id.tv_price);
        TextView tv_bianhao = view.findViewById(R.id.tv_bianhao);
        TextView tv_guige = view.findViewById(R.id.tv_guige);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ImageView iv_close = view.findViewById(R.id.iv_close);
        TextView tv_done = view.findViewById(R.id.tv_done);
        final VolumeView volume = view.findViewById(R.id.volume);
        volume.setOnVolumeChangeListener(new VolumeView.OnVolumeChangeListener() {
            @Override
            public void onVolumeChange(View view, int v) {
                count = v;
            }
        });

        GlideHelper.loadRadiusImage(mContext, iv_icon, Constant.IMG_IP + productDetailBean.getProduct_icon(),
                10, R.mipmap.iv_error);
        tv_bianhao.setText("商品编号：" + productDetailBean.getProduct_name());
        tv_guige.setText("商品规格：" + productDetailBean.getProduct_volume());
        tv_price.setText("￥" + productDetailBean.getProduct_price());

        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                addShoppoingCart();
            }
        });

        if (productBean.getProduct_type() == 3) {
            grade_name = "-" + productDetailBean.getChild_list().get(0).getGrade_name();
            count = 6;
            volume.setVolume(6);
            volume.setMin(6);

            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            final ProductGradeAdapter adapter = new ProductGradeAdapter(this);
            recyclerView.setAdapter(adapter);
            adapter.loadData(productDetailBean.getChild_list());

            adapter.setOnCheckChangedListener(new ProductGradeAdapter.OnCheckListener() {
                @Override
                public void onCheckChanged(int position) {
                    adapter.setSelctPosition(position);
                    ProductDetailBean.ProductChildBean item = adapter.getItem(position);
                    grade_name = "-" + item.getGrade_name();
                    grade_id = item.getProduct_id();
                }
            });
        }

        dialog = builder.setView(view).create();

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);

    }

    /**
     * 添加收藏
     */
    private void addCollect() {
        isCollect = isCollect == 0 ? 1 : 0;
        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", productBean.getProduct_id());
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("goods_count", count);
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
        int product_type = productBean.getProduct_type();

        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", productBean.getProduct_id());
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("goods_count", count);//数量（如果为定制酒是至少6瓶）
        params.put("goods_name", productBean.getProduct_name() + grade_name);//商品名称（如果为定制酒是则将定制酒名称“-”拼接在后面）
        params.put("goods_price", productBean.getProduct_price());
        params.put("goods_pics", productBean.getProduct_pictures());
        if (grade_id != null)
            params.put("grade_id", grade_id);//定制酒ID（如果为定制酒时上传所选择的散酒id）

        cartPresenter.addShoppingCart(params);
    }

    @Override
    protected void initData() {
        cartPresenter = new ShoppingCartPresenter(this, ShoppingCartModel.newInstance());
        addPresenter(cartPresenter);
        collectPresenter = new CollectPresenter(this, CollectModel.newInstance());
        addPresenter(collectPresenter);
        productPresenter = new ProductPresenter(this, ProductModel.newInstance());
        addPresenter(productPresenter);

        Map<String, Object> params = new HashMap<>();
        params.put("goods_id", productBean.getProduct_id());
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        productPresenter.getProductDetail(params);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("商品详情");
    }

    @Override
    public void dealAddShoppingCartResult() {
        if (type == TYPE_BUY) {
            addOrder();
        } else
            showError("添加成功");
    }

    private void addOrder() {
        Intent intent = new Intent(mContext, AddOrderActivity.class);

        GoodsDetailBean bean = new GoodsDetailBean();
        bean.setCount(count);
        bean.setGoods_id(productBean.getProduct_id());
        bean.setName(productBean.getProduct_name());
        bean.setPrice(productBean.getProduct_price());
        bean.setIcon(productBean.getProduct_icon());

        List<GoodsDetailBean> list = new ArrayList<>();
        list.add(bean);

        intent.putExtra("data", (Serializable) list);
        startActivity(intent);
    }

    @Override
    public void showShoppingCart(List<ShoppingCartBean> list) {

    }

    @Override
    public void dealDeleteShoppingCartResult() {

    }

    @Override
    public void dealAddCollectResult() {
        Drawable dra = getResources().getDrawable(isCollect == 1 ? R.mipmap.ic_collection_def : R.mipmap.ic_collection_sel);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
        tv_collect.setCompoundDrawables(dra, null, null, null);
    }

    @Override
    public void showCollectList(List<CollectBean> list) {

    }

    @Override
    public void dealDeleteCollect() {

    }

    @Override
    public void showProductList(List<ProductBean> beans) {

    }

    @Override
    public void showProductDetail(ProductDetailBean bean) {
        if (bean != null) {
            productDetailBean = bean;
            initDialog();
        }
    }

    @Override
    public void showSpoorList(List<SpoorBean> beans) {

    }

    @Override
    public void dealDeleteSpoorListResult() {

    }
}

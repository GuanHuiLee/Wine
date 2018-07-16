package com.lgh.wine.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.api.Constant;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.BannerBean;
import com.lgh.wine.beans.HomeDataResult;
import com.lgh.wine.beans.WineAdBean;
import com.lgh.wine.contract.HomeContract;
import com.lgh.wine.model.HomeModel;
import com.lgh.wine.presenter.HomePresenter;
import com.lgh.wine.ui.home.adapter.PictureAdapter;
import com.lgh.wine.ui.web.WebActivity;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.SpannableStringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.banner_guide_content)
    BGABanner mContentBanner;
    @BindView(R.id.iv_brand)
    ImageView iv_brand;
    @BindView(R.id.recyclerView_custom)
    RecyclerView recyclerView_custom;//散酒
    @BindView(R.id.recyclerView_bluk)
    RecyclerView recyclerView_bulk;//定制
    @BindView(R.id.tv_update)
    TextView tv_update;

    public static final String TAG = HomeFragment.class.getName();
    private HomePresenter presenter;
    private List<BannerBean> bannerBeans;
    private List<String> bannerPath;
    private List<WineAdBean> bulkList;
    private List<WineAdBean> customList;
    private PictureAdapter adapterBulk;
    private PictureAdapter adapterCustom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        bulkList = new ArrayList<>();
        customList = new ArrayList<>();
        presenter = new HomePresenter(this, HomeModel.newInstance());
        presenter.getHomeData();

        recyclerView_bulk.setLayoutManager(new GridLayoutManager(mContext, 2));
        adapterBulk = new PictureAdapter(R.layout.item_pic, bulkList);
        recyclerView_bulk.setAdapter(adapterBulk);

        recyclerView_custom.setLayoutManager(new GridLayoutManager(mContext, 3));
        adapterCustom = new PictureAdapter(R.layout.item_pic, customList);
        recyclerView_custom.setAdapter(adapterCustom);

        mContentBanner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
                if (bannerBeans.size() > 0) {
                    BannerBean bannerBean = bannerBeans.get(position);
//                    if (bannerBean.getType() == 1) { //广告，可以打开链接
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", bannerBean.getBanner_url());
                    startActivity(intent);
//                    }

                }
            }
        });

        SpannableStringBuilder str = SpannableStringUtils.getBuilder("App版本已更新，下单更")
                .append("优惠")
                .setForegroundColor(ContextCompat.getColor(mContext, R.color.pink))
                .create();
        tv_update.setText(str);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showHomeData(HomeDataResult result) {
        bannerPath = new ArrayList<>();
        bannerBeans = new ArrayList<>();
        List<BannerBean> bannerLists = result.getBannerList();
        if (bannerLists != null) {

            for (BannerBean bannerBean : bannerLists) {
//                if (bannerBean.getStatus() == 1 && bannerBean.getShowStartTime() > System.currentTimeMillis() &&
//                        System.currentTimeMillis() < bannerBean.getShowEndTime()) {
                bannerPath.add(bannerBean.getBanner_image());
                bannerBeans.add(bannerBean);
//                }
            }

            if (bannerBeans.size() > 0) {
                mContentBanner.setData(bannerPath, null);
            }
            mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                @Override
                public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {

                    GlideHelper.loadImage(mContext, itemView, Constant.IMG_IP + model);
                }
            });
        }

        List<WineAdBean> brandWineAdList = result.getBrandWineAdList();
        if (brandWineAdList != null && brandWineAdList.size() > 0) {
            GlideHelper.loadImage(mContext, iv_brand, Constant.IMG_IP + brandWineAdList.get(0).getAd_image());
        }

        List<WineAdBean> bulkWineAdList = result.getBulkWineAdList();
        if (bulkWineAdList != null && bannerLists.size() > 0) {
            setBulkAdapter(bulkWineAdList);
        }

        List<WineAdBean> customMadeWineAdList = result.getCustomMadeWineAdList();
        if (customMadeWineAdList != null && !customMadeWineAdList.isEmpty()) {
            setCustomAdapter(customMadeWineAdList);
        }
    }

    private void setCustomAdapter(List<WineAdBean> customMadeWineAdList) {
        adapterCustom.setNewData(customMadeWineAdList);
    }

    private void setBulkAdapter(List<WineAdBean> bulkWineAdList) {
        adapterBulk.setNewData(bulkWineAdList);
    }
}

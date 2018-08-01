package com.lgh.wine.ui.personal;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.ui.coupon.fragment.CouponFragment;
import com.lgh.wine.ui.product.OrderFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ProductOrderMainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    private static final String[] FRAGMENT_TITLE = {"全部", "代付款", "代发货", "待收货", "待评价"};


    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_order_main;
    }


    @Override
    protected void initUI() {
        fragments.add(OrderFragment.newInstance(OrderFragment.TYPE_ALL));
        fragments.add(OrderFragment.newInstance(OrderFragment.TYPE_NEED_PAY));
        fragments.add(OrderFragment.newInstance(OrderFragment.TYPE_SEND_GOODS));
        fragments.add(OrderFragment.newInstance(OrderFragment.TYPE_RECEIVE_GOODS));
        fragments.add(OrderFragment.newInstance(OrderFragment.TYPE_COMMENT));

        viewpager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewpager);
        setTabLayout();
    }

    private void setTabLayout() {
        tabLayout.removeAllTabs();
        for (String title : FRAGMENT_TITLE) {
            addTab(title, title.equals(FRAGMENT_TITLE[0]));
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                View indicatorView = tabView.findViewById(R.id.view_item_indicator);
                indicatorView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View tabView = tab.getCustomView();
                View indicatorView = tabView.findViewById(R.id.view_item_indicator);
                indicatorView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void addTab(String tab, boolean indicator) {
        View customView = getTabView(mContext, tab, indicator);
        tabLayout.addTab(tabLayout.newTab().setCustomView(customView));
    }

    private View getTabView(Context context, String text, boolean indicator) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item_order_layout, null);
        TextView tabText = view.findViewById(R.id.tv_title);
        View indicatorView = view.findViewById(R.id.view_item_indicator);
        indicatorView.setVisibility(indicator ? View.VISIBLE : View.INVISIBLE);
        tabText.setText(text);
        return view;
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return FRAGMENT_TITLE[position];
        }

        @Override
        public int getCount() {
            return FRAGMENT_TITLE.length;
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("商品订单");
    }
}

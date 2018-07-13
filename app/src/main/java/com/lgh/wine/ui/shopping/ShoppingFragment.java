package com.lgh.wine.ui.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.ui.home.HomeFragment;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class ShoppingFragment extends BaseFragment {
    public static final String TAG = ShoppingFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        return view;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }
}

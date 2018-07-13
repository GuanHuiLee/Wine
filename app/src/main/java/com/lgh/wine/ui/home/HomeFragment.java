package com.lgh.wine.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class HomeFragment extends BaseFragment {
    public static final String TAG = HomeFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }
}

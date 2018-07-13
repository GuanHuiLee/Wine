package com.lgh.wine;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.ui.account.RegisterActivity;
import com.lgh.wine.ui.discover.DiscoverFragment;
import com.lgh.wine.ui.home.HomeFragment;
import com.lgh.wine.ui.personal.PersonalFragment;
import com.lgh.wine.ui.shopping.ShoppingFragment;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class MainActivity extends BaseActivity {
    @BindView(R.id.rb_home)
    RadioButton rbHome;

    @BindView(R.id.rb_discover)
    RadioButton rbDiscover;

    @BindView(R.id.rb_shopping)
    RadioButton rbShopping;

    @BindView(R.id.rb_mine)
    RadioButton rbMine;

    private FragmentManager fragmentManager;
    private CompoundButton selectView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initUI() {
        fragmentManager = getSupportFragmentManager();
        rbHome.setTag(HomeFragment.TAG);
        rbDiscover.setTag(DiscoverFragment.TAG);
        rbShopping.setTag(ShoppingFragment.TAG);
        rbMine.setTag(PersonalFragment.TAG);

        rbHome.setChecked(true);
    }

    @OnCheckedChanged({R.id.rb_home, R.id.rb_discover, R.id.rb_shopping, R.id.rb_mine})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            showFragment(buttonView);
        } else {
            hideFragment(buttonView);
        }
    }

    private void showFragment(CompoundButton curView) {
        if (selectView != null) {
            selectView.setChecked(false);
        }
        selectView = curView;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        String tag = (String) curView.getTag();
        if (fragmentManager.findFragmentByTag(tag) == null) {
            Fragment fragment = Fragment.instantiate(this, tag);
            fragmentTransaction.add(R.id.fl_content, fragment, tag);
            fragmentTransaction.commitAllowingStateLoss();
        } else {
            boolean isHide = fragmentManager.findFragmentByTag(tag).isHidden();
            if (isHide) {
                fragmentTransaction.show(fragmentManager.findFragmentByTag(tag));
                fragmentTransaction.commitAllowingStateLoss();
                fragmentManager.findFragmentByTag(tag).onResume(); //切换Fragment，实时刷新数据
            }
        }
    }

    private void hideFragment(CompoundButton lastView) {
        if (lastView != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            String tag = (String) lastView.getTag();
            if (fragmentManager.findFragmentByTag(tag) != null) {
                boolean isHide = fragmentManager.findFragmentByTag(tag).isHidden();
                if (!isHide) {
                    fragmentTransaction.hide(fragmentManager.findFragmentByTag(tag));
                    fragmentTransaction.commitAllowingStateLoss();
                }
            }
        }
    }

    long touchTime = 0;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {

    }
}

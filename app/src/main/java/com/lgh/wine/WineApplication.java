package com.lgh.wine;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.lgh.wine.base.BaseApplication;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.sobot.chat.SobotApi;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class WineApplication extends BaseApplication {

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
//                return new ClassicsHeader(context);
                return new MaterialHeader(context).setColorSchemeColors(Color.parseColor("#248bfe"));
            }
        });

        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        GlideHelper.init(R.mipmap.iv_error, R.mipmap.iv_error);
        SugarContext.init(this);

        /**
         * 初始化sdk
         * @param context 上下文  必填
         * @param appkey  用户的appkey  必填 如果是平台版用户需要传总公司的appkey
         * @param uid     用户的唯一标识，不能传一样的值，可以为空
         */
        SobotApi.initSobotSDK(this, Constant.SOBOT_KEY, "");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
        SobotApi.exitSobotChat(this);
    }

}

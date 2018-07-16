package com.lgh.wine;

import com.lgh.wine.base.BaseApplication;
import com.lgh.wine.utils.GlideHelper;
import com.orm.SugarContext;
import com.orm.SugarRecord;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class WineApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        GlideHelper.init(R.mipmap.iv_error, R.mipmap.iv_error);
        SugarContext.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}

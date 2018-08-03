package com.lgh.wine.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by niujingtong on 2018/7/23.
 * 模块：
 */
public class VersionUtil {
    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}

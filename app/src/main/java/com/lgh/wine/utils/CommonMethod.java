package com.lgh.wine.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by niujingtong on 2018/8/6.
 * 模块：
 */
public class CommonMethod {
    public static void contact(Activity activity) {
        try {
            String url3521 = "mqqwpa://im/chat?chat_type=wpa&uin=503425407";
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url3521)));
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showShort("未安装QQ");
        }
    }
}

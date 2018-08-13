package com.lgh.wine.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.lgh.wine.R;
import com.lgh.wine.beans.Account;
import com.sobot.chat.SobotApi;
import com.sobot.chat.api.enumtype.SobotChatTitleDisplayMode;
import com.sobot.chat.api.model.Information;

/**
 * Created by niujingtong on 2018/8/6.
 * 模块：
 */
public class CommonMethod {
    public static void contact(Activity activity) {
        Account account = AccountUtil.getAccount();
        Information info = new Information();
        info.setAppkey(Constant.SOBOT_KEY);
        info.setUid(account.getUserId());
        info.setUname(account.getUserNickname());
        info.setTel(account.getUserPhone());
        info.setRealname(account.getUserPhone());
        info.setShowSatisfaction(true);

        SobotApi.setEvaluationCompletedExit(activity, true);
        SobotApi.setChatTitleDisplayMode(activity, SobotChatTitleDisplayMode.ShowFixedText, "贵酒仁品");
        SobotApi.startSobotChat(activity, info);
    }
}

package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class CommentModel extends BaseModel {
    private static CommentModel model;

    public static CommentModel newInstance() {
        if (model == null)
            model = new CommentModel();
        return model;
    }

    public void getCommentList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getCommentList(params).enqueue(callBack);
    }

}

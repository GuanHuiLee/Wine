package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.base.BaseModel;

import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class ArticleModel extends BaseModel {
    private static ArticleModel model;

    public static ArticleModel newInstance() {
        if (model == null)
            model = new ArticleModel();
        return model;
    }

    public void getArticleList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getArticleList(params).enqueue(callBack);
    }
    public void getUserArticleList(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getArticleList(params).enqueue(callBack);
    }


    public void getArticleDetail(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().getArticleDetail(params).enqueue(callBack);
    }

    public void addUserArticle(Map<String, Object> params, MyCallBack callBack) {
        ApiFactory.getService().addUserArticle(params).enqueue(callBack);
    }
}

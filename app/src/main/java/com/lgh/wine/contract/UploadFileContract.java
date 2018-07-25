package com.lgh.wine.contract;

import com.lgh.wine.base.BasePresenter;
import com.lgh.wine.base.BaseView;
import com.lgh.wine.beans.ShoppingCartBean;
import com.lgh.wine.model.ShoppingCartModel;
import com.lgh.wine.model.UploadFileModel;

import java.util.List;
import java.util.Map;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public interface UploadFileContract {
    public interface View extends BaseView {

        void dealUploadFileResult(String url);

    }

    public abstract class Presenter extends BasePresenter<View, UploadFileModel> {

        public Presenter(View view, UploadFileModel model) {
            super(view, model);
        }


        public abstract void uploadFile(String params);

    }
}

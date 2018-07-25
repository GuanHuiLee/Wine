package com.lgh.wine.model;

import com.lgh.wine.MyCallBack;
import com.lgh.wine.api.ApiFactory;
import com.lgh.wine.api.ApiService;
import com.lgh.wine.base.BaseModel;
import com.lgh.wine.beans.BaseResult;
import com.lgh.wine.utils.Constant;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niujingtong on 2018/7/16.
 * 模块：
 */
public class UploadFileModel extends BaseModel {
    private static UploadFileModel model;

    public static UploadFileModel newInstance() {
        if (model == null)
            model = new UploadFileModel();
        return model;
    }

    public void uploadFile(String photo, MyCallBack callBack) {
        File file = new File(photo);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_IP)
                .build();
        retrofit.create(ApiService.class).uploadFile(body).
                enqueue(callBack);
    }

}

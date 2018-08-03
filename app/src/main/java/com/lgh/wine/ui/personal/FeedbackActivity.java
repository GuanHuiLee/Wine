package com.lgh.wine.ui.personal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.contract.UploadFileContract;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.model.UploadFileModel;
import com.lgh.wine.presenter.AccountPresenter;
import com.lgh.wine.presenter.UploadFilePresenter;
import com.lgh.wine.ui.personal.adapter.PictureAdapter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.GifSizeFilter;
import com.lgh.wine.utils.MyGlideEngine;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivity implements AccountContract.View, UploadFileContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.et_phone)
    EditText et_phone;

    PictureAdapter adapter;

    private static final int REQUEST_CODE_CHOOSE = 2;
    private List<String> mSelected;
    private AccountPresenter presenter;
    private UploadFilePresenter uploadFilePresenter;
    private String pics = "";
    private int size;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }


    @Override
    protected void initUI() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new PictureAdapter(R.layout.item_feedback_pic, new ArrayList<String>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter = new AccountPresenter(this, AccountModel.newInstance());
        uploadFilePresenter = new UploadFilePresenter(this, UploadFileModel.newInstance());
    }

    @OnClick({R.id.iv_pic})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.iv_pic:
                selectPic();
                break;
            default:
                break;
        }
    }

    private void selectPic() {

        AndPermission.with(this)
                .runtime()
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Matisse.from(FeedbackActivity.this)
                                .choose(MimeType.ofImage())
                                .countable(false)
                                .maxSelectable(9)
                                .capture(true)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .theme(R.style.Matisse_Zhihu)
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .captureStrategy(new CaptureStrategy(true, "com.lgh.wine.provider.MyFileProvider"))//参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                                .thumbnailScale(0.85f)
                                .imageEngine(new MyGlideEngine())
                                .forResult(REQUEST_CODE_CHOOSE);
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> permissions) {
                        showError("请允许存储权限");
                    }
                })
                .start();


    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("意见反馈");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("保存");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress("加载中");
                if (size > 0) {
                    uploadPic();
                } else {
                    addFeedback();
                }
            }
        });
    }

    private void uploadPic() {
        for (String s : mSelected) {
            uploadFilePresenter.uploadFile(s);
        }

    }

    private void addFeedback() {
        String content = et_content.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showError("请填写内容");
            hideProgress();
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put("content", content);
        params.put("user_id", AccountUtil.getUserId());
        params.put("phone", et_phone.getText().toString());
        params.put("pics", pics);

        presenter.addFeedback(params);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainPathResult(data);
            if (mSelected != null && !mSelected.isEmpty()) {
                adapter.setNewData(mSelected);
                size = mSelected.size();
                Logger.d("mSelected: " + mSelected);
            }
        }
    }

    @Override
    public void showLoginInfo(Account data) {

    }

    @Override
    public void showSmsCode(String code) {

    }

    @Override
    public void verifyCodeSuccess() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void dealAddFeedbackResult() {
        showError("反馈成功");
    }

    @Override
    public void dealUpdateUserResult() {

    }

    @Override
    public void dealUploadFileResult(String url) {
        size--;
        if (size > 0) pics += url + "|";
        else pics += url;

        if (size == 0) {
            Logger.d(pics);
            addFeedback();
        }
    }
}

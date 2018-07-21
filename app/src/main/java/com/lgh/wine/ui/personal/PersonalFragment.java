package com.lgh.wine.ui.personal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lgh.wine.MainActivity;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.ui.home.HomeFragment;
import com.lgh.wine.utils.GifSizeFilter;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.MyGlideEngine;
import com.orhanobut.logger.Logger;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by niujingtong on 2018/7/12.
 * 模块：
 */
public class PersonalFragment extends BaseFragment {
    public static final String TAG = PersonalFragment.class.getName();
    private static final int REQUEST_CODE_CHOOSE = 1;
    List<Uri> mSelected;

    @BindView(R.id.iv_icon)
    ImageView iv_icon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_icon})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                selectPic();
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
                        Matisse.from(getActivity())
                                .choose(MimeType.ofImage())
                                .countable(false)
                                .maxSelectable(1)
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Logger.d("mSelected: " + mSelected);
            if (!mSelected.isEmpty()) {
                CropImage.activity(mSelected.get(0))
                        .start(getActivity());
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                Glide.with(mContext)
                        .load(resultUri)
                        .into(iv_icon);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Logger.e(error.getMessage());
            }
        }
    }

    @OnClick({R.id.iv_setting})
    public void clickSetting(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;
            default:
                break;
        }
    }
}

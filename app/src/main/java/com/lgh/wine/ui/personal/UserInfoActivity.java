package com.lgh.wine.ui.personal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.Account;
import com.lgh.wine.contract.AccountContract;
import com.lgh.wine.contract.UploadFileContract;
import com.lgh.wine.model.AccountModel;
import com.lgh.wine.model.UploadFileModel;
import com.lgh.wine.presenter.AccountPresenter;
import com.lgh.wine.presenter.UploadFilePresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GifSizeFilter;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.InputItemView;
import com.lgh.wine.utils.MyGlideEngine;
import com.lgh.wine.utils.PickerDialogHelper;
import com.lgh.wine.utils.TimeUtils;
import com.orhanobut.logger.Logger;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity implements UploadFileContract.View, AccountContract.View {
    private static final int REQUEST_CODE_CHOOSE = 2;
    @BindView(R.id.input_name)
    InputItemView inputName;
    @BindView(R.id.input_sex)
    InputItemView inputSex;
    @BindView(R.id.input_birthday)
    InputItemView inputBirthday;
    @BindView(R.id.input_address)
    InputItemView inputAddress;
    @BindView(R.id.iv_icon)
    ImageView iv_icon;

    private List<Uri> mSelected;
    private UploadFilePresenter uploadFilePresenter;
    private AccountPresenter accountPresenter;
    private String iconUrl;
    private boolean isEdit;
    private int sex;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }


    @Override
    protected void initUI() {
        Account account = AccountUtil.getAccount();
        if (account != null) {
            String userIcon = account.getUserIcon();
            showIcon(userIcon);
            inputName.setContent(account.getUserNickname());
            inputAddress.setContent(account.getUserAddress());
            inputSex.setContent(account.getUserSex() == 1 ? "男" : "女");
            inputBirthday.setContent(account.getUserBirthday());
        }
    }

    private void showIcon(String userIcon) {
        if (!TextUtils.isEmpty(userIcon)) {
            GlideHelper.loadCircleImage(mContext, iv_icon, Constant.IMG_IP + userIcon, R.mipmap.iv_error);
        }
    }

    @Override
    protected void initData() {
        uploadFilePresenter = new UploadFilePresenter(this, UploadFileModel.newInstance());
        addPresenter(uploadFilePresenter);
        accountPresenter = new AccountPresenter(this, AccountModel.newInstance());
        addPresenter(accountPresenter);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("编辑个人资料");
    }

    @OnClick({R.id.input_name, R.id.input_birthday,
            R.id.input_sex, R.id.input_address_manage, R.id.input_address, R.id.ll_icon, R.id.input_safety})
    public void clickView(View view) {
        isEdit = true;
        switch (view.getId()) {
            case R.id.input_address:
                break;
            case R.id.input_address_manage:
                startActivity(new Intent(mContext, AddressManageActivity.class));
                break;
            case R.id.input_name:
                break;
            case R.id.input_safety:
                startActivity(new Intent(mContext, UpdatePwdActivity.class));
                break;
            case R.id.input_birthday:
                PickerDialogHelper.showTimePicker(mContext, Calendar.getInstance(), true, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        long time = date.getTime();
                        inputBirthday.setContent(TimeUtils.changeToYMD(time));
                    }
                });
                break;
            case R.id.input_sex:
                PickerDialogHelper.showSexOption(mContext, new OnOptionsSelectListener() {
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        inputSex.setContent(PickerDialogHelper.sex[options1]);
                        sex = options1 + 1;
                    }
                });
                break;
            case R.id.ll_icon:
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
                        Matisse.from(UserInfoActivity.this)
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
//                CropImage.activity(mSelected.get(0))
//                        .start(getActivity());
                List<String> strings = Matisse.obtainPathResult(data);
                uploadFilePresenter.uploadFile(strings.get(0));
            }
        }
    }

    @Override
    public void dealUploadFileResult(String url) {
        showIcon(url);
        iconUrl = url;
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

    }

    @Override
    public void dealUpdateUserResult() {
        Account account = AccountUtil.getAccount();
        account.setUserIcon(iconUrl);
        account.setUserNickname(inputName.getContent());
        account.setUserBirthday(inputBirthday.getContent());
        account.setUserAddress(inputAddress.getContent());
        account.update();

        showError("修改成功");
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { //按下的如果是BACK，同时没有重复
            saveDataAndExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveDataAndExit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveDataAndExit() {
        if (!isEdit) {
            finish();
            return;
        }
        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("user_birthday", inputBirthday.getContent());
        params.put("user_sex", sex);
        params.put("user_icon", iconUrl);
        params.put("user_address", inputAddress.getContent());
        params.put("user_nickname", inputName.getContent());

        accountPresenter.updateUser(params);
    }
}

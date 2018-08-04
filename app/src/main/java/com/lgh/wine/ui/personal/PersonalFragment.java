package com.lgh.wine.ui.personal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jauker.widget.BadgeView;
import com.lgh.wine.R;
import com.lgh.wine.base.BaseFragment;
import com.lgh.wine.beans.Account;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.beans.OrderStatusBean;
import com.lgh.wine.beans.TrackerBean;
import com.lgh.wine.contract.OrderContract;
import com.lgh.wine.contract.UploadFileContract;
import com.lgh.wine.model.OrderModel;
import com.lgh.wine.model.UploadFileModel;
import com.lgh.wine.presenter.OrderPresenter;
import com.lgh.wine.presenter.UploadFilePresenter;
import com.lgh.wine.ui.collect.CollectListActivity;
import com.lgh.wine.ui.coupon.CouponMainActivity;
import com.lgh.wine.ui.product.SpoorListActivity;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GifSizeFilter;
import com.lgh.wine.utils.GlideHelper;
import com.lgh.wine.utils.MyGlideEngine;
import com.orhanobut.logger.Logger;
import com.theartofdev.edmodo.cropper.CropImage;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by ligh on 2018/7/12.
 * 模块：
 */
public class PersonalFragment extends BaseFragment implements UploadFileContract.View, OrderContract.View {
    @BindView(R.id.tv_need_access)
    TextView tv_access;
    @BindView(R.id.tv_need_send)
    TextView tv_send;
    @BindView(R.id.tv_need_pay)
    TextView tv_pay;
    @BindView(R.id.tv_receive)
    TextView tv_receive;

    BadgeView bv_access;
    BadgeView bv_send;
    BadgeView bv_receive;
    BadgeView bv_pay;


    public static final String TAG = PersonalFragment.class.getName();
    private static final int REQUEST_CODE_CHOOSE = 1;
    List<Uri> mSelected;

    @BindView(R.id.iv_icon)
    ImageView iv_icon;

    private UploadFilePresenter presenter;
    private OrderPresenter orderPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    protected void initUI() {
        setIcon();

        bv_access = new BadgeView(mContext);
        bv_access.setTargetView(tv_access);
        bv_pay = new BadgeView(mContext);
        bv_pay.setTargetView(tv_pay);
        bv_receive = new BadgeView(mContext);
        bv_receive.setTargetView(tv_receive);
        bv_send = new BadgeView(mContext);
        bv_send.setTargetView(tv_send);

        bv_pay.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        bv_access.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        bv_receive.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
        bv_send.setBadgeGravity(Gravity.TOP | Gravity.RIGHT);
    }

    @Override
    protected void initData() {
        presenter = new UploadFilePresenter(this, UploadFileModel.newInstance());
        orderPresenter = new OrderPresenter(this, OrderModel.newInstance());
        addPresenter(presenter);
        addPresenter(orderPresenter);

        Map<String, Object> params = new HashMap<>();
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        orderPresenter.getOrderStatusNum(params);
    }

    @OnClick({R.id.iv_icon, R.id.input_spoor, R.id.input_collect, R.id.input_coupon, R.id.tv_edit
            , R.id.iv_setting, R.id.iv_feedback
            , R.id.tv_need_pay, R.id.tv_need_send, R.id.tv_receive, R.id.tv_need_access
            , R.id.input_comment})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
//                selectPic();
                break;
            case R.id.tv_edit:
                startActivity(new Intent(mContext, UserInfoActivity.class));
                break;
            case R.id.input_spoor:
                startActivity(new Intent(mContext, SpoorListActivity.class));
                break;
            case R.id.input_collect:
                startActivity(new Intent(mContext, CollectListActivity.class));
                break;
            case R.id.input_coupon:
                startActivity(new Intent(mContext, CouponMainActivity.class));
                break;
            case R.id.iv_feedback:
                startActivity(new Intent(mContext, FeedbackActivity.class));
                break;
            case R.id.iv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));
                break;

            case R.id.tv_need_pay:
                startOrder(1);
                break;
            case R.id.tv_need_send:
                startOrder(2);
                break;
            case R.id.tv_need_access:
                startOrder(4);
                break;
            case R.id.tv_receive:
                startOrder(3);
                break;
            case R.id.input_comment:
                startActivity(new Intent(mContext, MyCommentListActivity.class));
                break;
            default:
                break;
        }
    }

    private void startOrder(int typeNeedPay) {
        Intent intent = new Intent(mContext, ProductOrderMainActivity.class);
        intent.putExtra("type", typeNeedPay);
        startActivity(intent);
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
//                CropImage.activity(mSelected.get(0))
//                        .start(getActivity());
                List<String> strings = Matisse.obtainPathResult(data);
                presenter.uploadFile(strings.get(0));
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


    @Override
    public void dealUploadFileResult(String url) {
        showError("头像修改成功");

        Account account = AccountUtil.getAccount();
        account.setUserIcon(url);
        account.update();

        setIcon();

    }

    private void setIcon() {
        Account account = AccountUtil.getAccount();
        String userIcon = account.getUserIcon();
        if (!TextUtils.isEmpty(userIcon))
            GlideHelper.loadCircleImage(mContext, iv_icon, Constant.IMG_IP + userIcon, R.mipmap.iv_error);
    }

    @Override
    public void dealAddOrderResult(String id) {

    }

    @Override
    public void showOrderDetail(OrderBean bean) {

    }

    @Override
    public void dealDeleteOrderResult() {

    }

    @Override
    public void dealUpdateOrderResult() {

    }

    @Override
    public void showOrderList(List<OrderBean> list) {

    }

    @Override
    public void showOrderStatusNum(OrderStatusBean bean) {
        if (bean != null) {
            int dfh = bean.getOrder_num_dfh();
            int dfk = bean.getOrder_num_dfk();
            int dsh = bean.getOrder_num_dsh();
            int dpj = bean.getOrder_num_dpj();

            bv_send.setBadgeCount(dfh);
            bv_receive.setBadgeCount(dsh);
            bv_pay.setBadgeCount(dfk);
            bv_access.setBadgeCount(dpj);

            bv_send.setVisibility(dfh != 0 ? View.VISIBLE : View.GONE);
            bv_receive.setVisibility(dsh != 0 ? View.VISIBLE : View.GONE);
            bv_pay.setVisibility(dfk != 0 ? View.VISIBLE : View.GONE);
            bv_access.setVisibility(dpj != 0 ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void showCodeError(String s) {

    }

    @Override
    public void showPaySign(String s) {

    }

    @Override
    public void showTracker(TrackerBean.DataBean trackerBean) {

    }
}

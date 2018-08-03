package com.lgh.wine.ui.personal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.CommentBean;
import com.lgh.wine.beans.OrderBean;
import com.lgh.wine.contract.CommentContract;
import com.lgh.wine.model.CommentModel;
import com.lgh.wine.presenter.CommentPresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.ClearEditText;
import com.lgh.wine.utils.Constant;
import com.lgh.wine.utils.GlideHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AddOrderCommentActivity extends BaseActivity implements CommentContract.View {
    @BindView(R.id.rb_transport)
    MaterialRatingBar rb_transport;
    @BindView(R.id.rb_service)
    MaterialRatingBar rb_service;
    @BindView(R.id.rb_quality)
    MaterialRatingBar rb_quality;

    @BindView(R.id.ct_content)
    ClearEditText ct_content;
    @BindView(R.id.cb_show)
    CheckBox cb_show;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    private CommentPresenter presenter;
    private OrderBean orderBean;
    private String pic_url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_order_comment;
    }


    @Override
    protected void initUI() {
        orderBean = (OrderBean) getIntent().getSerializableExtra("data");
        GlideHelper.loadImage(mContext, iv_pic, Constant.IMG_IP + orderBean.getOrderGoodsList().get(0).getGoods_pic());
    }

    @Override
    protected void initData() {
        presenter = new CommentPresenter(this, CommentModel.newInstance());
        addPresenter(presenter);
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("发表评论");

        TextView tv_save = toolbar.findViewById(R.id.toolbar_other);
        tv_save.setText("发布");
        tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addComment();
            }
        });
    }

    private void addComment() {
        String content = ct_content.getText().toString();
        if (TextUtils.isEmpty(content)) {
            showError("请填写评论内容");
            return;
        }

        int transport = rb_transport.getProgress();
        int quality = rb_quality.getProgress();
        int service = rb_service.getProgress();
        int composite = (transport + quality + service) / 3;

        Map<String, Object> params = new HashMap<>();
        params.put("order_id", orderBean.getOrder_id());
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        params.put("composite_score", composite);
        params.put("quality_score", quality);
        params.put("transport_score", transport);
        params.put("serve_score", service);
        params.put("comment_content", content);
//        params.put("comment_pics", pic_url);
        params.put("is_show", cb_show.isChecked() ? 1 : 2);

        presenter.addComment(params);
    }

    @Override
    public void showCommentList(List<CommentBean> list) {

    }

    @Override
    public void dealAddCommentResult() {
        startActivity(new Intent(mContext, AddOrderCommentSuccessActivity.class));
        finish();
    }
}

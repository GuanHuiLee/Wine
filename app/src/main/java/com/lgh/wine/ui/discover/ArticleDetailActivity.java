package com.lgh.wine.ui.discover;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lgh.wine.R;
import com.lgh.wine.base.BaseActivity;
import com.lgh.wine.beans.ArticleBean;
import com.lgh.wine.beans.UserArticleBean;
import com.lgh.wine.contract.ArticleContract;
import com.lgh.wine.model.ArticleModel;
import com.lgh.wine.presenter.ArticlePresenter;
import com.lgh.wine.utils.AccountUtil;
import com.lgh.wine.utils.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ArticleDetailActivity extends BaseActivity implements ArticleContract.View {
    @BindView(R.id.pb_web_base)
    ProgressBar pb_web_base;
    @BindView(R.id.web_base)
    WebView web_base;
    @BindView(R.id.tv_like)
    TextView tv_like;
    @BindView(R.id.tv_message)
    TextView tv_message;

    private ArticleBean bean;
    private ArticlePresenter presenter;
    private int isLike = 2;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_detail;
    }


    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {
        bean = (ArticleBean) getIntent().getSerializableExtra("data");
        if (bean == null) return;

        presenter = new ArticlePresenter(this, ArticleModel.newInstance());
        addPresenter(presenter);
        Map<String, Object> params = new HashMap<>();
        params.put("article_id", bean.getArticle_id());
        params.put(Constant.USER_ID, AccountUtil.getUserId());
        presenter.getArticleDetail(params);

        pb_web_base.setMax(100);//设置加载进度最大值
        WebSettings webSettings = web_base.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            web_base.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        web_base.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码

        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false
        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        webSettings.setDomStorageEnabled(true);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        web_base.setSaveEnabled(true);
        web_base.setKeepScreenOn(true);

        web_base.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
//                textView.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pb_web_base.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法可在WebView中打开链接，反之用浏览器打开
        web_base.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!web_base.getSettings().getLoadsImagesAutomatically()) {
                    web_base.getSettings().setLoadsImagesAutomatically(true);
                }
                pb_web_base.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pb_web_base.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
        });
        web_base.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(paramAnonymousString1));
                startActivity(intent);
            }
        });

        web_base.loadUrl(bean.getArticle_url());
    }

    @Override
    public void onBackPressed() {
        if (web_base.canGoBack()) {
            web_base.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("详情");
    }

    @OnClick({R.id.tv_like, R.id.tv_message, R.id.iv_more})
    public void clickView(View view) {
        switch (view.getId()) {
            case R.id.tv_like:
                like();
                break;
            case R.id.tv_message:
                break;
            case R.id.iv_more:
                break;
        }
    }

    private void like() {
        isLike = isLike == 1 ? 2 : 1;

        Map<String, Object> params = new HashMap<>();
        params.put("article_like", isLike);
        params.put("article_id", bean.getArticle_id());

        presenter.addUserArticle(params);
    }

    @Override
    public void showArticleList(List<ArticleBean> list) {

    }

    @Override
    public void showUserArticleList(List<UserArticleBean> list) {

    }

    @Override
    public void showArticleDetail(ArticleBean bean) {
        if (bean != null) {
            tv_message.setText(bean.getComment_num());
            tv_like.setText(bean.getArticle_like());
            isLike = bean.isIf_articel() ? 1 : 2;
            setIsLike();
        }
    }

    private void setIsLike() {
        Drawable dra = getResources().getDrawable(isLike == 1 ? R.mipmap.ic_give_up_normal : R.mipmap.ic_give_up_selected);
        dra.setBounds(0, 0, dra.getMinimumWidth(), dra.getMinimumHeight());
        tv_like.setCompoundDrawables(dra, null, null, null);
    }

    @Override
    public void dealAddUserResult() {
        setIsLike();
    }
}

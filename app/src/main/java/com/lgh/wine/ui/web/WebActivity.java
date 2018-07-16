package com.lgh.wine.ui.web;

import android.content.Intent;
import android.graphics.Bitmap;
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

import butterknife.BindView;

public class WebActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }


    @Override
    protected void initUI() {

    }


    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_left);
        TextView tv_title = toolbar.findViewById(R.id.toolbar_title);
        tv_title.setText("轮播图详情");
        tv_title.setTextColor(getResources().getColor(android.R.color.black));
    }

    @BindView(R.id.pb_web_base)
    ProgressBar pb_web_base;
    @BindView(R.id.web_base)
    WebView web_base;
    private String url;
    private TextView textView;

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");

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
                textView.setText(title);
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

        web_base.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (web_base.canGoBack()) {
            web_base.goBack();
        } else {
            finish();
        }
    }

}

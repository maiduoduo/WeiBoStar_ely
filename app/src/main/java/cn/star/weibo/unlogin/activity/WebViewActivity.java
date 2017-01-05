package cn.star.weibo.unlogin.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.star.weibo.MainActivity;
import cn.star.weibo.base.BaseActivity;
import cn.star.weibo.common.Constants;
import cn.star.weibo.mvp.presenter.WebViewActivityPresent;
//import cn.star.weibo.mvp.presenter.imp.WebViewActivityPresentImp;
import cn.star.weibo.mvp.presenter.iml.WebViewActivityPresentImp;
import cn.star.weibo.mvp.view.WebViewActivityView;
import cn.star.weibo.R;


/**
 * 用户登录授权界面（在没有install官方微博客户端的情况下）
 */

public class WebViewActivity extends BaseActivity implements WebViewActivityView {

    private Context mContext;
    private String sRedirectUri;
    private WebView mWeb;
    private String mLoginURL;
    private WebViewActivityPresent mWebViewActivityPresent;
    private boolean mComeFromAccoutActivity;


    @Override
    protected int loadLayout() {
        return R.layout.webview_layout;
    }

    @Override
    protected void findView() {
        mContext = this;
        mWeb = $(R.id.webview);
    }

    @Override
    protected void regListener() {

    }

    @Override
    protected void loadData() {
        mLoginURL = getIntent().getStringExtra("url");
        mComeFromAccoutActivity = getIntent().getBooleanExtra("comeFromAccoutActivity", false);
        sRedirectUri = Constants.REDIRECT_URL;
        mWebViewActivityPresent = new WebViewActivityPresentImp(this);

        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWeb.setWebViewClient(new MyWebViewClient());
        mWeb.loadUrl(mLoginURL);
        mWeb.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mWeb.canGoBack()) {
                            mWeb.goBack();
                        } else {
                            if (!mComeFromAccoutActivity) {
                                Intent intent = new Intent(WebViewActivity.this, UnLoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                finish();
                            }

                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void reqServer() {

    }

    @Override
    protected void processClick(View view) {

    }

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
        intent.putExtra("fisrtstart", true);
        if (mComeFromAccoutActivity) {
            intent.putExtra("comeFromAccoutActivity", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(intent);
        finish();
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (isUrlRedirected(url)) {
                view.stopLoading();
                mWebViewActivityPresent.handleRedirectedUrl(mContext, url);
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            if (!url.equals("about:blank") && isUrlRedirected(url)) {
                view.stopLoading();
                mWebViewActivityPresent.handleRedirectedUrl(mContext, url);
                return;
            }
            super.onPageStarted(view, url, favicon);
        }
    }

    public boolean isUrlRedirected(String url) {
        return url.startsWith(sRedirectUri);
    }
}

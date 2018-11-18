package com.zhaoman.manny_core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhaoman.manny_core.delegates.IPageLoadListener;
import com.zhaoman.manny_core.delegates.web.chromeclient.WebChrromeClientImpl;
import com.zhaoman.manny_core.delegates.web.client.WebViewClientImpl;
import com.zhaoman.manny_core.delegates.web.route.RouteKeys;
import com.zhaoman.manny_core.delegates.web.route.Router;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class WebDelegateImpl extends WebDelagate {

    private IPageLoadListener mIPageLoadListener=null;

    public void setIPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener=listener;
    }

    public static WebDelegateImpl create(String url){

        final Bundle args=new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate=new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;

    }
    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getmWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        if (getUrl()!=null){

            //用原生的方式模拟web 跳转并进行页面加载

            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {

      final WebViewClientImpl client = new WebViewClientImpl(this);
      client.setIPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
      final WebChrromeClientImpl client = new WebChrromeClientImpl();
        return client;
    }
}

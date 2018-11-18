package com.zhaoman.manny_core.delegates.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:WebView 初始化接口
 */
public interface IWebViewInitializer {

    WebView initWebView(WebView webView);
    WebViewClient initWebViewClient();
    WebChromeClient initWebChromeClient();
}

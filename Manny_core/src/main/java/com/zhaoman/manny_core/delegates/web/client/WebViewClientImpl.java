package com.zhaoman.manny_core.delegates.web.client;

import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhaoman.manny_core.delegates.web.WebDelagate;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class WebViewClientImpl extends WebViewClient {


    private final WebDelagate DELEGATE;


    public WebViewClientImpl(WebDelagate delagate) {
        this.DELEGATE = delagate;
    }

   

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return super.shouldOverrideUrlLoading(view, url);
    }
}

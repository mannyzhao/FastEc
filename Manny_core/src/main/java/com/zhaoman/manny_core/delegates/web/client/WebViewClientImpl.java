package com.zhaoman.manny_core.delegates.web.client;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zhaoman.manny_core.delegates.IPageLoadListener;
import com.zhaoman.manny_core.delegates.web.WebDelagate;
import com.zhaoman.manny_core.delegates.web.route.Router;
import com.zhaoman.manny_core.ui.loader.MannyLoader;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class WebViewClientImpl extends WebViewClient {


    private final WebDelagate DELEGATE;

    private IPageLoadListener mIPageLoadListener=null;
    public void setIPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener=listener;
    }


    public WebViewClientImpl(WebDelagate delagate) {
        this.DELEGATE = delagate;
    }

   


    //设置app内部打开url，否则会使用机器自带的浏览器打开
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return Router.getInstance().handleWebUrl(DELEGATE,url);
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadStart();
        }

        MannyLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        if (mIPageLoadListener!=null){
            mIPageLoadListener.onLoadEnd();
        }
        MannyLoader.stopLoading();
    }
}

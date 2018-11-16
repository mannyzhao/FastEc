package com.zhaoman.manny_core.delegates.web;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.webkit.WebView;

import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.delegates.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public abstract class WebDelagate extends MannyDelegate {

    private WebView mWebView=null;

    private final ReferenceQueue<WebView> WEB_VIEW_QUENE=new ReferenceQueue<>();

    private String mUrl=null;

    private boolean mIsWebViewAvailable=false;


    public WebDelagate(){

    }



    public abstract IWebViewInitializer setInitializer();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        mUrl = arguments.getString(RouteKeys.URL.name());

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mWebView!=null){
            mWebView.onPause();
        }
    }


    public WebView getmWebView(){
        if (mWebView==null){
            throw new NullPointerException("webview is null");
        }

        return mIsWebViewAvailable?mWebView:null;
    }

    public String getUrl(){
        if (mUrl==null){
            throw new NullPointerException("url is null");
        }
        return mUrl;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView!=null){
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView=null;
        }
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView(){
        if (mWebView!=null){
            mWebView.removeAllViews();
            mWebView.destroy();
        }else{
            final IWebViewInitializer initializer = setInitializer();
            if (initializer!=null){
                final WeakReference<WebView> webViewWeakReference=
                        new WeakReference<>(new WebView(getContext()),WEB_VIEW_QUENE);

                mWebView = webViewWeakReference.get();
                mWebView= initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                mWebView.addJavascriptInterface(MannyWebInterface.create(this),"manny");
                mIsWebViewAvailable=true;

            }else{

                throw new NullPointerException("Initializer is null!");
            }
        }
    }
}

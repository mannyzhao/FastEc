package com.zhaoman.manny_core.delegates.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.delegates.web.WebDelagate;

public abstract class Event implements IEvent {

    private Context mContext=null;
    private String mACtion=null;
    private WebDelagate mDelegate=null;
    private String mUrl=null;
    private WebView mWebView=null;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }
    public WebView getWebView(){
        return  mDelegate.getmWebView();
    }

    public String getACtion() {
        return mACtion;
    }

    public void setACtion(String mACtion) {
        this.mACtion = mACtion;
    }

    public MannyDelegate getDelegate() {
        return mDelegate;
    }

    public void setDelegate(WebDelagate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}

package com.zhaoman.manny_core.delegates.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.zhaoman.manny_core.delegates.MannyDelegate;
import com.zhaoman.manny_core.delegates.web.WebDelagate;
import com.zhaoman.manny_core.delegates.web.WebDelegateImpl;

public class Router {


    private Router(){

    }

    private static final class Holder{

        private static final Router INSTANCE=new Router();
    }

    public static Router getInstance(){

        return Holder.INSTANCE;
    }


    public final boolean handleWebUrl(WebDelagate delagate,String url){

        //如果是电话协议
        if (url.contains("tel:")){
            callPhone(delagate.getContext(),url);
            return true;
        }

        final MannyDelegate topDelegate=delagate.getTopDelegate();


        final WebDelegateImpl webDelegate = WebDelegateImpl.create(url);

        topDelegate.start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView,String url){
        if (webView!=null){
            webView.loadUrl(url);
        }else{
            throw new NullPointerException("webview is null");
        }
    }

    //加载本地html页面
    private void loadLocalPage(WebView webView,String url){
        loadWebPage(webView,"file:///android_asset/"+url);
    }


    private  void loadPage(WebView webView,String url){
        if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url)){
            loadWebPage(webView,url);
        }else{
            loadLocalPage(webView,url);
        }
    }

    public final void loadPage(WebDelagate delagate,String url){

        loadPage(delagate.getmWebView(),url);

    }


    //打电话
    private void callPhone(Context context,String uri){

        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context,intent,null);

    }
}

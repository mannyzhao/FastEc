package com.zhaoman.manny_core.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.zhaoman.manny_core.delegates.web.route.RouteKeys;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:
 */
public class WebDelegateImpl extends WebDelagate {


    public static WebDelegateImpl create(String url){

        final Bundle args=new Bundle();
        args.putString(RouteKeys.URL.name(),url);
        final WebDelegateImpl delegate=new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;

    }
    @Override
    public IWebViewInitializer setInitializer() {
        return null;
    }

    @Override
    public Object setLayout() {
        return getmWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        if (getUrl()!=null){

            //用原生的方式模拟web 跳转
        }
    }
}

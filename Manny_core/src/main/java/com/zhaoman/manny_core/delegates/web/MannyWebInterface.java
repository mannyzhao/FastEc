package com.zhaoman.manny_core.delegates.web;

import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.zhaoman.manny_core.delegates.web.event.Event;
import com.zhaoman.manny_core.delegates.web.event.EventManager;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:用来和原生进行交互的接口
 */
public class MannyWebInterface  {

    private final WebDelagate DELEGATE;

    private MannyWebInterface(WebDelagate delagate) {
        this.DELEGATE = delagate;
    }

    static MannyWebInterface create(WebDelagate delagate){
        return new MannyWebInterface(delagate);
    }

    @JavascriptInterface
    public String  event(String params){
        final String action=JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event!=null){

            event.setACtion(action);
            event.setDelegate(DELEGATE);
            event.setContext(DELEGATE.getContext());
            event.setUrl(DELEGATE.getUrl());

            return event.execute(params);
        }

        return null;
    }
}

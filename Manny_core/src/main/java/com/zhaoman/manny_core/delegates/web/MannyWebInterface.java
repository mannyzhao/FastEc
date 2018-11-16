package com.zhaoman.manny_core.delegates.web;

import com.alibaba.fastjson.JSON;

/**
 * Author:zhaoman
 * Date:2018/11/15
 * Description:用来和原生进行交互
 */
public class MannyWebInterface  {

    private final WebDelagate DELEGATE;

    public MannyWebInterface(WebDelagate delagate) {
        this.DELEGATE = delagate;
    }

    static MannyWebInterface create(WebDelagate delagate){
        return new MannyWebInterface(delagate);
    }

    public String  event(String params){
        final String action=JSON.parseObject(params).getString("action");
        return null;
    }
}
